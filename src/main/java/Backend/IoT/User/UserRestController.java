package Backend.IoT.User;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path ="/ressources")
public class UserRestController {

    private final UserService userService;


    @Autowired
    UserRestController(UserService userService){
        this.userService = userService;
    }



    @PostMapping(path = "/user")
    public ResponseEntity<User> getUser(@RequestBody User u){
        try {
            //this is the user taht we are looking for
            User user= userService.findUserByCredentials(u.getEmail(),u.getPassword());
            if(user!=null){
                return  ResponseEntity.ok(user);
            }else{
                return ResponseEntity.notFound().build();
            }
        }catch (Exception e){
           e.printStackTrace();
           return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }


}
