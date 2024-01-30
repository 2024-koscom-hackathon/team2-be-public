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
public class FinancialTotalRes {

    private List<Integer> sales;
    private List<Integer> cogs;
    private List<Integer> gross_profit;
    private List<Integer> sga_expense;
    private List<Integer> op_profit;
    private List<Integer> net_income;
    private List<Integer> tci;

}
