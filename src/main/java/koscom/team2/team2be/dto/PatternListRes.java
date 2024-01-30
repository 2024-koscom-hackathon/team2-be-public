package koscom.team2.team2be.dto;

import koscom.team2.team2be.entity.PatternList;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PatternListRes {

    private String cfStartDate;
    private String cfEndDate;
    private int rank;

    public PatternList toEntity(){
        return PatternList.builder()
                .cfStartDate(cfStartDate)
                .cfEndDate(cfEndDate)
                .rank(rank)
                .build();
    }
}
