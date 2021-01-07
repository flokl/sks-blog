import {NewsCategory} from './news-category';

export interface StatisticEntry {
    id: string;
    date: Date;
    viewCount: number;
    category: NewsCategory;
}
