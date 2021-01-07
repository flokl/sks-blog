package at.technikumwien;

import org.springframework.data.jpa.repository.JpaRepository;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

public interface StatisticRepository extends JpaRepository<Statistic, Long> {
    Optional<Statistic> findByCategoryIdAndDate(long categoryid, Date date);

    List<Statistic> findAllByCategoryIdOrderByDate(long categoryId);

    List<Statistic> findAllByCategoryIdForMonthAndYear(long categoryid, int month, int year);
}
