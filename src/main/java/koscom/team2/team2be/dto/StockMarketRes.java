package koscom.team2.team2be.dto;

import koscom.team2.team2be.entity.StockMarket;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StockMarketRes {

    private Date date;
    private int openingPrice;
    private int closingPrice;
    private int maxPrice;
    private int minPrice;

    public StockMarket toEntity(){
        return StockMarket.builder()
                .date(date)
                .openingPrice(openingPrice)
                .closingPrice(closingPrice)
                .maxPrice(maxPrice)
                .minPrice(minPrice)
                .build();
    }

}
