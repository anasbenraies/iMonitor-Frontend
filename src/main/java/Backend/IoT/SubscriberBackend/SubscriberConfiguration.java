package Backend.IoT.SubscriberBackend;


import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SubscriberConfiguration {
//
    @Bean
    CommandLineRunner commandLineRunner2(SubscriberService subscriberService) {
        return args -> {
        subscriberService.subscribe("deviceUsage");
     };

     }
}
