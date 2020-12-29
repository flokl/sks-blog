import {Component, OnInit} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {News} from '../../interfaces/news';
import {NewsCategory} from '../../interfaces/news-category';

@Component({
    selector: 'app-categories',
    templateUrl: './categories.page.html',
    styleUrls: ['./categories.page.scss'],
})
export class CategoriesPage implements OnInit {
    public categories: NewsCategory[] = [];

    constructor(private httpClient: HttpClient) {
    }

    async ngOnInit() {
        this.categories = await this.httpClient.get('http://localhost:5555/api/news/resources/categories', {})
            .toPromise() as NewsCategory[];
    }

}
