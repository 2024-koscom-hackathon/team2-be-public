package koscom.team2.team2be.dto;

import koscom.team2.team2be.entity.StockInfo;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StockInfoRes {

    private String sCode;
    private String sName;

    public StockInfo toEntity(){
        return StockInfo.builder()
                .sCode(sCode)
                .sName(sName)
                .build();
    }
}
