import {Component, OnInit} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Author} from '../../interfaces/author';

@Component({
    selector: 'app-authors',
    templateUrl: './authors.page.html',
    styleUrls: ['./authors.page.scss'],
})
export class AuthorsPage implements OnInit {
    public authors: Author[] = [];

    constructor(private httpClient: HttpClient) {
    }

    async ngOnInit() {
        this.authors = await this.httpClient.get('http://localhost:5555/api/news/resources/authors', {}).toPromise() as Author[];
    }

}
