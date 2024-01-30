package koscom.team2.team2be.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import koscom.team2.team2be.dto.StockInfoRes;
import koscom.team2.team2be.service.StockInfoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api")
//@CrossOrigin(origins = "http://221.168.32.94/, http://localhost:3000/")
@CrossOrigin(origins = "*", allowedHeaders = "*")
@Tag(name = "Stock Information", description = "종목 정보 API")
public class StockInfoController {
    private final StockInfoService stockInfoService;

    @Operation(summary = "종목 정보 리스트", description = "10개 종목에 대한 종목명, 종목 코드")
    @ApiResponses(value = { @ApiResponse(responseCode = "200", description = "successful") })
    @GetMapping("/stock-info/list")
    public ResponseEntity<?> allStockList(){
        List<StockInfoRes> stockInfoList = stockInfoService.getAllStockInfo();
        return new ResponseEntity<>(stockInfoList, HttpStatus.OK);
    }

}
