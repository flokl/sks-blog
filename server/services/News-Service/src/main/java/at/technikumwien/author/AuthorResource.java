package at.technikumwien.author;

import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/resources/authors")
//@CrossOrigin // --> already defined in gateway security config
@Log
public class AuthorResource {
    @Autowired
    private AuthorRepository authorRepository;

    @GetMapping
    public List<Author> retrieveAll() {
        log.info("retrieveAll()");

        return authorRepository.findAll();
    }

    @GetMapping("/{id}")
    public Author retrieve(@PathVariable long id) {
        log.info("retrieve() >> id=" + id);

        return authorRepository
                .findById(id)
                .orElseThrow(() -> new EmptyResultDataAccessException("can't find author with id " + id, 1));
    }
}
