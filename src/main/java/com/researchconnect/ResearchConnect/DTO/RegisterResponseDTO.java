package com.researchconnect.ResearchConnect.DTO;

import java.util.UUID;

public class RegisterResponseDTO {

    private String email;
    private UUID id;


    public RegisterResponseDTO(){}


    public RegisterResponseDTO(UUID id, String email){
        this.id = id;
       
        this.email= email;
        
    }





    public String getEmail() {
        return email;
    }


    public void setEmail(String email) {
        this.email = email;
    }


    public UUID getId() {
        return id;
    }


    public void setId(UUID id) {
        this.id = id;
    }
    
}
