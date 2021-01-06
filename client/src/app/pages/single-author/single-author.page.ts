import {Component, OnInit} from '@angular/core';
import {ActivatedRoute} from '@angular/router';
import {HttpClient} from '@angular/common/http';
import {News} from '../../interfaces/news';
import {Author} from '../../interfaces/author';

@Component({
    selector: 'app-single-author',
    templateUrl: './single-author.page.html',
    styleUrls: ['./single-author.page.scss'],
})
export class SingleAuthorPage implements OnInit {
    public news: News[] = [];
    public author: Author = {id: '', sex: '', lastName: '', firstName: ''};

    constructor(private activatedRoute: ActivatedRoute, private httpClient: HttpClient) {
    }

    async ngOnInit() {
        const authorId = this.activatedRoute.snapshot.paramMap.get('id');

        this.author = await this.httpClient.get<Author>('http://localhost:5555/api/news/resources/authors/' + authorId,
            {responseType: 'json', headers: {Accept: 'application/json'}}).toPromise();

        this.news = await this.httpClient.get<News[]>('http://localhost:5555/api/news/resources/news/?authorid=' + authorId,
            {responseType: 'json', headers: {Accept: 'application/json'}}).toPromise();
    }

}
