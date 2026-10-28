package com.riseupcoder.patientservice.grpc;

import com.riseupcoder.billing.BillingRequest;
import com.riseupcoder.billing.BillingResponse;
import com.riseupcoder.billing.BillingServiceGrpc;
import io.grpc.StatusException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class BillingServiceGrpcClient {

    private static final Logger log = LoggerFactory.getLogger(BillingServiceGrpcClient.class);
    private final BillingServiceGrpc.BillingServiceBlockingV2Stub stub;

    public BillingServiceGrpcClient(BillingServiceGrpc.BillingServiceBlockingV2Stub stub) {
        this.stub = stub;
    }

    public BillingResponse createBillingAccount(String patientId, String name, String email) {

        BillingRequest billingRequest = BillingRequest.newBuilder()
                                                      .setPatientId(patientId)
                                                      .setName(name)
                                                      .setEmail(email)
                                                      .build();
        try {
            BillingResponse response = stub
                    .withDeadlineAfter(3, TimeUnit.SECONDS)
                    .createBillingAccount(billingRequest);

            log.info("Billing account created successfully.");
            log.info("Received response from gRPC BillingService: {}", response);

            return response;

        } catch (StatusException ex) {
            log.error("gRPC call to BillingService failed: {}", ex.getMessage());

            throw new RuntimeException("error");
        }
    }
}
