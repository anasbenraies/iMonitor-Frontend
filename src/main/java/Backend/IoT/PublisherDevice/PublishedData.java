package Backend.IoT.PublisherDevice;

import lombok.Data;
import org.springframework.stereotype.Component;


import java.time.LocalDate;
import java.util.Random;
@Data
@Component
public class PublishedData {

    private LocalDate usageDate;

    private int durationInMinutes;

    private Long device_id;





    public PublishedData(){
        randomUsageDate();
        randomDuration();
        randomDeviceId();
    }

    public void randomUsageDate(){
        Random random= new Random();
        int Year = 2024 ;
        int Month=random.nextInt(12)+1 ;
        int MaxDay = LocalDate.of(Year,Month,1).lengthOfMonth();
        int Day=random.nextInt(MaxDay)+1;
        this.usageDate=LocalDate.of(Year,Month,Day);
    }

    public void randomDuration(){
        Random random =new Random();
        this.durationInMinutes=random.nextInt(120)+1;
    }

    public void randomDeviceId(){
        Random random =new Random();
        this.device_id= (long) (random.nextInt(2)+1);
    }

}
