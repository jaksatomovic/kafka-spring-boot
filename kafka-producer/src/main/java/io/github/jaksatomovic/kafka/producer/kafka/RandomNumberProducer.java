package io.github.jaksatomovic.kafka.producer.kafka;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.concurrent.ThreadLocalRandom;

/**
 * @author Jakša Tomović
 * @since 1.0
 */
@Service
public class RandomNumberProducer
{
    private static final int MIN = 10;
    private static final int MAX = 100_000;
    private int counter = 1;

    private final KafkaTemplate<String, String> kafkaTemplate;

    public RandomNumberProducer(final KafkaTemplate<String, String> kafkaTemplate)
    {
        this.kafkaTemplate = kafkaTemplate;
    }

//    @Scheduled (fixedRate = 1000)
//    public void produce() throws UnknownHostException
//    {
//        int random = ThreadLocalRandom.current().nextInt(MIN, MAX);
//        this.kafkaTemplate.sendDefault(String.valueOf(random));
//        //just for logging
//        String hostName = InetAddress.getLocalHost().getHostName();
//        System.out.println(String.format("%s produced %d", hostName, random));
//    }

    @Scheduled (fixedRate = 1000)
    public void produce() throws UnknownHostException
    {
        int random = counter++;
        this.kafkaTemplate.sendDefault(String.valueOf(random));
        //just for logging
        String hostName = InetAddress.getLocalHost().getHostName();
        System.out.println(String.format("%s produced %d", hostName, random));
    }
}
