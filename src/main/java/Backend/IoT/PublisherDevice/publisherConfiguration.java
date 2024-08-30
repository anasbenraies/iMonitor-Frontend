package Backend.IoT.PublisherDevice;


import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class publisherConfiguration {


    /**
     * Creates a CommandLineRunner bean that continuously generates and publishes PublishedData objects
     * to the "deviceUsage" topic using the provided PublisherService. The method waits for 4 seconds
     * before starting to send data, and then sends a new PublishedData object every 3 seconds.
     *
     * @param  publisherService   the PublisherService instance to use for publishing messages
     * @return                    the CommandLineRunner bean
     */
//    @Bean
//    CommandLineRunner commandLineRunner(PublisherService publisherService) {
//        return args -> {
//            //publisher need to wait a for the subscriber to subscribe before starting to send data
//            Thread.sleep(4000);
//            while (true){
//                Thread.sleep(3000);
//                PublishedData publishedData = new PublishedData();
////                System.out.println(publishedData.getUsageDate().toString()+"/"
////                        +publishedData.getDurationInMinutes()+"/"+publishedData.getDevice_id());
//                String SentData = publishedData.getUsageDate().toString()+"/"
//                        +publishedData.getDurationInMinutes()+"/"+publishedData.getDevice_id()+"/"+publishedData.getEnergy_usage_in_kwh();
//                publisherService.publishMessage("deviceUsage",SentData);
//            }
//
//     };
//
//        };
    }

