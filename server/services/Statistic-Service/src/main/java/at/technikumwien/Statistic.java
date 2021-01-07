package at.technikumwien;


import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Date;

@Data
@NoArgsConstructor

@Entity
@Table(name = "t_statistic")

/*@NamedQuery(
        name = "Statistic.findStatisticByCategoryId",
        query = "SELECT new Statistic(CAST(0 AS long), s.category, CAST(CONCAT(YEAR(s.date), '-', MONTH(s.date), '-', '01') AS date),  SUM(s.viewCount)) " +
                "FROM Statistic s " +
                "WHERE s.category.id = :categoryid " +
                "GROUP BY s.category.id, MONTH(s.date), YEAR(s.date)"
)*/

@NamedQuery(
        name = "Statistic.findStatisticByCategoryId",
        query = "SELECT new Statistic(CAST(0 AS long), s.category, CAST(CONCAT(YEAR(s.date), '-', MONTH(s.date), '-', '01') AS date), SUM(s.viewCount)) " +
                "FROM Statistic s " +
                "WHERE s.category.id = :categoryid " +
                "GROUP BY s.category.id, CONCAT(YEAR(s.date), '-', MONTH(s.date), '-', '01')"
)

@NamedQuery(
        name = "Statistic.findSumByCategoryId",
        query = "SELECT SUM(s.viewCount) " +
                "FROM Statistic s " +
                "WHERE s.category.id = :categoryid"
)
@NamedQuery(
        name = "Statistic.findAllByMonthYear",
        query = "SELECT new Statistic(CAST(0 AS long), s.category, CAST(CONCAT(YEAR(s.date), '-', MONTH(s.date), '-', '01') AS date), SUM(s.viewCount))" +
                "FROM Statistic s " +
                "WHERE MONTH(s.date) = :month " +
                "AND YEAR(s.date) = :year " +
                "GROUP BY s.category"
)
@NamedQuery(
        name = "Statistic.findCurrentByCategoryId",
        query = "SELECT s " +
                "FROM Statistic s " +
                "WHERE s.category.id = :categoryid " +
                "AND s.date = CURRENT_DATE"
)
public class Statistic {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(nullable = false, name = "categoryid")
    private Category category;

    @Column(nullable = false)
    private Date date;

    @Setter(value = AccessLevel.NONE)
    @Column(nullable = false, name = "viewcount")
    private Long viewCount;

    public Statistic(Long id, Category category, java.util.Date date, Long viewCount) {
        this.id = id;
        this.category = category;
        // Cast to sql.Date, because util.Date includes Time
        this.date = (Date) date;
        this.viewCount = viewCount;
    }

    public Statistic(Category category) {
        this(null, category, new Date(System.currentTimeMillis()), 0L);
    }

    public void increaseViewCount() {
        if (viewCount == null)
            viewCount = 0L;
        viewCount++;
    }
}
