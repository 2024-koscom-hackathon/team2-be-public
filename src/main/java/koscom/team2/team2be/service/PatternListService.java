package koscom.team2.team2be.service;

import koscom.team2.team2be.dto.PatternListRes;
import koscom.team2.team2be.entity.PatternList;
import koscom.team2.team2be.repository.PatternListRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
@Slf4j
public class PatternListService {

    private final PatternListRepository patternListRepository;

    public List<PatternListRes> getPtnList(String sCode, String startDate, String endDate){
        List<PatternList> patternLists = patternListRepository.findAllBySCodeAndStdStartDateAndStdEndDate(sCode, startDate, endDate);
        List<PatternListRes> patternListRes = patternLists.stream().map(
                patternList -> PatternListRes.builder()
                        .cfStartDate(patternList.getCfStartDate())
                        .cfEndDate(patternList.getCfEndDate())
                        .rank(patternList.getRank())
                        .build()
        ).toList();
        return patternListRes;
    }

}
