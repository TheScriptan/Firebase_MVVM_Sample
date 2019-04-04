package com.example.firebaseexercises.business.repository;

import com.example.firebaseexercises.business.firebase.MyFirebaseDatabase;
import com.example.firebaseexercises.business.model.Message;
import com.example.firebaseexercises.utils.AppExecutors;

import java.util.List;

import androidx.lifecycle.LiveData;

public class MessageRepository {

    private static MessageRepository sInstance;
    private final MyFirebaseDatabase fdb;
    private final AppExecutors executors;

    private MessageRepository(MyFirebaseDatabase fdb, AppExecutors executors){
        this.fdb = fdb;
        this.executors = executors;
    }

    public static MessageRepository getInstance(MyFirebaseDatabase fdb, AppExecutors executors){
        if(sInstance == null){
            synchronized (MessageRepository.class){
                if(sInstance == null){
                    sInstance = new MessageRepository(fdb, executors);
                }
            }
        }
        return sInstance;
    }

    public LiveData<List<Message>> getMessageList(){
        return fdb.getMessageList();
    }

    public void addMessage(Message message){
        fdb.addMessage(message);
    }

    public void deleteMessage(String uid){
        fdb.deleteMessage(uid);
    }
}
