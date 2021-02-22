package io.github.jaksatomovic.kafka.consumer.kafka;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * @author Jakša Tomović
 * @since 1.0
 */
@Service
public class RandomNumberConsumer
{

    @Value ("${message.processing.time}")
    private Long processingTime;

//    @KafkaListener (topics = "random-number")
//    public void consume(String message) throws UnknownHostException
//    {
//        String hostName = InetAddress.getLocalHost().getHostName();
//        System.out.println(String.format("%s consumed %s", hostName, message));
//    }

    @KafkaListener(topics = "random-number")
    public void consume(String message) throws UnknownHostException, InterruptedException {
        String hostName = InetAddress.getLocalHost().getHostName();
        System.out.println(String.format("%s consumed %s", hostName, message));
        Thread.sleep(processingTime);
    }
}
