package com.example.firebaseexercises.business.firebase;

import android.util.Log;

import com.example.firebaseexercises.business.model.Message;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

public class MyFirebaseDatabase {

    private static MyFirebaseDatabase sInstance;

    private FirebaseDatabase fdb;
    private DatabaseReference messageListRef;

    private static MutableLiveData<List<Message>> messageList;
    private static ArrayList<Message> tempMessageList;

    /*
     * CONSTRUCTOR & GETINSTANCE FOR SINGLETON
     */

    private MyFirebaseDatabase(){
        messageList = new MutableLiveData<>();
        fdb = FirebaseDatabase.getInstance();
        messageListRef = fdb.getReference().child("messages");
        tempMessageList = new ArrayList<>();
        messageListRef.addValueEventListener(new MyValueEventListener());
    }

    public static MyFirebaseDatabase getInstance(){
        if(sInstance == null){
            synchronized (MyFirebaseDatabase.class){
                if(sInstance == null){
                    sInstance = new MyFirebaseDatabase();
                }
            }
        }
        return sInstance;
    }

    /*
     * MESSAGELIST HANDLING
     */

    public LiveData<List<Message>> getMessageList(){
        return messageList;
    }

    public void addMessage(Message message){
        messageListRef.push().setValue(message);
    }

    public void deleteMessage(String uid){
        messageListRef.child(uid).removeValue();
    }

    private static class MyValueEventListener implements ValueEventListener{

        @Override
        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
            if(dataSnapshot.exists()){
                tempMessageList.clear();
                for(DataSnapshot ds : dataSnapshot.getChildren()){
                    String uid = ds.getKey();
                    String user = ds.child("username").getValue().toString();
                    String content = ds.child("content").getValue().toString();
                    Message message = new Message(uid, user, content);
                    tempMessageList.add(message);
                }

                messageList.postValue(tempMessageList);
            }
        }

        @Override
        public void onCancelled(@NonNull DatabaseError databaseError) {

        }
    }

    /*
     * FirebaseAuthentication
     */
}
