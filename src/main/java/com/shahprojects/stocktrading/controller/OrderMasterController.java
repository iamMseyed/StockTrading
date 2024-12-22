package com.shahprojects.stocktrading.controller;

import com.shahprojects.stocktrading.payload.OrderMasterDTO;
import com.shahprojects.stocktrading.service.OrderMasterService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/order-details/")
public class OrderMasterController {

    private final OrderMasterService orderMasterService;
    OrderMasterController(OrderMasterService orderMasterService){
        this.orderMasterService = orderMasterService;
    }

    @PostMapping("add-order")
    public ResponseEntity<String> addOrderMaster(@RequestBody OrderMasterDTO orderMasterDTO, @RequestParam Long tradeId){
           orderMasterService.createOrderMaster(orderMasterDTO, tradeId);
           return new ResponseEntity<>("Order added successfully!", HttpStatus.CREATED);
    }

    @GetMapping("get-order")
    public ResponseEntity<?> getAllOrderMaster(){
        List<OrderMasterDTO> allOrderMasterDTO = orderMasterService.getAllOrderMaster();
        if(!allOrderMasterDTO.isEmpty())
            return new ResponseEntity<>(allOrderMasterDTO,HttpStatus.OK);
        else
            return new ResponseEntity<>("No Order Master stored yet!", HttpStatus.NOT_FOUND);
    }

    @GetMapping("get-order-by-id/{id}")
    public ResponseEntity<OrderMasterDTO> getOrderMasteryById(@PathVariable Long id){
        OrderMasterDTO orderMasterById = orderMasterService.getOrderMasterById(id);
        return new ResponseEntity<>(orderMasterById, HttpStatus.OK);
    }
    @PutMapping("edit-order-by-id/{id}")
    public ResponseEntity<String> editTradeById(@RequestBody OrderMasterDTO orderMasterDTO, @PathVariable Long id){
        orderMasterService.editOrderById(orderMasterDTO, id);
        return new ResponseEntity<>("Order status updated successfully!", HttpStatus.ACCEPTED);
    }
    @DeleteMapping("delete-order-by-id/{id}")
    public ResponseEntity<String> deleteById(@PathVariable Long id){
        orderMasterService.deleteOrderMaster(id);
        return new ResponseEntity<>("Order deleted successfully with order id: "+id,HttpStatus.OK);
    }
}
