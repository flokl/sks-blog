import {Component, OnInit} from '@angular/core';
import {News} from '../../interfaces/news';
import {ActivatedRoute} from '@angular/router';
import {HttpClient} from '@angular/common/http';
import {Attraction} from '../../interfaces/attraction';

@Component({
    selector: 'app-single-attraction',
    templateUrl: './single-attraction.page.html',
    styleUrls: ['./single-attraction.page.scss'],
})
export class SingleAttractionPage implements OnInit {
    public news: News[] = [];
    public attraction: Attraction = {id: '', name: ''};

    constructor(private activatedRoute: ActivatedRoute, private httpClient: HttpClient) {
    }

    async ngOnInit() {
        const categoryId = this.activatedRoute.snapshot.paramMap.get('id');

        this.attraction = await this.httpClient.get('http://localhost:5555/api/news/resources/attractions/' + categoryId, {})
            .toPromise() as Attraction;

        this.news = await this.httpClient.get('http://localhost:5555/api/news/resources/news/?attractionid=' + categoryId, {})
            .toPromise() as News[];
    }
}
