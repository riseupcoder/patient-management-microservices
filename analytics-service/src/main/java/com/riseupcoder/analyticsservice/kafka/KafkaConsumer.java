package com.riseupcoder.analyticsservice.kafka;

import com.google.protobuf.InvalidProtocolBufferException;
import com.riseupcoder.patientservice.events.PatientEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumer {

    private static final Logger log = LoggerFactory.getLogger(KafkaConsumer.class);

    @KafkaListener(topics = "patient", groupId = "analytics-service")
    public void consumeEvent(byte[] event) {

        try {
            PatientEvent patientEvent = PatientEvent.parseFrom(event);

            log.info("Patient ID: {}", patientEvent.getPatientId());
            log.info("Patient Name: {}", patientEvent.getName());
            log.info("Patient Email: {}", patientEvent.getEmail());

        } catch (InvalidProtocolBufferException e) {
            log.error("Error deserializing event {}", e.getMessage());
        }

    }
}
