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
public class CfRes {

    private String startDate;
    private String endDate;
    private FinancialInfoRes financialInfoResCurrent;
    private FinancialInfoRes financialInfoResPast;
    private List<MacroInfoRes> macroInfoRes;
    private List<NewsRes> newsRes;

}
