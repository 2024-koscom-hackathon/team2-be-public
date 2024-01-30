package koscom.team2.team2be.repository;

import koscom.team2.team2be.entity.MacroInfo;
import koscom.team2.team2be.entity.News;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MacroInfoRepository extends JpaRepository<MacroInfo, Integer> {

    List<MacroInfo> findAllByYearAndMonth(int year, int month);

}
