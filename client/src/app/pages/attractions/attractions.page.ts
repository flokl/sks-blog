import { Component, OnInit } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Attraction} from '../../interfaces/attraction';

@Component({
  selector: 'app-attractions',
  templateUrl: './attractions.page.html',
  styleUrls: ['./attractions.page.scss'],
})
export class AttractionsPage implements OnInit {
  public attractions: Attraction[] = [];

  constructor(private httpClient: HttpClient) {
  }

  async ngOnInit() {
    this.attractions = await this.httpClient.get('http://localhost:5555/api/news/resources/attractions', {})
        .toPromise() as Attraction[];
  }

}
