package at.technikumwien;

import lombok.*;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name = "t_category")
public class Category {
	@Id
	private Long id;
	
	@Column(nullable = false, length = 50)
	private String name;

	@Setter(value= AccessLevel.NONE)
	@Column(nullable = false)
	private Integer viewCount;
	
//	@OneToMany(mappedBy = "category")
//	private List<News> newsList;
	
	public Category(Long id, String name) {
		this(id, name, 0);
	}

	public void increaseViewCount() {
		if (this.viewCount == null)
			this.viewCount = 0;
		this.viewCount++;
	}
}
