package com.zosh.repository;

import com.zosh.modal.Asset;
import com.zosh.modal.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AssetRepository extends JpaRepository<Asset ,Long> {

    List<Asset> findByUserId(Long userId);

    Asset findByUserIdAndCoinId(Long userId,String coinId);


    Long user(User user);
}
