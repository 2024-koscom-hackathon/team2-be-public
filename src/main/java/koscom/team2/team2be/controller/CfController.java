package koscom.team2.team2be.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import koscom.team2.team2be.dto.*;
import koscom.team2.team2be.service.FinancialInfoService;
import koscom.team2.team2be.service.MacroInfoService;
import koscom.team2.team2be.service.NewsService;
import koscom.team2.team2be.service.PatternListService;
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
@Tag(name = "Cf. Period", description = "비교 기간, 공시&매크로 정보, 뉴스 및 요약 API")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class CfController {

    private final NewsService newsService;
    private final FinancialInfoService financialInfoService;
    private final MacroInfoService macroInfoService;
    private final PatternListService patternListService;

    @Operation(summary = "비교 기간 데이터", description = "뉴스 정보(최대 10개) + 공시&매크로 정보 + 유사도 리스트 // *sales: 매출, cogs: 매출원가, gross_profit: 매출총이익, sga_expense: 판매관리비, op_profit: 영업 이익, net_income: 당기순이익, tci: 총포괄손익")
    @ApiResponses(value = { @ApiResponse(responseCode = "200", description = "successful") })
    @GetMapping("/cf-period")
    public ResponseEntity<?> cfPeriodList(@RequestParam(value = "s_code") @Parameter(name = "s_code", description = "종목 코드", example = "005930") String sCode,
                                           @RequestParam(value = "start_date") @Parameter(name = "start_date", description = "비교 기간 시작일(String)", example = "2017-11-02") String startDate,
                                           @RequestParam(value = "end_date") @Parameter(name = "end_date", description = "비교 기간 종료일(String)", example = "2017-11-08") String endDate){

        FinancialInfoRes financialInfoResPast = financialInfoService.getPastFinancialInfo(startDate);
        FinancialInfoRes financialInfoResCurrent = financialInfoService.getCurrentFinancialInfo(startDate);
        List<MacroInfoRes> macroInfoRes = macroInfoService.getMacroInfo(startDate);
        List<NewsRes> newsResList = newsService.getNews(sCode, startDate, endDate);

        CfRes cfRes = new CfRes();
        cfRes.setStartDate(startDate);
        cfRes.setEndDate(endDate);
        cfRes.setFinancialInfoResPast(financialInfoResPast);
        cfRes.setFinancialInfoResCurrent(financialInfoResCurrent);
        cfRes.setMacroInfoRes(macroInfoRes);
        cfRes.setNewsRes(newsResList);

        return new ResponseEntity<>(cfRes, HttpStatus.OK);
    }
}
