package com.example.firebaseexercises.utils;

import com.example.firebaseexercises.application.viewmodel.MessageViewModelFactory;
import com.example.firebaseexercises.business.firebase.MyFirebaseDatabase;
import com.example.firebaseexercises.business.repository.MessageRepository;

public class InjectorUtils {

    private static MessageRepository provideMessageRepository(){
        MyFirebaseDatabase fdb = provideFirebaseDatabase();
        AppExecutors executors = AppExecutors.getInstance();
        return MessageRepository.getInstance(fdb, executors);
    }

    public static MyFirebaseDatabase provideFirebaseDatabase(){
        return MyFirebaseDatabase.getInstance();
    }

    public static MessageViewModelFactory provideMessageViewModelFactory(){
        MessageRepository repository = provideMessageRepository();
        return new MessageViewModelFactory(repository);
    }
}
