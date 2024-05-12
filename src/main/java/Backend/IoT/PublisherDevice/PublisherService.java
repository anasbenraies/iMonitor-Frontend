package Backend.IoT.PublisherDevice;

import org.eclipse.paho.client.mqttv3.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.logging.Level;
import java.util.logging.Logger;

@Service
public class PublisherService {

    @Value("${mqtt.broker.url}")
    private String brokerUrl;

    @Value("${mqtt.username}")
    private String username;

    @Value("${mqtt.password}")
    private String password;

    private static final Logger LOGGER = Logger.getLogger(PublisherService.class.getName());

    public void publishMessage(String topic, String messageContent) {
        try {
            MqttClient mqttClient = new MqttClient(brokerUrl, MqttClient.generateClientId());
            MqttConnectOptions connOpts = new MqttConnectOptions();
            connOpts.setUserName(username);
            connOpts.setPassword(password.toCharArray());
            mqttClient.connect(connOpts);
            if(mqttClient.isConnected()){
                System.out.println("IoT device has connected to the Broker");
            }

            MqttMessage message = new MqttMessage(messageContent.getBytes());
            message.setQos(1); // Set Quality of Service (QoS)
            mqttClient.publish(topic, message);

            mqttClient.disconnect();
            mqttClient.close();
        } catch (MqttException e) {
            LOGGER.log(Level.SEVERE, "An error occurred while publishing message", e);
            // Implement retry logic or other error-handling mechanisms here
        }
    }
}
