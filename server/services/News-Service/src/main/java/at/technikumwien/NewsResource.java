package at.technikumwien;

import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

// see http://localhost:8080/resources/news

@RestController
@RequestMapping("/resources/news")
//@CrossOrigin // --> already defined in gateway security config
@Log
public class NewsResource {
	@Autowired
	private NewsRepository newsRepository;

	@Autowired
	private Source source;

	@PostMapping
	public ResponseEntity<?> create(@RequestBody News news) {
		log.info("create() >> news=" + news);
		
		news.setId(null);
		news = newsRepository.save(news);
		
		URI location = WebMvcLinkBuilder.linkTo(
			WebMvcLinkBuilder.methodOn(getClass()).retrieve(news.getId())
		).toUri();
		
		return ResponseEntity.created(location).build();
	}

	@GetMapping("/{id}")
	public /* ResponseEntity<News> */ News retrieve(@PathVariable long id) {
		log.info("retrieve() >> id=" + id);

		Optional<News> news = newsRepository.findById(id);

		for (Author author : news.get().getAuthors()) {
			Message<Author> message = MessageBuilder
					.withPayload(author)
					.setHeader("TOPIC", "commission")
					.build();

			source
					.output()
					.send(message);
		}

		Message<Category> message = MessageBuilder
				.withPayload(news.get().getCategory())
				.setHeader("TOPIC", "statistic")
				.build();

		source
				.output()
				.send(message);

		return news.orElseThrow(() -> new EmptyResultDataAccessException("can't find news with id " + id, 1));
	}
	
	@PutMapping("/{id}")
	public void update(@PathVariable long id, @RequestBody News news) {
		log.info("update() >> id=" + id + ", news=" + news);
		
		news.setId(id);
		newsRepository.save(news);
	}	
	
	@DeleteMapping("/{id}")
	public void delete(@PathVariable long id) {
		log.info("delete() >> id=" + id);
		
		newsRepository.deleteById(id);   // throws EmptyResultDataAccessException if news could not be found
	}	
	
	@GetMapping
	public List<News> retrieveAll(@RequestParam("categoryid") Optional<Long> optCategoryId, @RequestParam("authorid") Optional<Long> optAuthorId) {
		log.info("retrieveAll() >> optCategoryId=" + optCategoryId + ", optAuthorId=" + optAuthorId);
		
		return optCategoryId
			.map(categoryId -> newsRepository.findAllByCategoryId(categoryId))
			.orElse(optAuthorId
						.map(authorId -> newsRepository.findAllByAuthorsId(authorId))
						.orElse(newsRepository.findAll()));
	}
	
	@GetMapping("/dto")
	public List<NewsDto> retrieveAllAsDto() {
		log.info("retrieveAllAsDto()");
		
		return newsRepository.findAll(
			Sort.by(
				Order.desc("topNews"),
				Order.desc("publicationDate")
			)
		).stream()
			.map(news -> NewsDto.of(news))   // Stream<News> => Stream<NewsDto>
			.collect(Collectors.toList());
	}	
}
