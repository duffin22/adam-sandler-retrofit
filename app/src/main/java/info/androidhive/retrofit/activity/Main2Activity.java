package info.androidhive.retrofit.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import com.koushikdutta.ion.Ion;

import info.androidhive.retrofit.R;

public class Main2Activity extends AppCompatActivity {

    ImageView image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        Intent i = getIntent();
        String poster = i.getStringExtra("id");

        image = (ImageView) findViewById(R.id.imageView);

        String url = "http://image.tmdb.org/t/p/w500/"+poster;

        Ion.with(image)
                .load(url);
    }
}
