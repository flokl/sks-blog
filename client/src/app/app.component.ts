import {Component, OnInit} from '@angular/core';

import {Platform} from '@ionic/angular';
import {SplashScreen} from '@ionic-native/splash-screen/ngx';
import {StatusBar} from '@ionic-native/status-bar/ngx';
import {ActivatedRoute, NavigationEnd, Router} from '@angular/router';

@Component({
    selector: 'app-root',
    templateUrl: 'app.component.html',
    styleUrls: ['app.component.scss']
})
export class AppComponent implements OnInit {
    public currentPath = '/';
    public appPages = [
        {
            title: 'All News',
            url: '/news',
            icon: 'newspaper'
        },
        {
            title: 'Authors',
            url: '/authors',
            icon: 'people'
        },
        {
            title: 'Categories',
            url: '/categories',
            icon: 'layers'
        },
        {
            title: 'Create Entry',
            url: '/create-entry',
            icon: 'add-circle'
        },

    ];

    constructor(
        private platform: Platform,
        private splashScreen: SplashScreen,
        private statusBar: StatusBar,
        private router: Router
    ) {
        this.initializeApp();

        this.router.events.subscribe(event => {
            if (event instanceof NavigationEnd) {
                this.currentPath = '/' + (event as NavigationEnd).url.split('/')[1];
            }
        });
    }

    initializeApp() {
        this.platform.ready().then(() => {
            this.statusBar.styleDefault();
            this.splashScreen.hide();
        });
    }

    ngOnInit() {}
}
