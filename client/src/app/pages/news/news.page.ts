import {Component, OnInit} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {News} from '../../interfaces/news';
import {Router} from '@angular/router';

@Component({
    selector: 'app-news',
    templateUrl: './news.page.html',
    styleUrls: ['./news.page.scss'],
})
export class NewsPage implements OnInit {
    public news: News[] = [];

    constructor(private httpClient: HttpClient, private router: Router) {}

    async ngOnInit() {
        this.news = await this.httpClient.get('http://localhost:5555/api/news/resources/news', {}).toPromise() as News[];

        console.log(this.news);
    }

    async openNews(id: string) {
        await this.router.navigate(['news', id]);
    }
}
