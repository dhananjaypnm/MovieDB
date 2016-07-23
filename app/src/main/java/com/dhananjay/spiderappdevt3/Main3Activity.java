package com.dhananjay.spiderappdevt3;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.j256.ormlite.android.apptools.OpenHelperManager;
import com.j256.ormlite.dao.Dao;

import java.sql.SQLException;
import java.util.List;

public class Main3Activity extends AppCompatActivity {
    DatabaseHelper helper;
    Dao<MovieDetails,Long> dao;
    String movieName;
    Bundle bundle;
    ImageView poster;
    TextView title;
    TextView year;
    TextView rated;
    TextView released;
    TextView runtime;
    TextView genre;
    TextView director;
    TextView writer;
    TextView language;
    TextView country;
    TextView metaScore;
    TextView imdbRating;

    private List<MovieDetails> movieDetails;
    MovieDetails details;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        title= (TextView) findViewById(R.id.title);
        year= (TextView) findViewById(R.id.year);
        rated= (TextView) findViewById(R.id.rated);
        released= (TextView) findViewById(R.id.released);
        runtime= (TextView) findViewById(R.id.runTime);
        genre= (TextView) findViewById(R.id.genre);
        director= (TextView) findViewById(R.id.director);
        writer= (TextView) findViewById(R.id.writer);
        language= (TextView) findViewById(R.id.language);
        country= (TextView) findViewById(R.id.country);
        metaScore= (TextView) findViewById(R.id.metaScore);
        imdbRating= (TextView) findViewById(R.id.imdbRating);
        poster= (ImageView) findViewById(R.id.poster);
        try {
            helper= OpenHelperManager.getHelper(Main3Activity.this,DatabaseHelper.class);
            dao=helper.getDao();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        bundle=getIntent().getExtras();
       movieName=bundle.getString("movieName","Titanic");
        try {
            movieDetails=dao.queryForEq("title",movieName);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        details=movieDetails.get(0);
        title.setText(details.title);
        year.setText(details.year);
        rated.setText(details.rated);
        released.setText(details.released);
        runtime.setText(details.runtime);
        genre.setText(details.genre);
        director.setText(details.director);
        writer.setText(details.writer);
        language.setText(details.language);
        country.setText(details.country);
        metaScore.setText(details.metascore);
        imdbRating.setText(details.imdbrating);
        Bitmap bmp= BitmapFactory.decodeByteArray(details.imagebytes,0,details.imagebytes.length);
        poster.setImageBitmap(bmp);

    }
}
