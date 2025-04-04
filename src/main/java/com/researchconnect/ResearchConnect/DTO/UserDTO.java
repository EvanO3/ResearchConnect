
package com.researchconnect.ResearchConnect.DTO;
/**
 * DTO's  (Data Transfer Object) is a simple java class that is used
 * to transfer data between different layers of an application
 * prevents exposing sensitive data 
 */
public class UserDTO {
    
    private String id;
    private String email;
    private String firstName;
    private String lastName;
    private String role;
    
    
    public UserDTO(){}


    public UserDTO(String id, String email, String firstName, String lastName, String role){
        this.id=id;
        this.email=email;
        this.firstName=firstName;
        this.lastName=lastName;
        this.role=role;
    }

    
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public String getRole() {
        return role;
    }
    public void setRole(String role) {
        this.role = role;
    }
    
}
