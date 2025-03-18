import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.researchconnect.ResearchConnect.repository.UserRespository;
@Service
public class UsersService {
    private final UserRespository userRespository;

    @Autowired
    public UsersService(UserRespository userRespository){
        this.userRepository = userRespository;
    }
    

public UserDTO getUserByEmail(String email){
    Users user = userRespository.findbyEmail(email);
    return new UserDTO(user);
}


}
