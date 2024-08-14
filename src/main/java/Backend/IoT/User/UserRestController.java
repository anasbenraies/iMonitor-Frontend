package Backend.IoT.User;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path ="/users")
public class UserRestController {

    private final UserService userService;


    @Autowired
    UserRestController(UserService userService){
        this.userService = userService;
    }

    @PostMapping(path = "/SignUp")
    public ResponseEntity<?> SignUp(@RequestBody User user){
        try {
            //this method returns the User(and save it to db) if the user is valid or returns null if the user is not valid
            AuthenticationResponse SignedUpUser = userService.SignUp(user);
            if(SignedUpUser!=null){
                return ResponseEntity.ok(SignedUpUser);
            }else{
                //if SignedUpUser is not in a valid form
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("The user is not valid for SignUp !");
            }
        }catch(Exception e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }





    //@CrossOrigin(origins = "http://localhost:3001")
    @PostMapping(path = "/Login")
    public ResponseEntity<?> LoginUser(@RequestBody User u){
        try {
            //this is the user that we are looking for
            //AuthenticationResponse is a user with token
            AuthenticationResponse user= userService.findUserByEmail(u);
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
