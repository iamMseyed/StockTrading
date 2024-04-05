package com.shahprojects.stocktrading.service.impl;

import com.shahprojects.stocktrading.entity.OrderMaster;
import com.shahprojects.stocktrading.entity.TradeDetails;
import com.shahprojects.stocktrading.exception.ResourceNotFoundException;
import com.shahprojects.stocktrading.payload.OrderMasterDTO;
import com.shahprojects.stocktrading.repository.OrderMasterRepository;
import com.shahprojects.stocktrading.repository.TradeDetailsRepository;
import com.shahprojects.stocktrading.service.OrderMasterService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
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

        OrderMaster orderMaster=modelMapper.map(orderMasterDTO, OrderMaster.class);
        orderMaster.setTradeDetails(tradeDetail); //dto has id, entity has object, so modelMaper fails there, we need to manually add here

       orderMasterRepository.save(orderMaster);
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
        return modelMapper.map(orderMaster,OrderMasterDTO.class);
    }

    @Override
    public void editOrderById(OrderMasterDTO orderMasterDTO, Long id) {
        OrderMaster orderMaster = orderMasterRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("No order details found with the id: " + id)
        );
        if(orderMasterDTO.getStatus()!=null)
            orderMaster.setStatus(orderMasterDTO.getStatus());
        orderMasterRepository.save(orderMaster);
    }
    @Override
    public void deleteOrderMaster(Long order_id){
        orderMasterRepository.findById(order_id).orElseThrow(
                () -> new ResourceNotFoundException("No order found with the id: " + order_id)
        );
        orderMasterRepository.deleteById(order_id);
    }
}