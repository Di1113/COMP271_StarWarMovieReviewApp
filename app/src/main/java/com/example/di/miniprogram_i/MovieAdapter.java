package com.example.di.miniprogram_i;

import android.content.Context;
import android.graphics.Color;
import android.provider.ContactsContract;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.ViewDragHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import com.squareup.picasso.Picasso;


/**
 * Created by di on 2/10/18.
 */

public class MovieAdapter extends BaseAdapter {

    //adapter takes the app itself and a list of data to display
    private Context mContext;
    private ArrayList<Movie> mMovieList;
    private LayoutInflater mInflator;

    //constructors
    public MovieAdapter(Context context, ArrayList<Movie> movieList) {

        //initialize instance variables;
        this.mContext = context;
        this.mMovieList = movieList;
        mInflator = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    //methods that need to be overrode

    //return the number of movies in the data source
    @Override
    public int getCount() {
        return mMovieList.size();
    }

    //return the item at specific position in the data source
    @Override
    public Object getItem(int position) {
        return mMovieList.get(position);
    }

    //return the row id associated with the specific position in the list
    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;

        //chech if the view already exist
        //if yes, no need to inflate and findVieById again
        if (convertView == null) {
            //inflate
            convertView = mInflator.inflate(R.layout.list_item, parent, false);
            //and the views to the holder
            viewHolder = new ViewHolder();
            //views
            viewHolder.titleTextView = convertView.findViewById(R.id.movie_list_title);
            viewHolder.descriptionTextView = convertView.findViewById(R.id.movie_list_description);
            viewHolder.charactersTextView = convertView.findViewById(R.id.movie_list_characters);
            viewHolder.hasSeenTextView = convertView.findViewById(R.id.movie_list_hasseen);
            viewHolder.posterImageView = convertView.findViewById(R.id.movie_list_thumbnail);
            //add the holder to the view
            //for future use
            convertView.setTag(viewHolder);
        } else {
            //get the view holder form convertView
            viewHolder = (ViewHolder) convertView.getTag();
        }

        //get relavant subview of the row view
        //what does these do
        TextView titleTextView = viewHolder.titleTextView;
        TextView desTextView = viewHolder.descriptionTextView;
        TextView chaTextView = viewHolder.charactersTextView;
        TextView hasseenTextView = viewHolder.hasSeenTextView;
        ImageView posterImageView = viewHolder.posterImageView;

        //get corresponding recipe for each row
        Movie movie = (Movie) getItem(position);

        //update the row view's textViews and imageView to display the information
        //titleTextView
        titleTextView.setText(movie.title);

        //desTextView
        desTextView.setText(movie.description);

        //chaTextView
        String character1 = movie.mainCharacters.get(0);
        String character2 = movie.mainCharacters.get(1);
        String character3 = movie.mainCharacters.get(2);
        ;
        String threeCharacters = character1 + ", " + character2 + ", " + character3;
        chaTextView.setText(threeCharacters);

        //hasseenTextView
        hasseenTextView.setText(movie.hasSeenStatus);
        hasseenTextView.setTextColor(Color.GRAY);

                if(movie.hasSeenStatus.equals("Already Seen")) {
                    hasseenTextView.setTextColor(ContextCompat.getColor(mContext,R.color.purple));
                }
                else if(movie.hasSeenStatus.equals("Want to see")){
                    hasseenTextView.setTextColor(ContextCompat.getColor(mContext,R.color.colorPrimary));
                }
                else if(movie.hasSeenStatus.equals("Do not like")){
                    hasseenTextView.setTextColor(ContextCompat.getColor(mContext,R.color.colorAccent));
                }
                else if(movie.hasSeenStatus.equals("Has Seen?")){
                    hasseenTextView.setTextColor(Color.GRAY);
                }




        //imageView
        //use picasso library
        Picasso.with(mContext).load(movie.posterURL).into(posterImageView);
        Picasso.with(mContext).setLoggingEnabled(true);

        return convertView;
    }

    //viewHolder
    //is used to customize what you want to put into the view
    //it depends on the layout design of your row
    //this will be a private static class you have to define
    private static class ViewHolder {
        public TextView titleTextView;
        public TextView descriptionTextView;
        public TextView charactersTextView;
        public TextView hasSeenTextView;
        public ImageView posterImageView;
    }
}
