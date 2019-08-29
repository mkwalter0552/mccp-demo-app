import { Injectable } from '@angular/core';
import { Observable, of } from 'rxjs';
import { HttpClient } from '@angular/common/http';

import { Movie } from '../models/movie';

@Injectable({
  providedIn: 'root'
})
export class MoviesService {

  constructor(public http: HttpClient) { }

  getMovies(): Observable<Movie[]>{
    const endpoint = '/movie';
    return this.http.get<Movie[]>(endpoint);
  }

  addMovie(movie: Movie): Observable<Movie> {
    const endpoint = '/movie';
    console.log('endpoint:', endpoint);
    console.log('payload:', movie);
    return this.http.post<Movie>(endpoint, movie);
  }
}
