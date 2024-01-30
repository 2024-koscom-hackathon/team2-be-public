package koscom.team2.team2be.repository;

import koscom.team2.team2be.entity.StockMarket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface StockMarketRepository extends JpaRepository<StockMarket, Integer> {

        @Query(value = "SELECT * FROM stock_market s WHERE s.s_code =:sCode", nativeQuery = true)
        List<StockMarket> findAllBySCode(String sCode);

        @Query(value = "SELECT * FROM stock_market s WHERE s.s_code =:sCode AND s.date BETWEEN :startDate AND :endDate", nativeQuery = true)
        List<StockMarket> findAllBySCodeAndDateBetween(String sCode, String startDate, String endDate);

}
