package com.zosh.service;

import com.zosh.modal.Asset;
import com.zosh.modal.Coins;
import com.zosh.modal.User;

import java.util.List;

public interface AssetService {


    Asset createAsset(User user, Coins coin,double quantity);

    Asset getAssetById(Long assetId) throws Exception;

    Asset getAssetByUserIdAndId(Long userId,Long assetId);

    List<Asset>getUserAssets(Long userId);

    Asset updateAsset(Long assetId,double quantity) throws Exception;
    Asset findAssetByUserIdAndCoinId(Long userId,String coinId);

    void deleteAsset(Long assetId);






}
