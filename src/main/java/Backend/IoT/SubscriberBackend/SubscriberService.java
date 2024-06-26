package Backend.IoT.SubscriberBackend;

import Backend.IoT.DeviceUsage.DeviceUsage;
import Backend.IoT.DeviceUsage.DeviceUsageRepository;
import org.eclipse.paho.client.mqttv3.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.logging.Level;
import java.util.logging.Logger;

@Service
public class SubscriberService {

    @Value("${mqtt.broker.url}")
    private String brokerUrl;

    @Value("${mqtt.username}")
    private String username;

    @Value("${mqtt.password}")
    private String password;

    @Autowired
    DeviceUsageRepository deviceUsageRepository;


    private static final Logger LOGGER = Logger.getLogger(SubscriberService.class.getName());

    public void subscribe(String topic) {
        try {
            MqttClient mqttClient = new MqttClient(brokerUrl, MqttClient.generateClientId());
            MqttConnectOptions connOpts = new MqttConnectOptions();
            connOpts.setUserName(username);
            connOpts.setPassword(password.toCharArray());
            mqttClient.connect(connOpts);
            if(mqttClient.isConnected()){
                System.out.println("backend(subscriber) has connected to the Broker");
            }

            mqttClient.setCallback(new MqttCallback() {
                @Override
                public void connectionLost(Throwable cause) {
                    LOGGER.log(Level.SEVERE, "Connection lost", cause);
                    // Implement reconnect logic here
                }

                @Override
                public void messageArrived(String topic, MqttMessage message) throws Exception {
                    String messageContent = new String(message.getPayload());
                    LOGGER.info("Message received on topic: " + topic + ", content:  " + messageContent);
                    String[] UsageInformation=messageContent.split("/");
                    DeviceUsage deviceUsage = new DeviceUsage(LocalDate.parse(UsageInformation[0]), Integer.parseInt(UsageInformation[1]),Long.parseLong(UsageInformation[2]));
                    System.out.println("==>"+deviceUsage);
                    deviceUsageRepository.save(deviceUsage);
                    System.out.println("DeviceUsage saved in Database .");
                    // Implement handling of received message here
                }

                @Override
                public void deliveryComplete(IMqttDeliveryToken token) {
                    // Not used for subscribers
                }
            });

            mqttClient.subscribe(topic);
        } catch (MqttException e) {
            LOGGER.log(Level.SEVERE, "An error occurred while subscribing to topic", e);
            // Implement retry logic or other error-handling mechanisms here
        }
    }
}
