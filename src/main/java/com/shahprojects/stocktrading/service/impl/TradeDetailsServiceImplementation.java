package com.shahprojects.stocktrading.service.impl;

import com.shahprojects.stocktrading.entity.TradeDetails;
import com.shahprojects.stocktrading.exception.ResourceNotFoundException;
import com.shahprojects.stocktrading.payload.TradeDetailsDTO;
import com.shahprojects.stocktrading.repository.TradeDetailsRepository;
import com.shahprojects.stocktrading.service.TradeDetailsService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TradeDetailsServiceImplementation implements TradeDetailsService {
    private final TradeDetailsRepository tradeDetailsRepository;
    private final ModelMapper modelMapper;

    TradeDetailsServiceImplementation(TradeDetailsRepository tradeDetailsRepository, ModelMapper modelMapper){
        this.tradeDetailsRepository = tradeDetailsRepository;
        this.modelMapper = modelMapper;
    }
@Override
    public void createTradeDetails(TradeDetailsDTO tradeDetailsDTO) {
        TradeDetails tradeDetails= modelMapper.map(tradeDetailsDTO, TradeDetails.class);
        tradeDetails.setTradeDateTime(LocalDateTime.now()); //not setting dateTime via postman

        tradeDetailsRepository.save(tradeDetails);
    }

    @Override
    public void editTradeById(TradeDetailsDTO tradeDetailsDTO, Long id) {
        TradeDetails tradeDetails = tradeDetailsRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("No Trade found with the id: " + id)
        );
        if(tradeDetailsDTO.getStockName()!=null)
            tradeDetails.setStockName(tradeDetailsDTO.getStockName());
        if(tradeDetailsDTO.getListingPrice()!=null)
            tradeDetails.setListingPrice(tradeDetailsDTO.getListingPrice());
        if(tradeDetailsDTO.getQuantity()!=null)
            tradeDetails.setQuantity(tradeDetailsDTO.getQuantity());
        if(tradeDetailsDTO.getType()!=null)
            tradeDetails.setType(tradeDetailsDTO.getType());
        if(tradeDetailsDTO.getPricePerUnit()!=null)
            tradeDetails.setPricePerUnit(tradeDetailsDTO.getPricePerUnit());
        tradeDetails.setTradeDateTime(LocalDateTime.now()) ;

        tradeDetailsRepository.save(tradeDetails);
    }

    @Override
    public TradeDetailsDTO getTradeDetailsById(Long id){
        TradeDetails tradeDetails = tradeDetailsRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("No Trade found with the id: " + id)
        );
        TradeDetailsDTO tradeDetailsDTO = new TradeDetailsDTO();

        tradeDetailsDTO.setTradeDetailsId(String.valueOf(tradeDetails.getTradeDetailsId()));
        tradeDetailsDTO.setStockName(tradeDetails.getStockName());
        tradeDetailsDTO.setListingPrice(tradeDetails.getListingPrice());
        tradeDetailsDTO.setQuantity(tradeDetails.getQuantity());
        tradeDetailsDTO.setType(tradeDetails.getType());
        tradeDetailsDTO.setPricePerUnit(tradeDetails.getPricePerUnit());
        tradeDetailsDTO.setTradeDateTime(tradeDetails.getTradeDateTime());

        return tradeDetailsDTO ;
    }

    @Override
    public List<TradeDetailsDTO> getAllTradeDetails(){
        List<TradeDetails> tradeDetails = tradeDetailsRepository.findAll();
        if(!tradeDetails.isEmpty()){
            List<TradeDetailsDTO> newTradeDetailsDTOS= tradeDetails.stream().map
                            (individualTradeItem->modelMapper.map(individualTradeItem,TradeDetailsDTO.class)) //using modelMapper() to convert entity to dto
                    .collect(Collectors.toList());
            return newTradeDetailsDTOS;
        }
        else{
           throw new ResourceNotFoundException("Trade history is empty!");
        }
    }
    @Override
    public void deleteTradeDetails(Long id){
        tradeDetailsRepository.findById(id).orElseThrow(
                    () -> new ResourceNotFoundException("No Trade found with the id: " + id)
          );
        tradeDetailsRepository.deleteById(id);
    }
}