
package Backend.IoT.User;

import Backend.IoT.DeviceUsage.DeviceUsage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    @Query("SELECT du FROM DeviceUsage du ")
    List<DeviceUsage> findAllWithDeviceAndUser();

    @Query("SELECT u FROM User u WHERE u.email = :email")
    User findUserByEmail(String email);
}





