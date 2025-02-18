package com.zosh.controller;



import com.zosh.modal.Coins;
import com.zosh.modal.User;
import com.zosh.modal.Watchlist;
import com.zosh.service.CoinService;
import com.zosh.service.UserService;
import com.zosh.service.WatchListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/watchlist")
public class WatchListController {
    @Autowired
    private  WatchListService watchlistService;
    @Autowired
    private  UserService userService;

    @Autowired
    private CoinService coinService;



    @GetMapping("/user")
    public ResponseEntity<Watchlist> getUserWatchlist(
            @RequestHeader("Authorization") String jwt) throws Exception {

        User user=userService.findUserProfileByJwt(jwt);
        Watchlist watchlist = watchlistService.findUserWatchList(user.getId());
        return ResponseEntity.ok(watchlist);

    }



    @GetMapping("/{watchlistId}")
    public ResponseEntity<Watchlist> getWatchlistById(
            @PathVariable Long watchlistId) throws Exception {

        Watchlist watchlist = watchlistService.findById(watchlistId);
        return ResponseEntity.ok(watchlist);

    }

    @PatchMapping("/add/coin/{coinId}")
    public ResponseEntity<Coins> addItemToWatchlist(
            @RequestHeader("Authorization") String jwt,
            @PathVariable String coinId) throws Exception {


        User user=userService.findUserProfileByJwt(jwt);
        System.out.println("Looking for coin with ID: " + coinId);

        Coins coin=coinService.findById(coinId);
        Coins addedCoin = watchlistService.addItemToWatchList(coin, user);
        return ResponseEntity.ok(addedCoin);

    }
}
