package com.zosh.repository;

import com.zosh.modal.Watchlist;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WatchListRepository extends JpaRepository<Watchlist,Long> {



    Watchlist findByUserId(Long userId);
}
