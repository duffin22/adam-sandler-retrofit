package info.androidhive.retrofit.activity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.List;

import info.androidhive.retrofit.R;
import info.androidhive.retrofit.adapter.MoviesAdapter;
import info.androidhive.retrofit.model.Movie;
import info.androidhive.retrofit.model.MoviesResponse;
import info.androidhive.retrofit.rest.ApiClient;
import info.androidhive.retrofit.rest.ApiInterface;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();
    LinearLayoutManager layoutManager;


    // TODO - insert your themoviedb.org API KEY here
    private final static String API_KEY = "7c5bbe3c272bcc46bd4e18afb35aabe5";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final MediaPlayer myMediaPlayer = MediaPlayer.create(MainActivity.this, R.raw.noise);
        //Button related to play btn
        View myButtonOne = (ImageView) findViewById(R.id.adam);
        myButtonOne.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                myMediaPlayer.start();

            }
        });

        if (API_KEY.isEmpty()) {
            Toast.makeText(getApplicationContext(), "Please obtain your API KEY from themoviedb.org first!", Toast.LENGTH_LONG).show();
            return;
        }

        final RecyclerView recyclerView = (RecyclerView) findViewById(R.id.movies_recycler_view);

        layoutManager = new LinearLayoutManager(this);

        recyclerView.setLayoutManager(layoutManager);

        ApiInterface apiService =
                ApiClient.getClient().create(ApiInterface.class);

        Call<MoviesResponse> call = apiService.getTopRatedMovies(API_KEY, "19292", "release_date.desc");
//        Call<MoviesResponse> call = apiService.getTopRatedMoviesTwoPeople(API_KEY, "488","3","release_date.desc");
        call.enqueue(new Callback<MoviesResponse>() {
            @Override
            public void onResponse(Call<MoviesResponse> call, Response<MoviesResponse> response) {
                int statusCode = response.code();
                List<Movie> movies = response.body().getResults();
                recyclerView.setAdapter(new MoviesAdapter(movies, R.layout.list_item_movie, getApplicationContext()));
            }

            @Override
            public void onFailure(Call<MoviesResponse> call, Throwable t) {
                // Log error here since request failed
                Log.e(TAG, t.toString());
            }
        });

//        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
//
//            @Override
//            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
//
//                if(dy > 0) {
//
//                    int visibleItemCount = layoutManager.getChildCount();
//                    int totalItemCount = layoutManager.getItemCount();
//                    int pastVisiblesItems = layoutManager.findFirstVisibleItemPosition();
//
//
//                    if ( (visibleItemCount + pastVisiblesItems) >= totalItemCount-5) {
//                        Toast.makeText(MainActivity.this,"We got to the bottom!", Toast.LENGTH_SHORT).show();
//                    }
//                }
//            }
//        });
    }
}
