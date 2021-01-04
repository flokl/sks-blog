import {Component, OnInit} from '@angular/core';
import {ActivatedRoute} from '@angular/router';
import {HttpClient} from '@angular/common/http';
import {News} from '../../interfaces/news';
import {NewsCategory} from '../../interfaces/news-category';

@Component({
    selector: 'app-single-category',
    templateUrl: './single-category.page.html',
    styleUrls: ['./single-category.page.scss'],
})
export class SingleCategoryPage implements OnInit {
    public news: News[] = [];
    public category: NewsCategory = {id: '', name: ''};

    constructor(private activatedRoute: ActivatedRoute, private httpClient: HttpClient) {
    }

    async ngOnInit() {
        const categoryId = this.activatedRoute.snapshot.paramMap.get('id');

        this.category = await this.httpClient.get('http://localhost:5555/api/news/resources/categories/' + categoryId, {})
            .toPromise() as NewsCategory;

        this.news = await this.httpClient.get('http://localhost:5555/api/news/resources/news/?categoryid=' + categoryId, {})
            .toPromise() as News[];
    }

}
