package com.shahprojects.stocktrading.service;

import com.shahprojects.stocktrading.entity.OrderMaster;
import com.shahprojects.stocktrading.payload.OrderMasterDTO;
import org.hibernate.criterion.Order;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface OrderMasterService {
    void createOrderMaster(OrderMasterDTO orderMasterDTO, Long id);
    OrderMasterDTO getOrderMasterById(Long id);
    List<OrderMasterDTO> getAllOrderMaster();
    void editOrderById(OrderMasterDTO orderMasterDTO, Long id);
    void deleteOrderMaster(Long id);
}