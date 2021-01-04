package at.technikumwien;

import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.stereotype.Component;

@Component
@Log
public class AuthorResource {

    @Autowired
    private AuthorRepository authorRepository;

    @StreamListener(value = Sink.INPUT, condition = "headers['TOPIC'] == 'commission'")
    public void handleClick(Author author) {

        author = authorRepository.findById(author.getId()).orElse(author);
        author.increaseCommission();

        authorRepository.save(author);

        log.info("Commission for " + author.toString() + " increased to " + author.getCommission() + "€");
    }
}
