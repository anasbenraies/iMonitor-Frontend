package Backend.IoT.DeviceUsage;


import Backend.IoT.Device.Device;
import Backend.IoT.User.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path ="/ressources")
public class DeviceUsageRestController {

    private final DeviceUsageService deviceUsageService ;


    @Autowired
    DeviceUsageRestController(DeviceUsageService deviceUsageService){
        this.deviceUsageService=deviceUsageService;
    }

    // get the user with devices with usage on device
    @GetMapping(path = "/devicesUsages")
    public ResponseEntity<List<DeviceUsage>> getUsages() {
        try {
            List<DeviceUsage> usages = deviceUsageService.getUsages();
            return ResponseEntity.ok(usages);
        } catch (Exception e) {
            e.printStackTrace(); // Log the exception
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @GetMapping(path = "/devicesUsages/{UserId}")
    public ResponseEntity<List<DeviceUsage>> getUserUsages(@PathVariable("UserId") Long id ){
        try {
            List<DeviceUsage> UsagesUsages =  deviceUsageService.getUserUsages(id);
            if (UsagesUsages !=null){
                return ResponseEntity.ok(UsagesUsages);
            }else{
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
            }
        }catch(Exception e ){
            e.printStackTrace(); // Log the exception
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
}
