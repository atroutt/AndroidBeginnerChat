package com.audreytroutt.android.androidbeginnerchat.chat;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.audreytroutt.android.androidbeginnerchat.R;

import butterknife.ButterKnife;

/**
 * Created by audrey on 5/1/16.
 */
public class ChatAdapter extends RecyclerView.Adapter<ChatAdapter.ViewHolder> {
    private ChatMessage[] mDataset;

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class ViewHolder extends RecyclerView.ViewHolder {

        public View mChatBox;

        public ViewHolder(View chatBox) {
            super(chatBox);
            mChatBox = chatBox;
        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public ChatAdapter(ChatMessage[] myDataset) {
        mDataset = myDataset;
    }

    @Override
    public ChatAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_chat, parent, false);
        // set the view's size, margins, paddings and layout parameters

        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        // ChatMessage chatMessages = mDataset[position];
        TextView senderLabel = ButterKnife.findById(holder.mChatBox, R.id.chat_sender_name);
        senderLabel.setText("Sender" + position);
        TextView messageText = ButterKnife.findById(holder.mChatBox, R.id.chat_message);
        messageText.setText("This is message " + position + ". Here's some more text." + "This is message " + position + ". Here's some more text." + "This is message " + position + ". Here's some more text.");
    }

    @Override
    public int getItemCount() {
        return 28; //mDataset.length;
    }

}
