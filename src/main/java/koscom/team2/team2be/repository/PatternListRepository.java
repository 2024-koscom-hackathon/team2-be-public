package koscom.team2.team2be.repository;

import koscom.team2.team2be.entity.PatternList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PatternListRepository extends JpaRepository<PatternList, Integer> {

    @Query(value = "SELECT * FROM pattern_list p WHERE p.s_code = :sCode AND p.std_start_date = :startDate AND p.std_end_date = :endDate ORDER BY p.rank LIMIT 7 ", nativeQuery = true)
    List<PatternList> findAllBySCodeAndStdStartDateAndStdEndDate(String sCode, String startDate, String endDate);

}
