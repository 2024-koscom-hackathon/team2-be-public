package koscom.team2.team2be.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StdRes {

//    private FinancialTotalRes financialTotalRes;
    private FinancialInfoRes financialInfoResCurrent;
    private FinancialInfoRes financialInfoResPast;
    private List<MacroInfoRes> macroInfoRes;
    private List<NewsRes> newsRes;
    private List<PatternListRes> patternListRes;

}
