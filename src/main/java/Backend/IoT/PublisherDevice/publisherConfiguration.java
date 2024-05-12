package Backend.IoT.PublisherDevice;


import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class publisherConfiguration {


    @Bean
    CommandLineRunner commandLineRunner(PublisherService publisherService) {
        return args -> {
//            //publisher need to wait a for the subscriber to subscribe before starting to send data
//            Thread.sleep(3000);
//            while (true){
//                Thread.sleep(3000);
//                PublishedData publishedData = new PublishedData();
////                System.out.println(publishedData.getUsageDate().toString()+"/"
////                        +publishedData.getDurationInMinutes()+"/"+publishedData.getDevice_id());
//                publisherService.publishMessage("deviceUsage",publishedData.getUsageDate().toString()+"/"
//                        +publishedData.getDurationInMinutes()+"/"+publishedData.getDevice_id());
//            }
//
//     };

        };
    }
}
