package com.audreytroutt.android.androidbeginnerchat;

import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import it.slyce.slyce_messaging.messenger.SlyceMessagingFragment;
import it.slyce.slyce_messaging.messenger.listeners.ShouldLoadMoreMessagesListener;
import it.slyce.slyce_messaging.messenger.listeners.UserSendsMessageListener;
import it.slyce.slyce_messaging.messenger.message.Message;
import it.slyce.slyce_messaging.messenger.message.MessageSource;
import it.slyce.slyce_messaging.messenger.message.TextMessage;

public class ChatActivity extends AppCompatActivity {

    SlyceMessagingFragment slyceMessagingFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        slyceMessagingFragment = (SlyceMessagingFragment) getFragmentManager().findFragmentById(R.id.messaging_fragment);
        slyceMessagingFragment.setDefaultAvatarUrl("https://cfcdnpull-creativefreedoml.netdna-ssl.com/wp-content/uploads/2013/03/00-android-4-0_icons.png");
        slyceMessagingFragment.setDefaultDisplayName("Android Beginner");
        slyceMessagingFragment.setMoreMessagesExist(false);
        slyceMessagingFragment.setShouldLoadMoreMessagesListener(new ShouldLoadMoreMessagesListener() {
            @Override
            public List<Message> shouldLoadMoreMessages() {
                return new ArrayList<Message>();
            }
        });

        TextMessage welcomeMessage = new TextMessage();
        welcomeMessage.setAvatarUrl("https://cfcdnpull-creativefreedoml.netdna-ssl.com/wp-content/uploads/2013/03/00-android-4-0_icons.png");
        welcomeMessage.setText("Welcome to beginner chat!");
        welcomeMessage.setDate(System.currentTimeMillis());
        welcomeMessage.setOrigin(MessageSource.EXTERNAL_USER);
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
