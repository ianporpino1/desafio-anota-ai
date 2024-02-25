package com.desafioanotaai.services;

import com.amazonaws.services.sns.AmazonSNS;
import com.amazonaws.services.sns.model.Topic;
import com.desafioanotaai.dtos.MessageDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class AwsSnsService {
    @Autowired
    private AmazonSNS snsClient;

    @Autowired
    @Qualifier("catalogEventsTopic")
    private Topic catalogTopic;


    public void publish(MessageDto messageDto){

        snsClient.publish(catalogTopic.getTopicArn(), messageDto.message());
        System.out.println(messageDto.message());
    }
}
