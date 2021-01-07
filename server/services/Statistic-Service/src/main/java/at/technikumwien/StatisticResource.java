package at.technikumwien;

import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

// see http://localhost:5555/api/statistics/resources/categories

@RestController
@RequestMapping("/resources/categories")
@Component
@Log
public class StatisticResource {
    @Autowired
    private StatisticRepository statisticRepository;

    @GetMapping(value = "/{categoryid}")
    public List<Statistic> getCategoryViews(@PathVariable long categoryid,
                                            @RequestParam("month") Optional<Integer> month,
                                            @RequestParam("year") Optional<Integer> year) {

        if (month.isPresent() && year.isPresent()) {
            log.info("getCategoryViews => " + categoryid + ", month: " + month.get() + ", year: " + year);
            return statisticRepository.findAllByCategoryIdForMonthAndYear(categoryid, month.get(), year.get());
        } else {
            log.info("getCategoryViews => " + categoryid);
            return statisticRepository.findAllByCategoryIdOrderByDate(categoryid);
        }
    }

    @GetMapping
    public List<Statistic> retrieveAll() {
        log.info("retrieveAll()");
        return statisticRepository.findAll();
    }

    @StreamListener(value = Sink.INPUT, condition = "headers['TOPIC'] == 'statistic'")
    public void handleClick(Category category) {
        Statistic currentStatistic = statisticRepository.findByCategoryIdAndDate(category.getId(),
                new Date(System.currentTimeMillis()))
                .orElse(new Statistic(category));

        currentStatistic.increaseViewCount();
        statisticRepository.save(currentStatistic);

        log.info("Category count for " + currentStatistic.getCategory().getName() +
                " increased to " + currentStatistic.getViewCount());
    }
}
