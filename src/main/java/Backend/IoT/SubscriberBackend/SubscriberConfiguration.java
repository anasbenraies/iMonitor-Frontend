package Backend.IoT.SubscriberBackend;


import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SubscriberConfiguration {

    /**
     * Creates a CommandLineRunner bean that initializes the subscriber service.
     *
     * @param       subscriberService	an instance of the SubscriberService class
     * @return  	a CommandLineRunner instance that starts the subscriber service
     */
    @Bean
    CommandLineRunner SubscriberRunner(SubscriberService subscriberService) {
        return args -> {
        System.out.println("Subscribed Started");
        subscriberService.subscribe("deviceUsage");

     };

     }
}
