package com.dhananjay.spiderappdevt3;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;

import com.j256.ormlite.android.apptools.OpenHelperManager;
import com.j256.ormlite.dao.Dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Main2Activity extends AppCompatActivity implements AdapterView.OnItemClickListener{

    String TAG="Main2Activity";

    GridView gridView;
    List<MovieDetails> movieDetailsList=new ArrayList<>();
    List<MovieGridItem> gridItemList=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        gridView= (GridView) findViewById(R.id.gridView);
        gridView.setOnItemClickListener(this);
        DatabaseHelper helper= OpenHelperManager.getHelper(this,DatabaseHelper.class);
        try {
            Dao<MovieDetails,Long> dao=helper.getDao();
            movieDetailsList=dao.queryForAll();
            Log.d(TAG, "onCreate: "+movieDetailsList.size());

        } catch (SQLException e) {
            e.printStackTrace();
        }

        for(int i=0;i<movieDetailsList.size();i++){
            MovieGridItem item=new MovieGridItem();
            item.genre=movieDetailsList.get(i).genre;
            item.imageBytes=movieDetailsList.get(i).imagebytes;
            item.title=movieDetailsList.get(i).title;
            gridItemList.add(item);
        }
        gridView.setAdapter(new MovieGridAdapter(this,gridItemList));


    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        Intent intent=new Intent(this,Main3Activity.class);
        TextView tv= (TextView) view.findViewById(R.id.textView);
        String s= tv.getText().toString();
        intent.putExtra("movieName",s);
        startActivity(intent);
    }
}
