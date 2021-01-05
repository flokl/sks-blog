import {NewsCategory} from './news-category';
import {Author} from './author';

export interface News {
    id: string;
    title: string;
    text: string;
    publicationDate: Date;
    topNews: boolean;

    category: NewsCategory;
    authors: Author[];
}
