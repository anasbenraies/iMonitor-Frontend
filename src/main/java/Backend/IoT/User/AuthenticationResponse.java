package Backend.IoT.User;


import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AuthenticationResponse {
    private Long id;
    private String token ;
    private String username;
    private String email ;
    private LocalDate dob;
    private Role role;



    public AuthenticationResponse(User user){
        this.id=user.getId();
        this.username=user.getUsername();
        this.email=user.getEmail();
        this.dob=user.getDob();
        this.role=user.getRole();
    }
}


