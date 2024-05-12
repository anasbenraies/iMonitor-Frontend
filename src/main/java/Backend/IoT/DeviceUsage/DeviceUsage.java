package Backend.IoT.DeviceUsage;

import Backend.IoT.Device.Device;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "device_usages")
public class DeviceUsage {
    @Id
    @SequenceGenerator(
            name = "usage_sequence",
            sequenceName = "usage_sequence",
            allocationSize = 1)
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "usage_sequence"
    )
    private Long id;
    @Column(name = "usage_date")
    private LocalDate usageDate;

    @Column(name = "duration_in_minutes")
    private int durationInMinutes;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "device_id", nullable = false)
    private Device device;

    public DeviceUsage(LocalDate usageDate, int durationInMinutes, Long deviceId) {
        this.usageDate = usageDate;
        this.durationInMinutes = durationInMinutes;
        this.device = new Device();
        this.device.setId(deviceId);
    }
    // Other attributes, constructors, getters, and setters
}