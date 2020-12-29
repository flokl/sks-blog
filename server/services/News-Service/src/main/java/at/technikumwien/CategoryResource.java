package at.technikumwien;

import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/resources/categories")
//@CrossOrigin // --> already defined in gateway security config
@Log
public class CategoryResource {
    @Autowired
    private CategoryRepository categoryRepository;

    @GetMapping
    public List<Category> retrieveAll() {
        log.info("retrieveAll()");

        return categoryRepository.findAll();
    }

    @GetMapping("/{id}")
    public Category retrieve(@PathVariable long id) {
        log.info("retrieve() >> id=" + id);

        return categoryRepository
                .findById(id)
                .orElseThrow(() -> new EmptyResultDataAccessException("can't find category with id " + id, 1));
    }
}
