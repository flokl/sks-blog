package at.technikumwien.news;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface NewsRepository extends JpaRepository<News, Long> {
	List<News> findAllByCategoryId(long categoryId);
	List<News> findAllByAuthorsId(long authorId);
	List<News> findAllByAttractionId(long attractionId);
}