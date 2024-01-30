package koscom.team2.team2be.dto;

import jakarta.persistence.Column;
import koscom.team2.team2be.entity.MacroInfo;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MacroInfoRes {

    private int year;
    private int month;
    private double kospi;
    private double kosdaq;
    private double exRate;
    private double baseRateKr;
//    private double baseRateUs;

    public MacroInfo toEntity(){
        return MacroInfo.builder()
                .year(year)
                .month(month)
                .kospi(kospi)
                .kosdaq(kosdaq)
                .exRate(exRate)
                .baseRateKr(baseRateKr)
//                .baseRateUs(baseRateUs)
                .build();
    }

}
