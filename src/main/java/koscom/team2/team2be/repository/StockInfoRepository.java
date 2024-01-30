package koscom.team2.team2be.repository;

import koscom.team2.team2be.entity.StockInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StockInfoRepository extends JpaRepository<StockInfo, Integer> {

    List<StockInfo> findAllById(int id);
}
