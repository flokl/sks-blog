import {Component, OnInit} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Author} from '../../interfaces/author';
import {News} from '../../interfaces/news';
import {NewsCategory} from '../../interfaces/news-category';

@Component({
    selector: 'app-create-entry',
    templateUrl: './create-entry.page.html',
    styleUrls: ['./create-entry.page.scss'],
})
export class CreateEntryPage implements OnInit {
    public authors: Author[] = [];
    public categories: NewsCategory[] = [];

    public news: News = {
        id: null,
        publicationDate: null,
        text: '',
        authors: [] as Author[],
        title: '',
        category: {} as NewsCategory,
        topNews: false,
    } as News;

    constructor(private httpClient: HttpClient) {
    }

    async ngOnInit() {
        this.authors = await this.httpClient.get('http://localhost:5555/api/news/resources/authors', {})
            .toPromise() as Author[];

        this.categories = await this.httpClient.get('http://localhost:5555/api/news/resources/categories', {})
            .toPromise() as NewsCategory[];
    }

    async createEntry() {
        this.news.publicationDate = new Date();

        console.log('entry sent to server: ', this.news);

        const response = await this.httpClient.post('http://localhost:5555/api/news/resources/news', this.news, {}).toPromise();
        console.log('response: ', response);
    }

    compareWithAuthor(o1: Author, o2: Author | Author[]) {
        if (!o1 || !o2) {
            return o1 === o2;
        }

        if (Array.isArray(o2)) {
            return o2.some((u: Author) => u.id === o1.id);
        }

        return o1.id === o2.id;
    }

    compareWithCategory(o1: NewsCategory, o2: NewsCategory) {
        return o1 && o2 ? o1.id === o2.id : o1 === o2;
    }


}
