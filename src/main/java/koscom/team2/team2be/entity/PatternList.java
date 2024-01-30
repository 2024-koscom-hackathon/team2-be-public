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
@Table(name = "pattern_list")
@EntityListeners(AuditingEntityListener.class)
public class PatternList {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "s_code")
    private String sCode;

    @Column(name = "std_start_date")
    private String stdStartDate;

    @Column(name = "std_end_date")
    private String stdEndDate;

    @Column(name = "cf_start_date")
    private String cfStartDate;

    @Column(name = "cf_end_date")
    private String cfEndDate;

    @Column(name = "rank")
        private int rank;

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
