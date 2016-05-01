package com.audreytroutt.android.androidbeginnerchat.chat;

/**
 * Created by audrey on 5/1/16.
 */
public class ChatMessage {

    String senderAvatarUrl;
    String senderName;
    String message;

    // Required default constructor for Firebase object mapping
    @SuppressWarnings("unused")
    private ChatMessage() {
    }

    ChatMessage(String message, String senderName, String senderAvatarUrl) {
        this.message = message;
        this.senderName = senderName;
        this.senderAvatarUrl = senderAvatarUrl;
    }

    public String getMessage() {
        return message;
    }

    public String getSenderName() {
        return senderName;
    }

    public String getSenderAvatarUrl() {
        return senderAvatarUrl;
    }
}
