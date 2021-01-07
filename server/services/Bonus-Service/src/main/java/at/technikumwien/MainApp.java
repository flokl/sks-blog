package at.technikumwien;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@SpringBootApplication
@EnableJpaRepositories
@EnableBinding(Sink.class)
@EnableScheduling
public class MainApp {
    @Autowired
    private AuthorRepository authorRepository;

    public static void main(String[] args) {
        SpringApplication.run(MainApp.class, args);
    }

    //TODO dev code
    @Scheduled(fixedDelay = 30000)
    // 00:01 on the 1 day of the month
    //@Scheduled(cron = "1 0 1 * *")
    public void run() {
        for (Author author : authorRepository.findAll()) {
            if (author.getCommission() > 0) {
                System.out.println(author.getFirstName() + " " + author.getLastName() + " got paid " + author.getCommission() + "€.");
                author.resetCommission();
                authorRepository.save(author);
            }
        }
    }
}
