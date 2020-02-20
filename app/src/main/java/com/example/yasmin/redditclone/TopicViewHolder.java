package com.example.yasmin.redditclone;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class TopicViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    TextView titleTxt, descriptionTxt, upvoteTxt, downvoteTxt;
    TopicAdapter.TopicListener topicListener;

    public TopicViewHolder(View itemView, TopicAdapter.TopicListener topicListener) {
        super(itemView);

        titleTxt = (TextView) itemView.findViewById(R.id.titleTxt);
        descriptionTxt = (TextView) itemView.findViewById(R.id.descriptionTxt);
        upvoteTxt = (TextView) itemView.findViewById(R.id.upvoteTxt);
        downvoteTxt = (TextView) itemView.findViewById(R.id.downvoteTxt);

        this.topicListener = topicListener;

        itemView.setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {
        topicListener.onTopicClick(getAdapterPosition());

    }
}
