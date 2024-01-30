package koscom.team2.team2be.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table(name = "stock_market")
@EntityListeners(AuditingEntityListener.class)
public class StockMarket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "s_code")
    private String sCode;

    @Column(name = "date")
    private Date date;

    @Column(name = "opening_price")
    private int openingPrice;

    @Column(name = "closing_price")
    private int closingPrice;

    @Column(name = "max_price")
    private int maxPrice;

    @Column(name = "min_price")
    private int minPrice;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @PrePersist // DB에 insert 되기 직전에 실행
    public void created_at(){
        this.createdAt = LocalDateTime.now();
    }

    @PreUpdate  // update 되기 직전 실행
    public void updated_at() {
        this.updatedAt = LocalDateTime.now();
    }
}
