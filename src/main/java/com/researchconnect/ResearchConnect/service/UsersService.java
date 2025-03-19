package com.researchconnect.ResearchConnect.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.fasterxml.jackson.databind.JsonNode;
import com.researchconnect.ResearchConnect.controller.AuthController;
import com.researchconnect.ResearchConnect.repository.UserRespository;

import reactor.core.publisher.Mono;

@Service
public class UsersService {

    private final UserRespository userRespository;
    private final WebClient webClient;
    
  
    public UsersService(UserRespository userRespository, WebClient webClient){
        this.userRespository = userRespository;
        this.webClient= webClient;
;
    }


    public Mono<Boolean> emailExists(String email){
        return webClient.get().uri(uriBuilder -> uriBuilder
        .path("/auth/v1/admin/users")
        .queryParam("email", email)
        .build())
        .retrieve()
        .bodyToMono(JsonNode.class)
        .map(response ->{
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
    

//   public Mono<String> registerUser(String email, String password){
   
//  }




}
