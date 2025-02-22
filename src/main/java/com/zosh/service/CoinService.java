package com.zosh.service;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.zosh.modal.*;

import java.util.List;

public interface CoinService {
    List<Coins> getCoinList(int page) throws Exception;
    String getMarketChart(String coinId,int days) throws Exception;
    String getCoinDetails(String coinId) throws Exception;


    Coins findById(String coinId) throws Exception;

    String searchCoin(String keyword) throws Exception;

    String getTop50CoinsByMarketCapRank() throws Exception;

    String getTreadingCoins() throws Exception;
}
