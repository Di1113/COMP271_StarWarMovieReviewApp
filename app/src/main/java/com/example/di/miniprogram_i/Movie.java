package com.example.di.miniprogram_i;

import android.content.Context;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.InputStream;
import java.util.ArrayList;

/**
 * Created by di on 2/10/18.
 */

public class Movie {
    //instance variables; set private in usual case with set and get methods
    public String title;
    public String posterURL;
    public String description;
    public String url;
    public String hasSeenStatus;
    public ArrayList<String> mainCharacters;
    public int episodeNo;

    //default empty constructor

    //static method that reads json file in
    public static ArrayList<Movie> getMovieFromFile(String filename, Context context){
        ArrayList<Movie> movieList = new ArrayList<Movie>();

        //try to read JSON file
        //get information by using tags
        //construct a Movie object for each movie in JSON
        //add the object to array list
        //return arrayList
        try{
            String jsonString = loadJsonFromAsset("movies.json", context);
            JSONObject json = new JSONObject(jsonString);
            JSONArray movies = json.getJSONArray("movies");

            //for loop to go through each movie in the movie array
            for(int i = 0; i < movies.length(); i++){
                Movie movie = new Movie();
                movie.hasSeenStatus = "Has Seen?";
                movie.title = movies.getJSONObject(i).getString("title");
                movie.description = movies.getJSONObject(i).getString("description");
                movie.posterURL = movies.getJSONObject(i).getString("poster");
                movie.url = movies.getJSONObject(i).getString("url");
                movie.episodeNo = movies.getJSONObject(i).getInt("episode_number");
                JSONArray characternames = movies.getJSONObject(i).getJSONArray("main_characters");

                ArrayList<String> names = new ArrayList<String>();
                if(characternames != null){
                    int size = characternames.length();
                    for(int j = 0; j < size; j++){
                        names.add(characternames.get(j).toString());
                    }
                }
//                JSONArray characters = json.getJSONArray("main_characters");
//                    for(int ii = 0; ii < characters.length(); ii++){
//                        String main_character = characters.getString(ii);
//                        movie.mainCharacters[ii] = main_character;
//                    }
//
//                for(int jj = 0; jj < 3; jj++){
//                    movie.characters += movie.mainCharacters[jj] + ", ";
//                }

                movie.mainCharacters = names;
                //add to arrayList
                movieList.add(movie);

            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return movieList;
    }

    //helper method that loads json files
    private static String loadJsonFromAsset(String filename, Context context) {
        String json = null;

        try {
            InputStream is = context.getAssets().open(filename);
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        }
        catch (java.io.IOException ex) {
            ex.printStackTrace();
            return null;
        }

        return json;
    }

}
