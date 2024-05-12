package Backend.IoT.User;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {


    private final UserRepository userRepository;

    @Autowired
    UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    public List<User> getUsages(){
        return userRepository.findAll();
    }

    public User findUserByCredentials(String email, String password) {
        return userRepository.findUserByCredentials(email , password);
    }
}
