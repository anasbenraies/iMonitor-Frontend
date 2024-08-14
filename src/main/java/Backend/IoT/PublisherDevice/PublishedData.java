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

    private Float energy_usage_in_kwh ;





    public PublishedData(){
        randomUsageDate();
        randomDuration();
        randomDeviceId();
        randomEnergyUsage();
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
        // Sending data to device 1 ,2 ,3 and 4
        Random random =new Random();
        this.device_id = (long) (random.nextInt(4) + 1);
    }

    public void randomEnergyUsage(){
        int durationInMinutes = this.durationInMinutes ;
        this.energy_usage_in_kwh = durationInMinutes / 100.0f ;
    }

}
