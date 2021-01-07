import {Component, OnInit} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {StatisticEntry} from '../../interfaces/statistic-entry';
import {NewsCategory} from '../../interfaces/news-category';

@Component({
    selector: 'app-statistics',
    templateUrl: './statistics.page.html',
    styleUrls: ['./statistics.page.scss'],
})
export class StatisticsPage implements OnInit {
    public allStats: StatisticEntry[] = [];
    public categories: NewsCategory[] = [];

    public sumOfViews: number = 0;

    constructor(private httpClient: HttpClient) {
    }

    async ngOnInit() {
        this.categories = await this.httpClient.get<NewsCategory[]>('http://localhost:5555/api/news/resources/categories',
            {}).toPromise();

        this.allStats = await this.httpClient.get<StatisticEntry[]>('http://localhost:5555/api/statistics/resources/categories/' + this.categories[0].id,
            {}).toPromise();

        this.sumOfViews = this.allStats.map(entry => entry.viewCount).reduce(((previousValue, currentValue) => previousValue + currentValue), 0);
    }

    async segmentChanged(event) {
        const selectedCategory = event.detail.value as NewsCategory;
        this.allStats = await this.httpClient.get<StatisticEntry[]>('http://localhost:5555/api/statistics/resources/categories/' + selectedCategory.id,
            {}).toPromise();

        this.sumOfViews = this.allStats.map(entry => entry.viewCount).reduce(((previousValue, currentValue) => previousValue + currentValue), 0);
    }

}
