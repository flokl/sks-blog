import {Component, OnInit} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {News} from '../../interfaces/news';
import {ActivatedRoute} from '@angular/router';

@Component({
    selector: 'app-news',
    templateUrl: './news.page.html',
    styleUrls: ['./news.page.scss'],
})
export class NewsPage implements OnInit {
    public news: News[] = [] as News[];

    constructor(private httpClient: HttpClient, private activatedRoute: ActivatedRoute) {
    }

    async ngOnInit() {
        this.news = await this.httpClient.get('http://localhost:5555/api/news/resources/news', {}).toPromise() as News[];
    }
}
