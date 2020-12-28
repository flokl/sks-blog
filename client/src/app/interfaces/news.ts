import {NewsCategory} from './news-category';
import {Author} from './author';

export interface News {
    id: string;
    title: string;
    text: string;
    publicationDate: string;
    topNews: boolean;

    category: NewsCategory;
    authors: Author[];
}
