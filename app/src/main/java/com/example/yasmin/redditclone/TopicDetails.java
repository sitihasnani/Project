package com.example.yasmin.redditclone;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.yasmin.redditclone.util.DatabaseHelper;

public class TopicDetails extends AppCompatActivity {

    TextView titleTxt, descriptionTxt;
    Button upvoteBtn, downvoteBtn;
    Intent intent;
    int upvoteClick, downvoteClick, idTopic;
    DatabaseHelper myDB;
    Cursor cursor;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_topic_details);

        myDB = new DatabaseHelper(this);

        titleTxt = (TextView) findViewById(R.id.titleTxt);
        descriptionTxt = (TextView) findViewById(R.id.descriptionTxt);

        upvoteBtn = (Button) findViewById(R.id.upvoteBtn);
        downvoteBtn = (Button) findViewById(R.id.downvoteBtn);

        intent = getIntent();
        titleTxt.setText(intent.getStringExtra("title"));
        descriptionTxt.setText(intent.getStringExtra("description"));

        upvoteClick = Integer.parseInt(intent.getStringExtra("upvote"));
        downvoteClick = Integer.parseInt(intent.getStringExtra("downvote"));
        idTopic = Integer.parseInt(intent.getStringExtra("idTopic"));

        upvoteBtn.setText("Upvote "+ intent.getStringExtra("upvote"));
        upvoteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                upvoteCounter();
            }
        });

        downvoteBtn.setText("Downvote "+ intent.getStringExtra("downvote"));
        downvoteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                downvoteCounter();
            }
        });
    }

    private void downvoteCounter() {
        downvoteClick++;
        runOnUiThread(new Runnable() {
            public void run() {

                downvoteBtn.setText("Downvote "+ String.valueOf(downvoteClick));
                myDB.open();
                cursor = myDB.updateDownvote(downvoteClick, idTopic);
            }
        });
    }

    private void upvoteCounter() {
        upvoteClick++;
        runOnUiThread(new Runnable() {
            public void run() {

                upvoteBtn.setText("Upvote "+ String.valueOf(upvoteClick));
                myDB.open();
                cursor = myDB.updateUpvote(upvoteClick, idTopic);
            }
        });

    }
}
