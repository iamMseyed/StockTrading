package com.shahprojects.stocktrading.controller;

import com.shahprojects.stocktrading.payload.TradeDetailsDTO;
import com.shahprojects.stocktrading.service.TradeDetailsService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tradeDetails/")
public class TradeDetailsController {

    private final TradeDetailsService tradeDetailsService;
    public TradeDetailsController(TradeDetailsService tradeDetailsService){
        this.tradeDetailsService= tradeDetailsService;
    }

    @PostMapping("addTrade")
    public ResponseEntity<String> addTradeDetails(@RequestBody TradeDetailsDTO tradeDetailsDTO){
        tradeDetailsService.createTradeDetails(tradeDetailsDTO);
        return new ResponseEntity<>("Trade added successfully!",HttpStatus.CREATED);
    }

    @GetMapping("getTrade")
    public ResponseEntity<?> getAllTradeDetails(){
        List<TradeDetailsDTO> allTradeDetails = tradeDetailsService.getAllTradeDetails();
        if(allTradeDetails != null && !allTradeDetails.isEmpty()){
            return new ResponseEntity<>(allTradeDetails,HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("getTradeById/{id}")
    public ResponseEntity<TradeDetailsDTO> getTradeDetailsById(@PathVariable Long id){
        TradeDetailsDTO tradeDetailsDTO = tradeDetailsService.getTradeDetailsById(id);
           return new ResponseEntity<>(tradeDetailsDTO, HttpStatus.OK);
    }

    @PutMapping("editTradeById/{id}")
    public ResponseEntity<String> editTradeById(@RequestBody TradeDetailsDTO tradeDetailsDTO, @PathVariable Long id){
        tradeDetailsService.editTradeById(tradeDetailsDTO, id);
        return new ResponseEntity<>("Trade details updated!",HttpStatus.ACCEPTED);
    }

    @DeleteMapping("deleteTradeById/{id}")
    public ResponseEntity<String> deleteTradeDetails(@PathVariable Long id){
        tradeDetailsService.deleteTradeDetails(id);
       return new ResponseEntity<>("Trade with id: "+id+" and its associated order(s) if any, deleted successfully!", HttpStatus.OK);
    }
}