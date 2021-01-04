package at.technikumwien.attraction;

import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/resources/attractions")
//@CrossOrigin // --> already defined in gateway security config
@Log
public class AttractionResource {
    @Autowired
    private AttractionRepository attractionRepository;

    @GetMapping
    public List<Attraction> retrieveAll() {
        log.info("retrieveAll()");

        return attractionRepository.findAll();
    }

    @GetMapping("/{id}")
    public Attraction retrieve(@PathVariable long id) {
        log.info("retrieve() >> id=" + id);

        return attractionRepository
                .findById(id)
                .orElseThrow(() -> new EmptyResultDataAccessException("can't find attraction with id " + id, 1));
    }
}
