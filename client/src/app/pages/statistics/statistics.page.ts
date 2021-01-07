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
    public sumOfViews = 0;
    public useMonthlyStatistic = false;
    public currentDate = '2021-01-01';
    private selectedCategory: NewsCategory = {id: '1', name: 'Allgemein'};

    constructor(private httpClient: HttpClient) {
    }

    async ngOnInit() {
        // get categories
        this.categories = await this.httpClient.get<NewsCategory[]>('http://localhost:5555/api/news/resources/categories',
            {responseType: 'json', headers: {Accept: 'application/json'}}).toPromise();

        // set selected category to first on in array
        this.selectedCategory = this.categories[0];

        // get stats for selected category
        this.allStats = await this.httpClient.get<StatisticEntry[]>('http://localhost:5555/api/statistics/resources/categories/'
            + this.categories[0].id, {responseType: 'json', headers: {Accept: 'application/json'}}).toPromise();

        this.updateSum();
    }

    async changeStatistic() {
        await this.updateStats();
        this.updateSum();
    }

    async segmentChanged(event) {
        this.selectedCategory = event.detail.value as NewsCategory;

        await this.updateStats();
        this.updateSum();
    }

    private updateSum() {
        this.sumOfViews = this.allStats
            .map(entry => entry.viewCount)
            .reduce(((previousValue, currentValue) => previousValue + currentValue), 0);
    }

    private async updateStats() {
        if (this.useMonthlyStatistic) {
            const currentDate = new Date(this.currentDate);
            const currentMonth = currentDate.getMonth() + 1; // + 1 because internal month numeration begins with 0
            const currentYear = currentDate.getFullYear();

            this.allStats = await this.httpClient.get<StatisticEntry[]>('http://localhost:5555/api/statistics/resources/categories/'
                + this.selectedCategory.id
                + '?month=' + currentMonth
                + '&year=' + currentYear
                , {responseType: 'json', headers: {Accept: 'application/json'}}).toPromise();
        } else {
            this.allStats = await this.httpClient.get<StatisticEntry[]>('http://localhost:5555/api/statistics/resources/categories/'
                + this.selectedCategory.id, {responseType: 'json', headers: {Accept: 'application/json'}}).toPromise();
        }
    }
}
