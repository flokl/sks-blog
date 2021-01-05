package at.technikumwien.news;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NewsRepository extends JpaRepository<News, Long> {
	List<News> findAllByCategoryId(long categoryId);
	List<News> findAllByAuthorsId(long authorId);
}