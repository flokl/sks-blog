import {Component, OnInit} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {ActivatedRoute} from '@angular/router';
import {News} from '../../interfaces/news';
import {NewsCategory} from '../../interfaces/news-category';
import {Author} from '../../interfaces/author';
import {Attraction} from '../../interfaces/attraction';

@Component({
    selector: 'app-single-news',
    templateUrl: './single-news.page.html',
    styleUrls: ['./single-news.page.scss'],
})
export class SingleNewsPage implements OnInit {
    public news: News = {
        id: '',
        publicationDate: '',
        text: '',
        authors: [] as Author[],
        title: '',
        category: {} as NewsCategory,
        topNews: false,
        attraction: {} as Attraction
    } as News;

    constructor(private httpClient: HttpClient, private activatedRoute: ActivatedRoute) {}

    async ngOnInit() {
        const id = this.activatedRoute.snapshot.paramMap.get('id');
        this.news = await this.httpClient.get('http://localhost:5555/api/news/resources/news/' + id, {}).toPromise() as News;
    }

}
