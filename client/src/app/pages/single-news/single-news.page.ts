import {Component, OnInit} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {ActivatedRoute} from '@angular/router';
import {News} from '../../interfaces/news';
import {NewsCategory} from '../../interfaces/news-category';
import {Author} from '../../interfaces/author';

@Component({
    selector: 'app-single-news',
    templateUrl: './single-news.page.html',
    styleUrls: ['./single-news.page.scss'],
})
export class SingleNewsPage implements OnInit {
    public news: News = {
        id: null,
        publicationDate: null,
        text: '',
        authors: [] as Author[],
        title: '',
        category: {} as NewsCategory,
        topNews: false,
    } as News;

    constructor(private httpClient: HttpClient, private activatedRoute: ActivatedRoute) {
    }

    async ngOnInit() {
        const id = this.activatedRoute.snapshot.paramMap.get('id');
        this.news = await this.httpClient.get<News>('http://localhost:5555/api/news/resources/news/' + id,
            {responseType: 'json', headers: {Accept: 'application/json'}}).toPromise();
    }

}
