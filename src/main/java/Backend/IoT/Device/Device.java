package Backend.IoT.Device;

import Backend.IoT.User.User;
import jakarta.persistence.*;
import lombok.Data;


@Data
@Entity
@Table(name = "devices")
public class Device {
    @Id
    @SequenceGenerator(
            name = "device_sequence",
            sequenceName = "device_sequence",
            allocationSize = 1)
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "device_sequence"
    )
    private Long id;
    private String type ;
    private String model ;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    // Other attributes, constructors, getters, and setters
}