package Backend.IoT.DeviceUsage;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Collections;
import java.util.List;

@Service
public class DeviceUsageService {


    private final DeviceUsageRepository deviceUsageRepository ;

    @Autowired
    DeviceUsageService(DeviceUsageRepository deviceUsageRepository){
        this.deviceUsageRepository=deviceUsageRepository ;
    }

    public List<DeviceUsage> getUsages(){
        return deviceUsageRepository.findAll();
    }


    public List<DeviceUsage> getUserUsages(Long id) {
        List<DeviceUsage> UserUsages = deviceUsageRepository.GetUserUsages(id);
        //when dealing with Lists check for empty list always
        if (UserUsages == null){
            return Collections.emptyList();
        }
            return UserUsages ;
    }
}
