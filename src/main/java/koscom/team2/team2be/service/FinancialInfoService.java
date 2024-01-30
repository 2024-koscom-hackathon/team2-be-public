package koscom.team2.team2be.service;

import koscom.team2.team2be.dto.FinancialInfoRes;
import koscom.team2.team2be.dto.FinancialTotalRes;
import koscom.team2.team2be.entity.FinancialInfo;
import koscom.team2.team2be.repository.FinancialInfoRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
@Slf4j
public class FinancialInfoService {

    private final FinancialInfoRepository financialInfoRepository;

    public FinancialInfoRes getCurrentFinancialInfo(String startDate){
        int year = Integer.parseInt(startDate.substring(0, 4));
        FinancialInfo financialInfo = financialInfoRepository.findAllByYear(year);
//        List<FinancialInfoRes> financialInfoRes = financialInfos.stream().map(
//                financialInfo -> FinancialInfoRes.builder()
//                        .sales(financialInfo.getSales())
//                        .cogs(financialInfo.getCogs())
//                        .gross_profit(financialInfo.getGross_profit())
//                        .sga_expense(financialInfo.getSga_expense())
//                        .op_profit(financialInfo.getOp_profit())
//                        .net_income(financialInfo.getNet_income())
//                        .tci(financialInfo.getTci())
//                        .build()).toList();
        FinancialInfoRes financialInfoRes = new FinancialInfoRes();
        financialInfoRes.setSales(financialInfo.getSales());
        financialInfoRes.setCogs(financialInfo.getCogs());
        financialInfoRes.setGross_profit(financialInfo.getGross_profit());
        financialInfoRes.setSga_expense(financialInfo.getSga_expense());
        financialInfoRes.setOp_profit(financialInfo.getOp_profit());
        financialInfoRes.setNet_income(financialInfo.getNet_income());
        financialInfoRes.setTci(financialInfo.getTci());

        return financialInfoRes;
    }

    public FinancialInfoRes getPastFinancialInfo(String startDate){
        int year = Integer.parseInt(startDate.substring(0, 4)) - 1;
        FinancialInfo financialInfo = financialInfoRepository.findAllByYear(year);
//        List<FinancialInfoRes> financialInfoRes = financialInfos.stream().map(
//                financialInfo -> FinancialInfoRes.builder()
//                        .sales(financialInfo.getSales())
//                        .cogs(financialInfo.getCogs())
//                        .gross_profit(financialInfo.getGross_profit())
//                        .sga_expense(financialInfo.getSga_expense())
//                        .op_profit(financialInfo.getOp_profit())
//                        .net_income(financialInfo.getNet_income())
//                        .tci(financialInfo.getTci())
//                        .build()).toList();
        FinancialInfoRes financialInfoRes = new FinancialInfoRes();
        financialInfoRes.setSales(financialInfo.getSales());
        financialInfoRes.setCogs(financialInfo.getCogs());
        financialInfoRes.setGross_profit(financialInfo.getGross_profit());
        financialInfoRes.setSga_expense(financialInfo.getSga_expense());
        financialInfoRes.setOp_profit(financialInfo.getOp_profit());
        financialInfoRes.setNet_income(financialInfo.getNet_income());
        financialInfoRes.setTci(financialInfo.getTci());

        return financialInfoRes;
    }
//
//    public FinancialTotalRes getTotalFinancialInfo(String startDate){
//        List<FinancialInfoRes> pastYear = getPastFinancialInfo(startDate);
//        List<FinancialInfoRes> currentYear = getCurrentFinancialInfo(startDate);
//
//        System.out.println("===TEST===");
//        System.out.println(currentYear.get(0).getSales());
//
//
//        FinancialTotalRes financialTotalRes = new FinancialTotalRes();
//
//        List<Integer> sales = null;
//        List<Integer> cogs = null;
//        List<Integer> gross_profit = null;
//        List<Integer> sga_expense = null;
//        List<Integer> op_profit = null;
//        List<Integer> net_income = null;
//        List<Integer> tci = null;
//
//        sales.set(0, currentYear.get(0).getSales());
//        sales.set(1, pastYear.get(0).getSales());
//        cogs.set(0, currentYear.get(0).getCogs());
//        cogs.set(1, pastYear.get(0).getCogs());
//        gross_profit.set(0, currentYear.get(0).getGross_profit());
//        gross_profit.set(1, pastYear.get(0).getGross_profit());
//        sga_expense.set(0, currentYear.get(0).getSga_expense());
//        sga_expense.set(1, pastYear.get(0).getSga_expense());
//        op_profit.set(0, currentYear.get(0).getOp_profit());
//        op_profit.set(1, pastYear.get(0).getOp_profit());
//        net_income.set(0, currentYear.get(0).getNet_income());
//        net_income.set(1, pastYear.get(0).getNet_income());
//        tci.set(0, currentYear.get(0).getTci());
//        tci.set(1, pastYear.get(0).getTci());
//
//        financialTotalRes.setSales(sales);
//        financialTotalRes.setCogs(cogs);
//        financialTotalRes.setGross_profit(gross_profit);
//        financialTotalRes.setSga_expense(sga_expense);
//
//        return financialTotalRes;
//    }
}
