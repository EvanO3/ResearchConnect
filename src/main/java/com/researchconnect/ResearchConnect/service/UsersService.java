package com.researchconnect.ResearchConnect.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.fasterxml.jackson.databind.JsonNode;
import com.researchconnect.ResearchConnect.DTO.RegisterDTO;
import com.researchconnect.ResearchConnect.DTO.RegisterResponseDTO;
import com.researchconnect.ResearchConnect.DTO.UserDTO;
import com.researchconnect.ResearchConnect.Exceptions.CustomRegistrationException;
import com.researchconnect.ResearchConnect.controller.AuthController;
import com.researchconnect.ResearchConnect.repository.UserRespository;
import java.util.UUID;
import reactor.core.publisher.Mono;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class UsersService {

    private final UserRespository userRespository;
    private final WebClient webClient;
    
  
    public UsersService(UserRespository userRespository, WebClient webClient){
        this.userRespository = userRespository;
        this.webClient= webClient;

    }


 public Mono<Boolean> emailExists(String email){
    Logger logger = LoggerFactory.getLogger(this.getClass());
    //Returns a 403 forbidden, most likely because Anon key is being used instead of service role key,

    // in controller also add error handling, if this route fails, do not continue

        return webClient.get().uri(uriBuilder -> uriBuilder
        .path("/auth/v1/admin/users")
        .queryParam("email", email)
        .build())
        .retrieve()
        .bodyToMono(JsonNode.class)
        .map(response ->{
            logger.info("Supabase response: {}", response.toPrettyString()); 
            JsonNode users = response.get("users");
            if(users !=null && users.isArray()){
                for (JsonNode user : users) {
                    if (user.get("email").asText().equals(email)) {
                        return true;  // Email found
                    }
                }
                      
            }
            return false;
        }).onErrorReturn(false);
    }
    

  public Mono<RegisterResponseDTO> registerUser(String email, String password){
    Logger logger = LoggerFactory.getLogger(this.getClass());
    return webClient.post()
    .uri("/auth/v1/signup")
    .bodyValue(new RegisterDTO(email, password))
    .retrieve()
    .bodyToMono(JsonNode.class)
    .map(jsonNode ->{
        logger.info("Supabase response: {}", jsonNode.toPrettyString()); 
         JsonNode userNode = jsonNode.path("data").path("user");

         String StringId =  jsonNode.path("id").asText();


        logger.info("Extracted ID: '{}'", StringId);

        UUID id;
        try {
            id = UUID.fromString(StringId);
        } catch (IllegalArgumentException e) {
            throw new CustomRegistrationException("Invalid UUID returned: " + StringId, e);
        }
         String usersEmail = userNode.path("email").asText();
    
         return new RegisterResponseDTO(id,usersEmail);
        
    
         
    })
    .onErrorResume(e->{
        return Mono.error(new CustomRegistrationException("Failed to register the user", e));
    });
    
  }




}
