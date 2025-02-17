package com.zosh.service;

import com.zosh.modal.Coins;
import com.zosh.modal.User;
import com.zosh.modal.Watchlist;
import com.zosh.repository.WatchListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class WatchListServiceImpl implements  WatchListService{

    @Autowired
    private WatchListRepository watchListRepository;




    @Override
    public Watchlist findUserWatchList(Long userId) throws Exception {
      Watchlist watchlist = watchListRepository.findByUserId(userId);
      if(watchlist==null){
          throw new Exception("WATCHLIST NOT FOUND!!");
      }

        return watchlist;
    }

    @Override
    public Watchlist createWatchList(User user) {
        Watchlist watchlist = new Watchlist();
        watchlist.setUser(user);
        return watchListRepository.save(watchlist);
    }

    @Override
    public Watchlist findById(Long id) throws Exception {
        Optional<Watchlist> watchlistOptional = watchListRepository.findById(id);
        if(watchlistOptional.isEmpty()){
            throw new Exception("WatchList Not fOund!!");
        }

        return watchlistOptional.get();
    }

    @Override
    public Coins addItemToWatchList(Coins coins, User user) throws Exception {

       Watchlist watchlist = findUserWatchList(user.getId());

if(watchlist.getCoin().contains(coins)){
    watchlist.getCoin().remove(coins);


}else {
    watchlist.getCoin().add(coins);
}
         watchListRepository.save(watchlist);
return coins;
    }
}
