package koscom.team2.team2be.service;

import koscom.team2.team2be.dto.NewsRes;
import koscom.team2.team2be.dto.StockInfoRes;
import koscom.team2.team2be.entity.News;
import koscom.team2.team2be.entity.StockInfo;
import koscom.team2.team2be.repository.NewsRepository;
import koscom.team2.team2be.repository.StockInfoRepository;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@RequiredArgsConstructor
@Service
@Slf4j
public class NewsService {
    private final NewsRepository newsRepository;

    public List<NewsRes> getNews(String sCode, String startDate, String endDate){
        List<News> newsList = newsRepository.findTop10BySCodeBetween(sCode, startDate, endDate);
        List<NewsRes> newsRes = newsList.stream().map(
                news -> NewsRes.builder()
                        .date(news.getDate())
                        .title(news.getTitle())
                        .source(news.getSource())
                        .link(news.getLink())
                        .summary(news.getSummary())
                        .score(news.getScore())
                        .build()).toList();
        return newsRes;
    }
}
