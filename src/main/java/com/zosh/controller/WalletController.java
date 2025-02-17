package com.zosh.controller;


import com.zosh.modal.Order;
import com.zosh.modal.PaymentOrder;
import com.zosh.modal.User;
import com.zosh.modal.Wallet.Wallet;
import com.zosh.modal.WalletTransaction;
import com.zosh.response.PaymentResponse;
import com.zosh.service.OrderService;
import com.zosh.service.PaymentService;
import com.zosh.service.UserService;
import com.zosh.service.WallerService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/wallet")
public class WalletController {

    @Autowired
    private WallerService wallerService;

    @Autowired
    private OrderService  orderService;
    @Autowired
    private UserService userService;

    @Autowired
    private PaymentService paymentService;

    @GetMapping("/api/wallet")
    public ResponseEntity<Wallet> getUserWallet(@RequestHeader("Authorization") String jwt) throws Exception {
        User user = userService.findUserProfileByJwt(jwt);
        Wallet wallet = wallerService.getUserWallet(user);


        return new ResponseEntity<>(wallet, HttpStatus.ACCEPTED);
    }



    @GetMapping("/api/wallet")
    public ResponseEntity<Wallet> l(@RequestHeader("Authorization") String jwt) throws Exception {
        User user = userService.findUserProfileByJwt(jwt);
        Wallet wallet = wallerService.getUserWallet(user);


        return new ResponseEntity<>(wallet, HttpStatus.ACCEPTED);
    }


    @PutMapping("/api/wallet/{walletId}/transfer")
    public ResponseEntity<Wallet> walletToWalletTransfer(@RequestHeader("Authorization") String jwt, @PathVariable Long walletId
    , @RequestBody WalletTransaction req) throws Exception {
User senderUser  = userService.findUserProfileByJwt(jwt);
Wallet receiverWallet = wallerService.findWalletById(walletId);
Wallet wallet = wallerService.walletToWalletTransfer(senderUser,receiverWallet,req.getAmount());


        return  new ResponseEntity<>(wallet, HttpStatus.ACCEPTED);

    }


    @PutMapping("/api/wallet/order/{orderId}/pay")
    public ResponseEntity<Wallet> payOrderPayment(@RequestHeader("Authorization") String jwt, @PathVariable Long orderId
            ) throws Exception {
        User user  = userService.findUserProfileByJwt(jwt);
        Order order = orderService.getOrderById(orderId);
        Wallet wallet = wallerService.payOrderPayment(order,user);

        return  new ResponseEntity<>(wallet, HttpStatus.ACCEPTED);

    }
    @PutMapping("/api/wallet/deposite")
    public ResponseEntity<Wallet> addMoneyToWallet(@RequestHeader("Authorization") String jwt,
                                               @RequestParam(name = "order_id")Long order_id,
                                                   @RequestParam(name = "payment_id")String paymentId

    ) throws Exception {
        User user  = userService.findUserProfileByJwt(jwt);
        Wallet wallet = wallerService.getUserWallet(user);

        PaymentOrder order = paymentService.getPaymentOrderById(order_id);


        Boolean status = paymentService.ProccedPaymentOrder(order,paymentId);
        PaymentResponse res = new PaymentResponse();
        res.setPayment_url("deposite success");
        if(status){
            wallet = wallerService.addBalance(wallet,order.getAmount());
        }
        return  new ResponseEntity<>(wallet, HttpStatus.ACCEPTED);

    }
}
