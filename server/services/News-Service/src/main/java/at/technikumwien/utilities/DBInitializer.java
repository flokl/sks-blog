package at.technikumwien.utilities;

import at.technikumwien.author.Author;
import at.technikumwien.author.AuthorRepository;
import at.technikumwien.category.Category;
import at.technikumwien.category.CategoryRepository;
import at.technikumwien.enums.Sex;
import at.technikumwien.news.News;
import at.technikumwien.news.NewsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Configuration
public class DBInitializer {
    private boolean alreadyInitialized = false;

    @Autowired
    private NewsRepository newsRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private AuthorRepository authorRepository;

    @Transactional
    @EventListener(ApplicationReadyEvent.class)
    public void handleApplicationReady() {
        if (!alreadyInitialized) { // needed because kafka fires the same event twice
            // create and save data begin ================================================
            // category

            Category generalCategory = new Category("Allgemein");
            Category stephansdom = new Category("Stephansdom");
            Category krimmlerWasserfalle = new Category("Krimmler Wasserfalle");
            Category grossglockner = new Category("Großglockner");

            List<Category> categories = categoryRepository.saveAll(List.of(
                    generalCategory, stephansdom, krimmlerWasserfalle, grossglockner
            ));

            // necessary to get id from created object
            generalCategory = categories.get(0);
            stephansdom = categories.get(1);
            krimmlerWasserfalle = categories.get(2);
            grossglockner = categories.get(3);


            // create and save data begin ================================================
            // author

            Author markus = new Author(Sex.MALE, "Markus", "Mustermann");
            Author martina = new Author(Sex.FEMALE, "Martina", "Musterfrau");
            Author lukas = new Author(Sex.MALE, "Lukas", "Hinterleitner");
            Author florian = new Author(Sex.MALE, "Florian", "Klotz");
            Author bernhard = new Author(Sex.MALE, "Bernhard", "Löwenstein");

            List<Author> authors = authorRepository.saveAll(List.of(
                    markus, martina, lukas, florian, bernhard
            ));

            // necessary to get id from created object
            markus = authors.get(0);
            martina = authors.get(1);
            lukas = authors.get(2);
            florian = authors.get(3);
            bernhard = authors.get(4);


            // create and save data begin ================================================
            // news

            News firstEntry = new News(
                    "Herzlich Willkommen",
                    "Wir dürfen Sie recht herzlich zu unserem neuen Portal über Österreichs Sehenswürdigkeiten begrüßen!",
                    LocalDate.of(2019, 1, 1),
                    false,
                    generalCategory,
                    List.of(lukas, florian)
            );

            News portalOnline = new News(
                    "News-Portal online!",
                    "Unser neues News-Portal ist online.",
                    LocalDate.of(2019, 1, 2),
                    true,
                    generalCategory,
                    null
            );

            News newsStephandsdom = new News(
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
                    stephansdom,
                    List.of(markus)
            );

            News newsGroßglockner = new News(
                    "Neues über den Stephansdom",
                    "Der Stephansdom (eigentlich Dom- und Metropolitankirche zu St. Stephan und allen Heiligen)" +
                            " am Wiener Stephansplatz (Bezirk Innere Stadt) ist seit 1365 Domkirche" +
                            " (Sitz eines Domkapitels), seit 1469/1479 Kathedrale (Bischofssitz) und seit 1723" +
                            " Metropolitankirche des Erzbischofs von Wien. Der von den Wienern auch kurz Steffl" +
                            " genannte römisch-katholische Dom gilt als Wahrzeichen Wiens und wird mitunter auch" +
                            " als österreichisches Nationalheiligtum bezeichnet. Namensgeber ist der heilige Stephanus," +
                            " der als erster christlicher Märtyrer gilt. Das zweite Patrozinium ist Allerheiligen.",
                    LocalDate.of(2021, 1, 3),
                    true,
                    grossglockner,
                    List.of(lukas, florian)
            );

            News newsKrimmlerWasserfaelle = new News(
                    "Das Dach der Welt",
                    "Die Krimmler Wasserfälle sind mit einer gesamten Fallhöhe von 385 m die höchsten " +
                            "Wasserfälle Österreichs. Sie befinden sich am Rand des Ortes Krimml (Salzburg) " +
                            "im Nationalpark Hohe Tauern. Gebildet werden sie durch die Krimmler Ache, die am " +
                            "Ende des hoch gelegenen Krimmler Achentals in drei Fallstufen hinunterstürzt.",
                    LocalDate.of(2021, 1, 3),
                    true,
                    krimmlerWasserfalle,
                    List.of(lukas, florian)
            );



            List<News> news = newsRepository.saveAll(List.of(
                    firstEntry, portalOnline, newsStephandsdom, newsGroßglockner, newsKrimmlerWasserfaelle
            ));

            // necessary to get id from created object
            firstEntry = news.get(0);
            portalOnline = news.get(1);
            newsStephandsdom = news.get(2);
            newsGroßglockner = news.get(3);
            newsKrimmlerWasserfaelle = news.get(4);
        }

        System.out.println("DBInitializer already intialized: " + this.alreadyInitialized);
        this.alreadyInitialized = true;
    }
}
