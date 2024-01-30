package koscom.team2.team2be.service;

import koscom.team2.team2be.dto.MacroInfoRes;
import koscom.team2.team2be.entity.MacroInfo;
import koscom.team2.team2be.repository.MacroInfoRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
@Slf4j
public class MacroInfoService {
    private final MacroInfoRepository macroInfoRepository;

    public List<MacroInfoRes> getMacroInfo(String startDate){
        int year = Integer.parseInt(startDate.substring(0, 4));
        int month = Integer.parseInt(startDate.substring(5,7));
        List<MacroInfo> macroInfos = macroInfoRepository.findAllByYearAndMonth(year, month);
        List<MacroInfoRes> macroInfoRes = macroInfos.stream().map(
                macroInfo -> MacroInfoRes.builder()
                        .year(macroInfo.getYear())
                        .month(macroInfo.getMonth())
                        .kospi(macroInfo.getKospi())
                        .kosdaq(macroInfo.getKosdaq())
                        .exRate(macroInfo.getExRate())
                        .baseRateKr(macroInfo.getBaseRateKr())
//                        .baseRateUs(macroInfo.getBaseRateUs())
                        .build()).toList();
        return macroInfoRes;
    }
}
