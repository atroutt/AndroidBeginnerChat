package com.audreytroutt.android.beginner.chat;

import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import it.slyce.messaging.SlyceMessagingFragment;
import it.slyce.messaging.listeners.LoadMoreMessagesListener;
import it.slyce.messaging.listeners.UserSendsMessageListener;
import it.slyce.messaging.message.Message;
import it.slyce.messaging.message.MessageSource;
import it.slyce.messaging.message.TextMessage;

import com.audreytroutt.android.beginner.chat.message.ChatMessage;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Exclude;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.IgnoreExtraProperties;
import com.google.firebase.database.Query;
import com.firebase.ui.database.*;
import com.google.firebase.database.ValueEventListener;

public class ChatActivity extends AppCompatActivity {

    private DatabaseReference mDatabase;
    private List<DataSnapshot> mMessages = new ArrayList<DataSnapshot>();
    SlyceMessagingFragment slyceMessagingFragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        mDatabase = FirebaseDatabase.getInstance().getReference();
        Query ref = mDatabase.child("posts").startAt(mMessages.size()).limitToFirst(20);

        ref.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                mMessages.add(dataSnapshot);

                ChatMessage newPost = dataSnapshot.getValue(ChatMessage.class);
                TextMessage welcomeMessage = new TextMessage();
                welcomeMessage.setAvatarUrl("https://cfcdnpull-creativefreedoml.netdna-ssl.com/wp-content/uploads/2013/03/00-android-4-0_icons.png");
                welcomeMessage.setText(newPost.body);
                welcomeMessage.setDate(System.currentTimeMillis());
                if (newPost.author.equals("other")) {
                    welcomeMessage.setSource(MessageSource.EXTERNAL_USER);
                } else {
                    welcomeMessage.setSource(MessageSource.LOCAL_USER);
                }
                slyceMessagingFragment.addNewMessage(welcomeMessage);
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                // ignore for now
            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {
                // ignore for now
            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {
                // ignore for now
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


        slyceMessagingFragment = (SlyceMessagingFragment) getFragmentManager().findFragmentById(R.id.messaging_fragment);
        slyceMessagingFragment.setDefaultAvatarUrl("https://cfcdnpull-creativefreedoml.netdna-ssl.com/wp-content/uploads/2013/03/00-android-4-0_icons.png");
        slyceMessagingFragment.setDefaultDisplayName("Android Beginner");
        slyceMessagingFragment.setMoreMessagesExist(false);
        slyceMessagingFragment.setLoadMoreMessagesListener(new LoadMoreMessagesListener() {
            @Override
            public List<Message> loadMoreMessages() {
                return new ArrayList<Message>();
            }
        });

        TextMessage welcomeMessage = new TextMessage();
        welcomeMessage.setAvatarUrl("https://cfcdnpull-creativefreedoml.netdna-ssl.com/wp-content/uploads/2013/03/00-android-4-0_icons.png");
        welcomeMessage.setText("Welcome to beginner chat!");
        welcomeMessage.setDate(System.currentTimeMillis());
        welcomeMessage.setSource(MessageSource.EXTERNAL_USER);
        welcomeMessage.setDisplayName("Android Friend");
        slyceMessagingFragment.addNewMessage(welcomeMessage);

        slyceMessagingFragment.setOnSendMessageListener(new UserSendsMessageListener() {
            @Override
            public void onUserSendsTextMessage(String text) {
                Log.d(getClass().getCanonicalName(), "onUserSendsTextMessage " + text);
            }

            @Override
            public void onUserSendsMediaMessage(Uri imageUri) {
                Log.d(getClass().getCanonicalName(), "onUserSendsMediaMessage " + imageUri.toString());
            }
        });
    }
}

