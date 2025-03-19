package com.researchconnect.ResearchConnect.controller;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.researchconnect.ResearchConnect.DTO.RegisterDTO;
import com.researchconnect.ResearchConnect.DTO.UserDTO;
import com.researchconnect.ResearchConnect.service.UsersService;

import reactor.core.publisher.Mono;

import org.springframework.web.bind.annotation.PostMapping;


@RestController
@RequestMapping("/auth")
public class AuthController {

    private final UsersService userService;

    public AuthController(UsersService userService){
        this.userService= userService;
    }
    
@GetMapping("/hello")
public ResponseEntity<String> getHello(){
 String hello = "Hello world";
 return new ResponseEntity<>(hello, HttpStatus.OK);
}
   

@PostMapping("/register")
public Mono<ResponseEntity<String>>registerUser(@RequestBody RegisterDTO registerDTO) {
    
   return userService.emailExists(registerDTO.getEmail()).flatMap(response ->{
    if(response){
        return Mono.just(ResponseEntity.badRequest().body("Email has already been taken"));
    }

    //Change this to actually register the user using a service route
    return Mono.just(ResponseEntity.badRequest().body("Email has already been taken"));
   });
 }

}
