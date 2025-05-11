package com.app.BookingService.Feign;

import com.app.BookingService.DTO.PaymentRequest;
import com.app.BookingService.DTO.PaymentResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "payment-service", url = "http://localhost:8085")
public interface PaymentServiceClient {

    @PostMapping("/payments/process")
    PaymentResponse processPayment(@RequestBody PaymentRequest paymentRequest);

    @GetMapping("/payments/booking/{bookingId}")
    PaymentResponse getPaymentByBookingId(@PathVariable("bookingId") Long bookingId);
}
