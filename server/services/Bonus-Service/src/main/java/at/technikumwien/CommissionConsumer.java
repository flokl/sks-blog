package at.technikumwien;

import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.stereotype.Component;

@Component
@Log
public class CommissionConsumer {

    @Autowired
    private AuthorRepository authorRepository;

    @StreamListener(Sink.INPUT)
    public void handleClick(Author author) {

        author = authorRepository.findById(author.getId()).orElse(author);
        author.increaseCommission();

        authorRepository.save(author);

        log.info("Commission increased for " + author.toString() + " to " + author.getCommission() + "€");
    }
}
