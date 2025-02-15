package com.zosh.repository;

import com.zosh.modal.ForgetPasswordToken;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ForgetPasswordRepository extends JpaRepository<ForgetPasswordToken,Long> {


    ForgetPasswordToken findByUserId(Long userId);


}
