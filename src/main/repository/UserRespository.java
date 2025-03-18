import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRespository extends JpaRepository<Users, UUID> {

    //Optional is used when it as a safeguard as it may or may not contain a null value
    Optional<Users>findbyId(UUID id);
    Optional<Users>findbyEmail(String email);
    Optional<Users>findByAuthId(UUID authUserId);

    List<Users>findByRole(Role role);



} 

