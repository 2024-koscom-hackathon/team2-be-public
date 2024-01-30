package koscom.team2.team2be.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import koscom.team2.team2be.dto.StockInfoRes;
import koscom.team2.team2be.dto.StockMarketRes;
import koscom.team2.team2be.service.StockInfoService;
import koscom.team2.team2be.service.StockMarketService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api")
//@CrossOrigin(origins = "http://221.168.32.94/, http://localhost:3000/")
@CrossOrigin(origins = "*", allowedHeaders = "*")
@Tag(name = "Stock Market", description = "주가 시세 정보 API")
public class StockMarketController {

    private final StockMarketService stockMarketService;

    @Operation(summary = "종목별 시세 정보", description = "종목별 5년치 시세 데이터(일별)")
    @ApiResponses(value = { @ApiResponse(responseCode = "200", description = "successful") })
    @GetMapping("/stock-market")
    public ResponseEntity<?> allStockList(@RequestParam(value = "s_code", required = true) @Parameter(name = "s_code", description = "종목 코드", example = "005930") String sCode){
        List<StockMarketRes> stockMarketRes = stockMarketService.getStockMarketBySCode(sCode);
        return new ResponseEntity<>(stockMarketRes, HttpStatus.OK);
    }

    @Operation(summary = "종목 기간별 시세 정보", description = "기간 설정을 통한 종목별 시세 데이터(일별)")
    @ApiResponses(value = { @ApiResponse(responseCode = "200", description = "successful") })
    @GetMapping("/stock-market/period")
    public ResponseEntity<?> stockListByDate(@RequestParam(value = "s_code", required = true) @Parameter(name = "s_code", description = "종목 코드", example = "005930") String sCode,
                                             @RequestParam(value = "start_date", required = true) @Parameter(name = "start_date", description = "시작일", example = "2024-01-02") String startDate,
                                             @RequestParam(value = "end_date", required = true) @Parameter(name = "end_date", description = "종료일", example = "2024-01-31") String endDate){
        List<StockMarketRes> stockMarketRes = stockMarketService.getStockMarketBySCodeAndDate(sCode, startDate, endDate);
        return new ResponseEntity<>(stockMarketRes, HttpStatus.OK);
    }

}
