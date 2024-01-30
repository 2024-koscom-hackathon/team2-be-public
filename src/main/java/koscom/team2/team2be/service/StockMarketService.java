package koscom.team2.team2be.service;

import koscom.team2.team2be.dto.StockMarketRes;
import koscom.team2.team2be.entity.StockMarket;
import koscom.team2.team2be.repository.StockMarketRepository;
import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
@Slf4j
public class StockMarketService {

    private final StockMarketRepository stockMarketRepository;

    public List<StockMarketRes> getStockMarketBySCode(String sCode){
        List<StockMarket> stockMarkets = stockMarketRepository.findAllBySCode(sCode);
        List<StockMarketRes> stockMarketRes = stockMarkets.stream().map(
                stockMarket -> StockMarketRes.builder().date(stockMarket.getDate())
                        .openingPrice(stockMarket.getOpeningPrice())
                        .closingPrice(stockMarket.getClosingPrice())
                        .maxPrice(stockMarket.getMaxPrice())
                        .minPrice(stockMarket.getMinPrice())
                        .build()).toList();

        return stockMarketRes;
    }

    public List<StockMarketRes> getStockMarketBySCodeAndDate(String sCode, String startDate, String endDate){
        List<StockMarket> stockMarkets = stockMarketRepository.findAllBySCodeAndDateBetween(sCode, startDate, endDate);
        List<StockMarketRes> stockMarketRes = stockMarkets.stream().map(
                stockMarket -> StockMarketRes.builder().date(stockMarket.getDate())
                        .openingPrice(stockMarket.getOpeningPrice())
                        .closingPrice(stockMarket.getClosingPrice())
                        .maxPrice(stockMarket.getMaxPrice())
                        .minPrice(stockMarket.getMinPrice())
                        .build()).toList();
        return stockMarketRes;
    }
}
