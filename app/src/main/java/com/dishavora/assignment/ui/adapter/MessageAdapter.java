package com.dishavora.assignment.ui.adapter;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.dishavora.assignment.model.ChatMessage;
import com.dishavora.assignment.R;

import java.util.List;

public class MessageAdapter extends RecyclerView.Adapter<MessageAdapter.MyViewHolder> {

    private Activity activity;
    private List<ChatMessage> messages;

    public MessageAdapter(Activity context, List<ChatMessage> objects) {
        this.activity = context;
        this.messages = objects;
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView msg_left, msg_right;
        private LinearLayout linear_left, linear_right;
        public MyViewHolder(View v) {
            super(v);
            linear_left = (LinearLayout) v.findViewById(R.id.linear_left);
            linear_right = (LinearLayout) v.findViewById(R.id.linear_right);
            msg_left = (TextView) v.findViewById(R.id.txt_msg_left);
            msg_right = (TextView) v.findViewById(R.id.txt_msg_right);
        }
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // create a new view
        View v = (View) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_chat, parent, false);
        MyViewHolder vh = new MyViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element

        ChatMessage chatMessage = messages.get(position);
        if(chatMessage.isMine()){
            holder.linear_left.setVisibility(View.VISIBLE);
            holder.linear_right.setVisibility(View.GONE);
            holder.msg_left.setText(chatMessage.getContent());
        } else {
            holder.linear_left.setVisibility(View.GONE);
            holder.linear_right.setVisibility(View.VISIBLE);
            holder.msg_right.setText(chatMessage.getContent());
        }

    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return messages.size();
    }

}
