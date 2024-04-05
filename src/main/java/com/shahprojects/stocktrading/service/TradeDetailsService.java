package com.shahprojects.stocktrading.service;

import com.shahprojects.stocktrading.entity.TradeDetails;
import com.shahprojects.stocktrading.payload.TradeDetailsDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface TradeDetailsService {
    void createTradeDetails(TradeDetailsDTO tradeDetailsDTO);
    List<TradeDetailsDTO> getAllTradeDetails();
    TradeDetailsDTO getTradeDetailsById(Long id);
    void editTradeById(TradeDetailsDTO tradeDetailsDTO, Long id);
    void deleteTradeDetails(Long id);
}
