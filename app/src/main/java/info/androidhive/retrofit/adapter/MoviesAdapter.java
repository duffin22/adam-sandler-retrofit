package info.androidhive.retrofit.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import info.androidhive.retrofit.R;
import info.androidhive.retrofit.activity.Main2Activity;
import info.androidhive.retrofit.activity.Main3Activity;
import info.androidhive.retrofit.activity.MainActivity;
import info.androidhive.retrofit.model.Movie;

public class MoviesAdapter extends RecyclerView.Adapter<MoviesAdapter.MovieViewHolder> {

    private List<Movie> movies;
    private int rowLayout;
    private Context context;


    public static class MovieViewHolder extends RecyclerView.ViewHolder {
        LinearLayout moviesLayout;
        TextView movieTitle;
        TextView data;
        TextView movieDescription;
        TextView rating;
        View view;


        public MovieViewHolder(View v) {
            super(v);
            moviesLayout = (LinearLayout) v.findViewById(R.id.movies_layout);
            movieTitle = (TextView) v.findViewById(R.id.title);
            data = (TextView) v.findViewById(R.id.subtitle);
            movieDescription = (TextView) v.findViewById(R.id.description);
//            rating = (TextView) v.findViewById(R.id.rating);
            view = v;

        }
    }

    public MoviesAdapter(List<Movie> movies, int rowLayout, Context context) {
        this.movies = movies;
        this.rowLayout = rowLayout;
        this.context = context;
    }

    @Override
    public MoviesAdapter.MovieViewHolder onCreateViewHolder(ViewGroup parent,
                                                            int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(rowLayout, parent, false);
        return new MovieViewHolder(view);
    }


    @Override
    public void onBindViewHolder(MovieViewHolder holder, final int position) {
        holder.movieTitle.setText(movies.get(position).getTitle());
        holder.data.setText(movies.get(position).getReleaseDate());
        holder.movieDescription.setText(movies.get(position).getOverview());
//        holder.rating.setText(movies.get(position).getVoteAverage().toString());

        View camera = (ImageView) holder.view.findViewById(R.id.photo);

        camera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String poster = movies.get(position).getPosterPath();
                Intent i = new Intent(context, Main2Activity.class);

                i.putExtra("id",poster);
                i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

                context.startActivity(i);

            }
        });

        View subtitle = (TextView) holder.view.findViewById(R.id.subtitle);

        subtitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String date = movies.get(position).getReleaseDate();
                Intent i = new Intent(context, Main3Activity.class);

                i.putExtra("date",date);
                i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

                context.startActivity(i);

            }
        });
    }

    @Override
    public int getItemCount() {
        return movies.size();
    }
}