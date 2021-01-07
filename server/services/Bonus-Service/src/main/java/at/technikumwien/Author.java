package at.technikumwien;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.math.RoundingMode;

@Data
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name = "t_author")
public class Author {

	@Id
	private Long id;

	@Enumerated(EnumType.STRING)
	@Column(nullable = false, length = 10)
	private Sex sex;
	
	@Column(nullable = false, length = 50)
	private String firstName;
	
	@Column(nullable = false, length = 50)
	private String lastName;

	@Setter(value=AccessLevel.NONE)
	@Column(nullable = false)
	private Double commission;

	public Author(Long id, Sex sex, String firstName, String lastName) {
		this(id, sex, firstName, lastName, 0.00);
	}

	public void increaseCommission() {
		if (this.commission == null)
			this.commission = 0.00;
		this.commission += 0.01;
		this.commission = new BigDecimal(commission).setScale(2, RoundingMode.HALF_DOWN).doubleValue();
	}

	public void resetCommission() {
		this.commission = 0.0;
	}
}
