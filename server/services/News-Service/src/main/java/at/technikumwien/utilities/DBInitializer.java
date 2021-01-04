package at.technikumwien.utilities;

import java.time.LocalDate;
import java.util.List;

import at.technikumwien.attraction.Attraction;
import at.technikumwien.attraction.AttractionRepository;
import at.technikumwien.author.AuthorRepository;
import at.technikumwien.category.CategoryRepository;
import at.technikumwien.enums.Sex;
import at.technikumwien.author.Author;
import at.technikumwien.category.Category;
import at.technikumwien.news.News;
import at.technikumwien.news.NewsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener;
import org.springframework.transaction.annotation.Transactional;

@Configuration
public class DBInitializer {
    @Autowired
    private NewsRepository newsRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private AttractionRepository attractionRepository;

    @Transactional
    @EventListener(ApplicationReadyEvent.class)
    public void handleApplicationReady() {

        // create and save data begin ================================================
        // attraction

        Attraction stephansdom = new Attraction("Stephansdom");
        Attraction grossglockner = new Attraction("Großglockner");
        Attraction krimmlerWaterfalls = new Attraction("Krimmler Wasserfälle");
        Attraction generalAttraction = new Attraction("--blank--");

        List<Attraction> attractions = attractionRepository.saveAll(List.of(
                stephansdom, grossglockner, krimmlerWaterfalls, generalAttraction
        ));

        // necessary to get id from created object
        stephansdom = attractions.get(0);
        grossglockner = attractions.get(1);
        krimmlerWaterfalls = attractions.get(2);
        generalAttraction = attractions.get(3);


        // create and save data begin ================================================
        // category

        Category generalCategory = new Category("Allgemein");
        Category city = new Category("Stadt");
        Category nature = new Category("Nature");
        Category other = new Category("Andere");

        List<Category> categories = categoryRepository.saveAll(List.of(
                generalCategory, city, nature, other
        ));

        // necessary to get id from created object
        generalCategory = categories.get(0);
        city = categories.get(1);
        nature = categories.get(2);
        other = categories.get(3);


        // create and save data begin ================================================
        // author

        Author markus = new Author(Sex.MALE, "Markus", "Mustermann");
        Author martina = new Author(Sex.FEMALE, "Martina", "Musterfrau");
        Author lukas = new Author(Sex.MALE, "Lukas", "Hinterleitner");
        Author florian = new Author(Sex.MALE, "Florian", "Klotz");

        List<Author> authors = authorRepository.saveAll(List.of(
                markus, martina, lukas, florian
        ));

        // necessary to get id from created object
        markus = authors.get(0);
        martina = authors.get(1);


        // create and save data begin ================================================
        // news

        News firstEntry = new News(
                "Herzlich Willkommen",
                "Wir dürfen Sie recht herzlich zu unserem neuen Portal über Österreichs Sehenswürdigkeiten begrüßen!",
                LocalDate.of(2019, 1, 1),
                false,
                generalCategory,
                authors,
                generalAttraction
        );

        News portalOnline = new News(
                "News-Portal online!",
                "Unser neues News-Portal ist online.",
                LocalDate.of(2019, 1, 2),
                true,
                other,
                null,
                generalAttraction
        );

        News stephansdom_firstEntry = new News(
                "Neues über den Stephansdom",
                "Der Stephansdom (eigentlich Dom- und Metropolitankirche zu St. Stephan und allen Heiligen)" +
                        " am Wiener Stephansplatz (Bezirk Innere Stadt) ist seit 1365 Domkirche" +
                        " (Sitz eines Domkapitels), seit 1469/1479 Kathedrale (Bischofssitz) und seit 1723" +
                        " Metropolitankirche des Erzbischofs von Wien. Der von den Wienern auch kurz Steffl" +
                        " genannte römisch-katholische Dom gilt als Wahrzeichen Wiens und wird mitunter auch" +
                        " als österreichisches Nationalheiligtum bezeichnet. Namensgeber ist der heilige Stephanus," +
                        " der als erster christlicher Märtyrer gilt. Das zweite Patrozinium ist Allerheiligen.",
                LocalDate.of(2020, 10, 10),
                true,
                city,
                List.of(markus),
                stephansdom
        );

        List<News> news = newsRepository.saveAll(List.of(
                firstEntry, portalOnline, stephansdom_firstEntry
        ));

        // necessary to get id from created object
        firstEntry = news.get(0);
        portalOnline = news.get(1);
    }
}
