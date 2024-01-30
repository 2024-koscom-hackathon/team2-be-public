package koscom.team2.team2be.entity;

import jakarta.persistence.*;
import koscom.team2.team2be.dto.FinancialInfoRes;
import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table(name = "financial_info")
@EntityListeners(AuditingEntityListener.class)
public class FinancialInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "year")
    private int year;

    @Column(name = "sales")
    private int sales;

    @Column(name = "cogs")
    private int cogs;

    @Column(name = "gross_profit")
    private int gross_profit;

    @Column(name = "sga_expense")
    private int sga_expense;

    @Column(name = "op_profit")
    private int op_profit;

    @Column(name = "net_income")
    private int net_income;

    @Column(name = "tci")
    private int tci;

    public FinancialInfo toEntity(){
        return FinancialInfo.builder()
                .sales(sales)
                .cogs(cogs)
                .gross_profit(gross_profit)
                .sga_expense(sga_expense)
                .op_profit(op_profit)
                .net_income(net_income)
                .tci(tci)
                .build();
    }

}
