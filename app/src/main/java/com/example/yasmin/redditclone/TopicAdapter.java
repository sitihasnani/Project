package com.example.yasmin.redditclone;

import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.yasmin.redditclone.model.TopicModel;
import com.example.yasmin.redditclone.util.AddTopicDialog;

import java.util.Collections;
import java.util.List;

public class TopicAdapter extends RecyclerView.Adapter<TopicViewHolder> {
    Activity activity;
    List<TopicModel> topicList ;
    TopicListener topicListener;

    public TopicAdapter(Activity activity, List<TopicModel> topicModels, TopicListener topicListener){
        this.activity = activity;
        this.topicList = topicModels;
        this.topicListener = topicListener;
    }

    @NonNull
    @Override
    public TopicViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_topic, parent, false);
        return new TopicViewHolder(view, topicListener);
    }

    @Override
    public void onBindViewHolder(@NonNull TopicViewHolder holder, int position) {
        holder.titleTxt.setText(topicList.get(position).getTitle());
        holder.descriptionTxt.setText(topicList.get(position).getDescription());
        holder.upvoteTxt.setText(""+topicList.get(position).getUpvote());
        holder.downvoteTxt.setText(""+topicList.get(position).getDownvote());


    }

    @Override
    public int getItemCount() {
        Log.i("Adapter", ""+topicList.size() );
        return topicList.size();
    }

    public interface TopicListener{
        void onTopicClick(int position);

    }

    public void refresh(List<TopicModel> items)
    {
        this.topicList = items;
        notifyDataSetChanged();
    }

}
