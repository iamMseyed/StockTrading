package com.shahprojects.stocktrading.service.impl;

import com.shahprojects.stocktrading.entity.OrderMaster;
import com.shahprojects.stocktrading.entity.TradeDetails;
import com.shahprojects.stocktrading.exception.ResourceNotFoundException;
import com.shahprojects.stocktrading.payload.OrderMasterDTO;
import com.shahprojects.stocktrading.payload.TradeDetailsDTO;
import com.shahprojects.stocktrading.repository.OrderMasterRepository;
import com.shahprojects.stocktrading.repository.TradeDetailsRepository;
import com.shahprojects.stocktrading.service.OrderMasterService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class OrderMasterServiceImplementation implements OrderMasterService {
   private final OrderMasterRepository orderMasterRepository;
   private final ModelMapper modelMapper;
   private final TradeDetailsRepository tradeDetailsRepository;

   public OrderMasterServiceImplementation(OrderMasterRepository orderMasterRepository, ModelMapper modelMapper, TradeDetailsRepository tradeDetailsRepository){
       this.orderMasterRepository = orderMasterRepository;
       this.modelMapper = modelMapper;
       this.tradeDetailsRepository = tradeDetailsRepository;
   }
    @Override
    public void createOrderMaster(OrderMasterDTO orderMasterDTO,Long tradeId) {
        TradeDetails tradeDetail= tradeDetailsRepository.findById(tradeId).orElseThrow(
                () -> new ResourceNotFoundException("No Trade details found with the id: " + tradeId)
        );

       OrderMaster orderMaster= new OrderMaster();

       orderMaster.setStatus(orderMasterDTO.getStatus());
       orderMaster.setTradeDetails(tradeDetail);
       orderMasterRepository.save(orderMaster);
    }

    @Override
    public OrderMasterDTO editOrderById(OrderMasterDTO orderMasterDTO, Long id) {
        orderMasterRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("No order details found with the id: " +id)
        );
        OrderMaster orderMaster= new OrderMaster();
        orderMaster.setStatus(orderMasterDTO.getStatus());
        OrderMaster saved = orderMasterRepository.save(orderMaster);

        return null;
    }

    @Override
    public List<OrderMasterDTO> getAllOrderMaster(){
        OrderMasterDTO orderMasterDTO = new OrderMasterDTO();

        List<OrderMaster> allOrders = orderMasterRepository.findAll();

        if(!allOrders.isEmpty()){
            for(OrderMaster order:allOrders){
                Long id = order.getTradeDetails().getTradeDetailsId(); //get id trade details
                orderMasterDTO.setTradeDetailsId(id);
            }

            return allOrders.stream()
                    .map(singleOrder->modelMapper.map(singleOrder,OrderMasterDTO.class)) //using modelMapper() to convert entity to dto
                    .collect(Collectors.toList());
        }else{
            throw  new ResourceNotFoundException("Order history is empty!");
        }
    }

    @Override
    public OrderMasterDTO getOrderMasterById(Long order_id){
        OrderMaster orderMaster = orderMasterRepository.findById(order_id).orElseThrow(
                () -> new ResourceNotFoundException("No order found with the id: " + order_id)
        );

        OrderMasterDTO orderMasterDTO = new OrderMasterDTO();

        modelMapper.map(orderMasterDTO,orderMaster);
        OrderMaster savedOrder = orderMasterRepository.save(orderMaster);
        OrderMasterDTO orderMasterDTO1 = modelMapper.map(savedOrder, OrderMasterDTO.class);
        return orderMasterDTO1 ;

    }
    @Override
    public void deleteOrderMaster(Long order_id){
        orderMasterRepository.findById(order_id).orElseThrow(
                () -> new ResourceNotFoundException("No order found with the id: " + order_id)
        );
        orderMasterRepository.deleteById(order_id);
    }
}