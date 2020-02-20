package com.example.yasmin.redditclone;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteException;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.yasmin.redditclone.model.TopicModel;
import com.example.yasmin.redditclone.util.AddTopicDialog;
import com.example.yasmin.redditclone.util.DatabaseHelper;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements TopicAdapter.TopicListener {

    DatabaseHelper myDB;
    public RecyclerView recyclerView;
    ArrayList<TopicModel> arrayList = new ArrayList<TopicModel>();
    TopicAdapter topicAdapter;
    Cursor cursor;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_main);
        myDB = new DatabaseHelper(this);

        recyclerView = (RecyclerView) findViewById(R.id.listOfTopic);
        LinearLayoutManager linearLayoutManager= new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        topicAdapter = new TopicAdapter(this, arrayList, this);
        recyclerView.setAdapter(topicAdapter);
        onLoadList();

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, AddTopicDialog.class);
                startActivity(i);
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
            }
        });
    }



    private void onLoadList(){

        try{

            myDB.open();
            cursor = myDB.loadData();
            if (cursor != null){
                if (cursor.moveToFirst()){
                    do {
                        TopicModel topicModelList = new TopicModel();
                        topicModelList.setId(Integer.parseInt(cursor.getString(0)));
                        topicModelList.setTitle(cursor.getString(1));
                        topicModelList.setDescription(cursor.getString(2));
                        topicModelList.setUpvote(Integer.parseInt(cursor.getString(3)));
                        topicModelList.setDownvote(Integer.parseInt(cursor.getString(4)));

                        Log.d("CURSOR", "LIst from database----"+ topicModelList.getId());

                        arrayList.add(topicModelList);

                    }while (cursor.moveToNext());
                }
            }

        }catch (SQLiteException e){e.printStackTrace();}




        topicAdapter.notifyDataSetChanged();

        Log.d("TAG", "LIst from database size"+ arrayList.size());
    }

    @Override
    public void onTopicClick(int position) {
        Log.d("TAG", "ontopicclick "+arrayList.get(position).getId());
//        arrayList.get(position);
        Intent i = new Intent(MainActivity.this, TopicDetails.class);
        i.putExtra("idTopic", ""+arrayList.get(position).getId());
        i.putExtra("title", arrayList.get(position).getTitle());
        i.putExtra("description", arrayList.get(position).getDescription());
        i.putExtra("upvote", ""+arrayList.get(position).getUpvote());
        i.putExtra("downvote", ""+arrayList.get(position).getDownvote());
        startActivity(i);

    }

    @Override
    public void onResume(){
        super.onResume();
        Log.d("BACK", "HERE");
        arrayList.clear();
        onLoadList();
        topicAdapter.refresh(arrayList);

    }

}
