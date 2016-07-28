package info.androidhive.retrofit.rest;

import info.androidhive.retrofit.model.MoviesResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;


public interface ApiInterface {
    @GET("discover/movie")
    Call<MoviesResponse> getTopRatedMovies(@Query("api_key") String apiKey, @Query("with_people") String person, @Query("sort_by") String i);

    @GET("discover/movie")
    Call<MoviesResponse> getMoviesByDate(@Query("api_key") String apiKey, @Query("primary_release_date.gte") String person, @Query("primary_release_date.lte") String i, @Query("sort_by") String j);

    @GET("discover/movie")
    Call<MoviesResponse> getTopRatedMoviesTwoPeople(@Query("api_key") String apiKey, @Query("with_people") String person, @Query("with_cast") String person2, @Query("sort_by") String i);

    @GET("movie/{id}")
    Call<MoviesResponse> getMovieDetails(@Path("id") int id, @Query("api_key") String apiKey);

}
