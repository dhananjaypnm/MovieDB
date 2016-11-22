package com.dhananjay.spiderappdevt3;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;
import com.j256.ormlite.android.apptools.OpenHelperManager;
import com.j256.ormlite.dao.Dao;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import java.io.ByteArrayOutputStream;
import java.sql.SQLException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity implements Callback<MovieDetails> {

    DatabaseHelper helper;
    Dao<MovieDetails,Long> dao;
    String TAG="mainactivity";
    EditText editText;
    String string=new String();
    Retrofit retrofit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_main);
        editText= (EditText) findViewById(R.id.editText);
        Gson gson=new Gson();
       retrofit= new Retrofit.Builder()
                .baseUrl("http://www.omdbapi.com/")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        try {
            helper= OpenHelperManager.getHelper(MainActivity.this,DatabaseHelper.class);
            dao=helper.getDao();
        } catch (SQLException e) {
            e.printStackTrace();
        }


       
        

    }

    @Override
    public void onResponse(Call<MovieDetails> call, Response<MovieDetails> response) {
        final MovieDetails movie=response.body();
        Target target=new Target() {
            @Override
            public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
                ByteArrayOutputStream stream = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
                movie.imagebytes = stream.toByteArray();
                Log.d(TAG, "onBitmapLoaded: bmp done");

                try {
                    MovieDetails movie2= dao.createIfNotExists(movie);
                    Log.d(TAG, "onBitmapLoaded: "+ movie2.id+movie2.title);


                } catch (SQLException e) {
                    e.printStackTrace();
                }
                Intent intent=new Intent(MainActivity.this,Main2Activity.class);
                startActivity(intent);
            }

            @Override
            public void onBitmapFailed(Drawable errorDrawable) {

            }

            @Override
            public void onPrepareLoad(Drawable placeHolderDrawable) {

            }
        };
        Picasso.with(this).load(movie.poster).into(target);
    }

    @Override
    public void onFailure(Call<MovieDetails> call, Throwable t) {
        Log.d(TAG, "onFailure: failed"+t);
        Toast.makeText(this,"Failed! Make sure you have proper internet connection",Toast.LENGTH_LONG).show();
    }

    public void navigate(View view){
        string=editText.getText().toString();
        List<MovieDetails> list;
        try {
            list=dao.queryForEq("title",string);
            if (list.size()==0){
                Log.d(TAG, "onCreate: not found");
                Toast.makeText(this,"Downloading",Toast.LENGTH_SHORT).show();
                MovieDetailsInterface movieDetailsInterface=retrofit.create(MovieDetailsInterface.class);
                Call<MovieDetails> call =movieDetailsInterface.getDetails(string);
                call.enqueue(this);
            }else {
                Toast.makeText(MainActivity.this, "Already found offline", Toast.LENGTH_SHORT).show();
                database(new View(this));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void database(View view){
        Intent intent=new Intent(this,Main2Activity.class);
        startActivity(intent);
    }
}
