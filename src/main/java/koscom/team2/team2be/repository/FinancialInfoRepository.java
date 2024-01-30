package koscom.team2.team2be.repository;

import koscom.team2.team2be.entity.FinancialInfo;
import koscom.team2.team2be.entity.MacroInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FinancialInfoRepository extends JpaRepository<FinancialInfo, Integer> {
    FinancialInfo findAllByYear(int year);
}
