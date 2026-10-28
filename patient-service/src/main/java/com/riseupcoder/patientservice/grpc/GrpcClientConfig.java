package com.riseupcoder.patientservice.grpc;

import com.riseupcoder.billing.BillingServiceGrpc;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.grpc.client.GrpcChannelFactory;

@Configuration
public class GrpcClientConfig {

    @Bean
    BillingServiceGrpc.BillingServiceBlockingV2Stub billingStub(GrpcChannelFactory channels) {
        return BillingServiceGrpc.newBlockingV2Stub(channels.createChannel("billing"));
    }
}
