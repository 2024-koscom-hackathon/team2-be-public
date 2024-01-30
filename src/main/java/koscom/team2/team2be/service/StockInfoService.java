package koscom.team2.team2be.service;

import koscom.team2.team2be.dto.StockInfoRes;
import koscom.team2.team2be.entity.StockInfo;
import koscom.team2.team2be.repository.StockInfoRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
@Slf4j
public class StockInfoService {
    private final StockInfoRepository stockInfoRepository;

    public List<StockInfoRes> getAllStockInfo(){
        List<StockInfo> stockInfoList = stockInfoRepository.findAll();
        List<StockInfoRes> stockInfoRes = stockInfoList.stream().map(
                stockInfo -> StockInfoRes.builder().sCode(stockInfo.getSCode())
                        .sName(stockInfo.getSName()).build()).toList();
        return stockInfoRes;
    }
}