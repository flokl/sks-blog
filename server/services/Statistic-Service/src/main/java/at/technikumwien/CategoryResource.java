package at.technikumwien;

import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/statistics/categories")
@Component
@Log
public class CategoryResource {
    @Autowired
    private CategoryRepository categoryRepository;

    @GetMapping
    public List<Category> retrieveAll() {
        log.info("retrieveAll()");

        return categoryRepository.findAll();
    }

    @StreamListener(value = Sink.INPUT, condition = "headers['TOPIC'] == 'statistic'")
    public void handleClick(Category category) {

        category = categoryRepository.findById(category.getId()).orElse(category);
        category.increaseViewCount();

        categoryRepository.save(category);

        log.info("Category count for " + category.toString() + " increased to " + category.getViewCount());
    }
}
