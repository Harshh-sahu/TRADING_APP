package com.zosh.repository;

import com.zosh.modal.Order;
import com.zosh.modal.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order,Long> {

    List<Order> findByUserId(Long userId);

    Long user(User user);
}
