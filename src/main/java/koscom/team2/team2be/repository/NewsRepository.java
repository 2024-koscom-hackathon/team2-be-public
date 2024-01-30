package koscom.team2.team2be.repository;

import koscom.team2.team2be.entity.News;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.List;

public interface NewsRepository extends JpaRepository<News, Integer> {

    @Query(value = "SELECT * FROM news n WHERE n.s_code =:sCode AND date BETWEEN :startDate AND :endDate LIMIT 10", nativeQuery = true)
    List<News> findTop10BySCodeBetween(String sCode, String startDate, String endDate);
}
