import { Component, OnInit } from '@angular/core';
import { MoviesService } from '../../services/movies.service';
import { Observable } from 'rxjs';
import { Movie } from '../../models/movie';

@Component({
  selector: 'app-movie-list',
  templateUrl: './movie-list.component.html',
  styleUrls: ['./movie-list.component.scss']
})
export class MovieListComponent implements OnInit {
  public movies$: Observable<Movie[]>;

  constructor(private moviesService: MoviesService) { }

  ngOnInit() {
    this.refreshMovies();
  }

  addMovie(form) {
    const movie:Movie = new Movie(form.value.title, form.value.rating);
    this.moviesService.addMovie(movie)
    .subscribe(
      movie => console.log('Next value: ', movie),
      err => console.error('Error: ', err),
      () => {
        console.log('Got a complete notification')
        this.refreshMovies();
      }
    );
    form.reset();
  }

  private refreshMovies(): void {
    this.movies$ = this.moviesService.getMovies();
  }
}
