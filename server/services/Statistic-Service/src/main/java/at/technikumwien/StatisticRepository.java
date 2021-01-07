package at.technikumwien;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StatisticRepository extends JpaRepository<Statistic, Long> {
    List<Statistic> findStatisticByCategoryId(long categoryid);

    Long findSumByCategoryId(long categoryid);

    List<Statistic> findAllByMonthYear(int month, int year);

    List<Statistic> findCurrentByCategoryId(long categoryid);

    List<Statistic> findAllByCategoryId(long categoryId);
}
