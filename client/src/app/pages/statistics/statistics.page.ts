import {Component, OnInit} from '@angular/core';
import {HttpClient} from '@angular/common/http';

@Component({
    selector: 'app-statistics',
    templateUrl: './statistics.page.html',
    styleUrls: ['./statistics.page.scss'],
})
export class StatisticsPage implements OnInit {

    constructor(private httpClient: HttpClient) {
    }

    async ngOnInit() {
        console.log(await this.httpClient.get('http://localhost:5555/api/statistics/resources/categories/total/1', {}).toPromise());

    }

}
