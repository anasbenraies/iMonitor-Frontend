package Backend.IoT.DeviceUsage;

import Backend.IoT.Device.Device;
import Backend.IoT.User.User;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface DeviceUsageRepository extends JpaRepository<DeviceUsage, Long> {
    @Query("SELECT du FROM DeviceUsage du ")
    List<DeviceUsage> findAllWithDeviceAndUser();
    @Query("SELECT du FROM DeviceUsage du JOIN du.device d WHERE d.user.id = :userId ORDER BY du.usageDate")
    List<DeviceUsage> GetUserUsages(Long userId);
}
