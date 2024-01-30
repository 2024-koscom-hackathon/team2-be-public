package koscom.team2.team2be.dto;

import koscom.team2.team2be.entity.News;
import koscom.team2.team2be.entity.StockInfo;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class NewsRes {

    private String title;
    private LocalDateTime date;
    private String source;
    private String link;
    private String summary;
    private int score;

    public News toEntity(){
        return News.builder()
                .title(title)
                .date(date)
                .source(source)
                .link(link)
                .summary(summary)
                .score(score)
                .build();
    }

}
