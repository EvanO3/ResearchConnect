package com.researchconnect.ResearchConnect.repository;
import org.springframework.stereotype.Repository;

import com.researchconnect.ResearchConnect.DTO.RegisterResponseDTO;
import com.researchconnect.ResearchConnect.enums.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.researchconnect.ResearchConnect.model.*;

import reactor.core.publisher.Mono;

import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface UserRespository extends JpaRepository<Users, UUID> {

    //Optional is used when it as a safeguard as it may or may not contain a null value
    Optional<Users>findById(UUID id);
    Optional<Users>findByEmail(String email);
    Optional<Users>findByauthUserId(UUID authUserId);
    List<Users>findByRole(Role role);



} 

