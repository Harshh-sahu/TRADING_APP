package com.zosh.service;

import com.zosh.domain.OrderStatus;
import com.zosh.domain.OrderType;
import com.zosh.modal.*;
import com.zosh.repository.OrderItemRepository;
import com.zosh.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import jakarta.transaction.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private AssetService assetService;
    @Autowired
    private OrderItemRepository orderItemRepository;
    @Autowired
    private OrderRepository orderRepository;

@Autowired
    private WallerService wallerService;
    @Override
    public Order createOrder(User user, OrderItem orderItem, OrderType orderType) {
     double price = orderItem.getCoin().getCurrentPrice()*orderItem.getQuantity();
     Order order = new Order();
     order.setUser(user);
     order.setOrderItem(orderItem);
     order.setOrderType(orderType);
     order.setPrice(BigDecimal.valueOf(price));

     order.setTimestamp(LocalDateTime.now());
     order.setStatus(OrderStatus.PENDING);



        return orderRepository.save(order);
    }

    @Override
    public Order getOrderById(Long orderId) throws Exception {
        return orderRepository.findById(orderId).orElseThrow(()-> new Exception("order not found"));

    }

    @Override
    public List<Order> getAllOrderOfUser(Long userId, OrderType orderType, String assetSymbol) {




        return orderRepository.findByUserId(userId);
    }

    private OrderItem createOrderItem(Coins coin,double quantity,double buyPrice,double sellPrice){
        OrderItem orderItem = new OrderItem();
        orderItem.setCoin(coin);
        orderItem.setQuantity(quantity);
        orderItem.setBuyPrice(buyPrice);
        orderItem.setSellPrice(sellPrice);
        return orderItemRepository.save(orderItem);
    }

@Transactional
    public Order buyAsset(Coins coins,double quantity,User user) throws Exception {
        if(quantity <0){
            throw new Exception("quantity should be > 0");

        }
        
        double buyPrice = coins.getCurrentPrice();
        OrderItem orderItem = createOrderItem(coins,quantity,buyPrice,0);
        Order order = createOrder(user,orderItem,OrderType.BUY);
        orderItem.setOrder(order);
        wallerService.payOrderPayment(order,user);
        order.setStatus(OrderStatus.SUCCESS);
        order.setOrderType(OrderType.BUY);
          Order savedOrder = orderRepository.save(order);
          //create asset in wallet

    Asset oldAsset = assetService.findAssetByUserIdAndCoinId(order.getUser().getId(),order.getOrderItem().getCoin().getId());
    if(oldAsset==null){
        assetService.createAsset(user,orderItem.getCoin(),orderItem.getQuantity());

    }else {
        assetService.updateAsset(oldAsset.getId(),quantity);

    }

        return savedOrder;
    }

    @Transactional
    public Order sellAsset(Coins coins,double quantity,User user) throws Exception {
        if(quantity <0){
            throw new Exception("quantity should be > 0");

        }
        double sellPrice = coins.getCurrentPrice();

        Asset assetToSell = assetService.findAssetByUserIdAndCoinId(user.getId(),coins.getId());
        double buyPrice = assetToSell.getBuyPrice();

        if(assetToSell!=null) {

            OrderItem orderItem = createOrderItem(coins, quantity, buyPrice, sellPrice);


            Order order = createOrder(user, orderItem, OrderType.SELL);
            orderItem.setOrder(order);
            if (assetToSell.getQuantity() >= quantity) {
                order.setStatus(OrderStatus.SUCCESS);
                order.setOrderType(OrderType.SELL);
                Order savedOrder = orderRepository.save(order);
                wallerService.payOrderPayment(order, user);
                Asset updatedAsset = assetService.updateAsset(
                        assetToSell.getId(),-quantity
                );
                if (updatedAsset.getQuantity() * coins.getCurrentPrice() <= 1) {
                    assetService.deleteAsset(updatedAsset.getId());
                }
                return savedOrder;
            }

            throw new Exception("Unsufficient Quantity to Sell !!");

        }
        throw new Exception("Asset not found !!");

    }


@Transactional
    @Override
    public Order processOrder(Coins coin, double quantiy, OrderType orderType, User user) throws Exception {

     if(orderType.equals(OrderType.BUY)){
         return buyAsset(coin,quantiy,user);

     }else if(orderType.equals(OrderType.SELL)){
         return sellAsset(coin,quantiy,user);
     }


        throw new Exception("INVALID ORDER TYPE");
    }
}
