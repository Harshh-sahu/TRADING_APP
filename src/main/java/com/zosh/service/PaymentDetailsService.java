package com.zosh.service;

import com.zosh.modal.PaymentDetails;
import com.zosh.modal.User;

public interface PaymentDetailsService {


    public PaymentDetails addPaymentDetails(String accountNumber,
                                            String accountHolderName,
                                            String ifsc,
                                            String bankName,
                                            User user);

    public PaymentDetails getUserPaymentDetails(User user);


}
