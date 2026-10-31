package com.riseupcoder.patientservice.kafka;

import com.riseupcoder.patientservice.events.PatientEvent;
import com.riseupcoder.patientservice.model.Patient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaProducer {

    private static final Logger log = LoggerFactory.getLogger(KafkaProducer.class);
    private final KafkaTemplate<String, byte[]> kafkaTemplate;

    public KafkaProducer(KafkaTemplate<String, byte[]> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendEvent(Patient patient) {

        PatientEvent event = PatientEvent.newBuilder()
                                         .setPatientId(patient.getId().toString())
                                         .setName(patient.getName())
                                         .setEmail(patient.getEmail())
                                         .setEventType("PATIENT_CREATED")
                                         .build();

        try {
            kafkaTemplate
                    .send("patient", event.getPatientId(), event.toByteArray())

                    .whenComplete((result, exception) -> {
                        if (exception == null)
                            log.info("PatientCreatedEvent sent successfully");
                        else
                            log.error("Failed to send patient event", exception);
                    });

        } catch (Exception e) {
            log.error("Error sending PatientCreatedEvent {}", event);
        }
    }
}
