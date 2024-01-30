package koscom.team2.team2be.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table(name = "macro_info")
@EntityListeners(AuditingEntityListener.class)
public class MacroInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "year")
    private int year;

    @Column(name = "month")
    private int month;

    @Column(name = "kospi")
    private double kospi;

    @Column(name = "kosdaq")
    private double kosdaq;

    @Column(name = "ex_rate")
    private double exRate;

    @Column(name = "base_rate_kr")
    private double baseRateKr;

    @Column(name = "base_rate_us")
    private double baseRateUs;

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
