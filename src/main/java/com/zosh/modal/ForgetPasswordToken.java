package com.zosh.modal;

import com.zosh.domain.VerificationType;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class ForgetPasswordToken {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToOne
    private User user;


    private String otp;

    private VerificationType verificationType;

    private String sendTo;
}
