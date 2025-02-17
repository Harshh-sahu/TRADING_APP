package com.zosh.service;

import com.razorpay.RazorpayException;
import com.stripe.exception.StripeException;
import com.zosh.domain.PaymentMethod;
import com.zosh.modal.PaymentOrder;
import com.zosh.modal.User;
import com.zosh.response.PaymentResponse;

public interface PaymentService {


    PaymentOrder createOrder(User user, Long amount, PaymentMethod paymentMethod);

    PaymentOrder getPaymentOrderById(Long id) throws Exception;

    Boolean ProccedPaymentOrder(PaymentOrder paymentOrder,String paymentId) throws RazorpayException;

    PaymentResponse createRazorpayPaymentLink(User user, Long amount, Long orderId) throws Exception;

    PaymentResponse createStripePaymentLink(User user, Long amount,Long orderId) throws StripeException;



}
