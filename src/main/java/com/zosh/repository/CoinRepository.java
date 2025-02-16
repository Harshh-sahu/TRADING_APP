package com.zosh.repository;

import com.zosh.modal.Coins;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CoinRepository extends JpaRepository<Coins,String> {
}
