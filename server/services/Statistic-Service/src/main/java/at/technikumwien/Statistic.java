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

@NamedQuery(
        name = "Statistic.findAllByCategoryIdForMonthAndYear",
        query = "SELECT s " +
                "FROM Statistic AS s " +
                "WHERE s.category.id = :categoryid " +
                "AND MONTH(s.date) = :month " +
                "AND YEAR(s.date) = :year " +
                "ORDER BY s.date"
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
