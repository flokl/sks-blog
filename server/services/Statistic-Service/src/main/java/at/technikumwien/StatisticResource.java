package at.technikumwien;

import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.time.Month;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/statistics/categories")
@Component
@Log
public class StatisticResource {
    @Autowired
    private StatisticRepository statisticRepository;

    @GetMapping(value = "/{categoryid}")
    public List<Statistic> getCategoryViews(@PathVariable long categoryid) {
        log.info("getCategoryViews => " + categoryid);
        return Optional.ofNullable(statisticRepository.findStatisticByCategoryId(categoryid)).orElseThrow(() ->
                new EmptyResultDataAccessException("can't find statistics for category with id " + categoryid, 1));
    }

    @GetMapping(value = "/total/{categoryid}")
    public Long getTotalCategoryViews(@PathVariable long categoryid) {
        log.info("getTotalCategoryViews => " + categoryid);
        return Optional.ofNullable(statisticRepository.findSumByCategoryId(categoryid)).orElseThrow(() ->
                new EmptyResultDataAccessException("can't find statistic for category with id " + categoryid, 1));
    }

    @GetMapping
    public List<Statistic> getMonthlyViews(@RequestParam("month") int month, @RequestParam("year") int year) {
        log.info("getMonthlyViews => " + month + "." + year);
        return Optional.ofNullable(statisticRepository.findAllByMonthYear(month, year)).orElseThrow(() ->
                new EmptyResultDataAccessException("can't find statistic for " + Month.of(month) + " " + year, 1));
    }

    private Optional<Statistic> getCurrentMonthViews(long categoryid) {
        log.info("getCurrentMonthViews => " + categoryid);
        return statisticRepository.findCurrentByCategoryId(categoryid).isEmpty() ?
                Optional.empty() : Optional.ofNullable(statisticRepository.findCurrentByCategoryId(categoryid).get(0));
    }

    @StreamListener(value = Sink.INPUT, condition = "headers['TOPIC'] == 'statistic'")
    public void handleClick(Category category) {

        Statistic currentStatistic = getCurrentMonthViews(category.getId())
                .orElse(new Statistic(category));

        currentStatistic.increaseViewCount();
        statisticRepository.save(currentStatistic);

        log.info("Category count for " + currentStatistic.getCategory().getName() +
                " increased to " + currentStatistic.getViewCount());
    }
}
