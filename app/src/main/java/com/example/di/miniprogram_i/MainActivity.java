package com.example.di.miniprogram_i;

import android.content.Context;
import android.content.Intent;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ListView mListView;
    private Context mContext;

    //data to display
    private ArrayList<Movie> movieList;

    //create the adapter
    private MovieAdapter movieAdapter;

    private Movie selectedMovie;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mContext = this;

        movieList = Movie.getMovieFromFile("movies.json",this);
        movieAdapter = new MovieAdapter(this, movieList);


        //find the listView in the layout
        //set the adapter to listView
        mListView = findViewById(R.id.movie_list);
        mListView.setAdapter(movieAdapter);

        //set rows to be clickable
        //when the row is clicked
        //the intent is created and send

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                 selectedMovie = movieList.get(position);

                //create my intent package and all the info needed for detail page
                //call start activity method

                //1.
                //explicit intent; parameter(from, to)
                Intent movieDetailAcitivityIntent = new Intent(mContext, MovieDetailActivity.class);

                //2.
                //add data of title, poster and description to the activity
                movieDetailAcitivityIntent.putExtra("title", selectedMovie.title);
                movieDetailAcitivityIntent.putExtra("description", selectedMovie.description);
                movieDetailAcitivityIntent.putExtra("poster", selectedMovie.posterURL);

                //3.
                //start the activity
                startActivityForResult(movieDetailAcitivityIntent, 1);

            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {

                String updateSeenStatus = data.getStringExtra("seenStatus");
                selectedMovie.hasSeenStatus = updateSeenStatus;

            //prof ask to write it…
            movieAdapter.notifyDataSetChanged();
            }
        }
    }

