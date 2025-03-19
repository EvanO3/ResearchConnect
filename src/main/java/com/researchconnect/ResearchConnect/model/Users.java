package com.researchconnect.ResearchConnect.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import com.researchconnect.ResearchConnect.enums.*;
import java.util.UUID;
import java.sql.Timestamp;
/*
 * Finish the rest of the validations after
 * 
*/
@Entity
@Table(name="users")
public class Users {

@Id
@GeneratedValue(strategy =GenerationType.AUTO)
private UUID id;

@Column(name ="user_id", nullable = false, unique = true)
private UUID authUserId;

@Email
@Column(name="email", nullable = false, unique = true)
private String email;

//enum of role would be made, a role could be a student or professor
@Enumerated(EnumType.STRING)
@Column(name="role", nullable = false)
private Role role;

@Size(min=2, max=25, message="Name must be between 2 and 25 chars")
@Column(name="first_name", nullable = false)
private String firstName;

@Column(name="last_name", nullable = false)
private String lastName;

@Column(name="created_at")
private Timestamp created_at;

@Column(name="updated_at")
private Timestamp updated_at;


@PrePersist
public void onCreate(){
    this.created_at = new Timestamp(System.currentTimeMillis());
    this.updated_at= this.created_at;
}


@PreUpdate
public void onUpdate(){
    this.updated_at = new Timestamp(System.currentTimeMillis());
}



public UUID getAuthUserId(){
    return authUserId;
}

public void setAuthUserId(UUID authUserId){
   this.authUserId= authUserId;
}

public void setFirstName(String firstName){
    this.firstName = firstName;
}

public String getFirstName(){
    return firstName;
}


public void setLastName(String lastName){
    this.lastName = lastName;
}

public String getLastName(){
    return lastName;
}

public Role getRole(){
    return role;
}

public void setRole(Role role){
    this.role= role;
}

public UUID getId(){
    return id;
}

public void setId(UUID id){
    this.id = id;
}

public Users(){}

public Users(UUID authUserId, String email, Role role, String firstName, String lastName ){
    this.authUserId= authUserId;
    this.email= email;
    this.role= role;
    this.firstName= firstName;
    this.lastName= lastName;
   
}
 

public String getEmail() {
    return email;
}


public void setEmail(String email) {
    this.email = email;
}


public Timestamp getCreated_at() {
    return created_at;
}


public void setCreated_at(Timestamp created_at) {
    this.created_at = created_at;
}


public Timestamp getUpdated_at() {
    return updated_at;
}


public void setUpdated_at(Timestamp updated_at) {
    this.updated_at = updated_at;
}



}