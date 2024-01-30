package koscom.team2.team2be.dto;

import jakarta.persistence.Column;
import lombok.*;

@Data
@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class FinancialInfoRes {

    private int sales;
    private int cogs;
    private int gross_profit;
    private int sga_expense;
    private int op_profit;
    private int net_income;
    private int tci;

}
