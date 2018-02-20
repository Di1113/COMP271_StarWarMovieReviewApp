package com.example.di.miniprogram_i;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

/**
 * Created by di on 2/19/18.
 */

public class MovieDetailActivity extends AppCompatActivity{
    private Context mContext;
    private TextView titleText;
    private ImageView poster;
    private TextView descriptionText;
    private Button submitButton;
    private boolean checked;
    private String hasSeenStatus;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);

        mContext = this;
        submitButton = findViewById(R.id.button_submit);
        //make button clickable and send intent
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //construct intent
                Intent radioButtonIntent = new Intent();
                //put the boolean values into the intent
                radioButtonIntent.putExtra("seenStatus", hasSeenStatus);
                //send back to main activity
                setResult(RESULT_OK, radioButtonIntent);
                finish();
            }
        });

        //title
        //poster url
        //description
        String title = this.getIntent().getExtras().getString("title");
        String posterURL = this.getIntent().getExtras().getString("poster");
        String description = this.getIntent().getExtras().getString("description");

        //set the title
        setTitle(title);

        //load title
        titleText = findViewById(R.id.detail_activity_title);
        titleText.setText(title);

        //load the poster
        poster = findViewById(R.id.detail_activity_poster);
        Picasso.with(mContext).load(posterURL).into(poster);
        Picasso.with(mContext).setLoggingEnabled(true);

        //load the description
        descriptionText = findViewById(R.id.detail_activity_description);
        descriptionText.setText(description);


    }
    public void onRadioButtonClicked(View view) {

        checked = ((RadioButton) view).isChecked();

        // Check which radio button was clicked
        switch(view.getId()) {
            case R.id.radioButton_alreadyseen:
                if (checked)
                    hasSeenStatus = "Already Seen";
                    break;
            case R.id.radioButton_wanttosee:
                if (checked)
                    hasSeenStatus = "Want to see";
                    break;
            case R.id.radioButton_donotlike:
                if (checked)
                    hasSeenStatus = "Do not like";
                    break;
        }
    }
}
