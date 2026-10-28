package com.riseupcoder.billingservice.grpc;

import com.riseupcoder.billing.BillingRequest;
import com.riseupcoder.billing.BillingResponse;
import com.riseupcoder.billing.BillingServiceGrpc;
import io.grpc.stub.StreamObserver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.grpc.server.service.GrpcService;

@GrpcService
public class BillingGrpcService extends BillingServiceGrpc.BillingServiceImplBase {

    private static final Logger log = LoggerFactory.getLogger(BillingGrpcService.class);

    @Override
    public void createBillingAccount(BillingRequest billingRequest,
                                     StreamObserver<BillingResponse> responseObserver) {

        log.info("createBillingAccount request received {}", billingRequest.toString());

        BillingResponse billingResponse = BillingResponse
                .newBuilder()
                .setAccountId("acc-123")
                .setStatus("ACTIVE")
                .build();

        responseObserver.onNext(billingResponse);
        responseObserver.onCompleted();
    }
}
