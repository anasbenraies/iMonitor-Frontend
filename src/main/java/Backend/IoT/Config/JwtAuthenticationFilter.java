package Backend.IoT.Config;

import Backend.IoT.User.User;
import Backend.IoT.User.UserRepository;
import Backend.IoT.User.UserService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.antlr.v4.runtime.misc.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.lang.NonNull ;
import java.io.IOException;

@Component
@RequiredArgsConstructor //constructor for the private finale attributes only
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtService jwtService ;

    private final UserDetailsService userDetailsService;
    private final UserRepository userRepository ;

    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request,
                                    @NonNull HttpServletResponse response,
                                    @NonNull FilterChain filterChain)
            throws ServletException, IOException {

        final String authHeader = request.getHeader("Authorization");
        final String jwt ;
        final String userEmail ;
        //checking token
        if(authHeader==null || !authHeader.startsWith("Bearer ")){
            filterChain.doFilter(request,response);
            return;
        }
        jwt=authHeader.substring(7);
        //it's extracting the email actually not the Username
        userEmail=jwtService.extractEmail(jwt);
        //verification of jwt token
        if (userEmail !=null && SecurityContextHolder.getContext().getAuthentication()==null){
            //loading actually is by email && casted to user instead of userdetails
            User user = (User) this.userDetailsService.loadUserByUsername(userEmail);

            //User user = this.userRepository.findUserByEmail(userEmail);

            if (jwtService.isTokenValid(jwt,user)){
                UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                        //UserDetails ; we are using user instead
                        user,
                        null,
                        user.getAuthorities()
                );
                authToken.setDetails(
                        new WebAuthenticationDetailsSource().buildDetails(request)
                );
                SecurityContextHolder.getContext().setAuthentication(authToken);
            }
        }
        filterChain.doFilter(request,response);
    }
}
