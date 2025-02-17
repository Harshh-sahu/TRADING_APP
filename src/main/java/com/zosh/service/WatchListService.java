package com.zosh.service;

import com.zosh.modal.Coins;
import com.zosh.modal.User;
import com.zosh.modal.Watchlist;

public interface WatchListService {

    Watchlist findUserWatchList(Long userId) throws Exception;

    Watchlist createWatchList(User user);

    Watchlist findById(Long id) throws Exception;

    Coins addItemToWatchList(Coins coins,User user) throws Exception;





}
