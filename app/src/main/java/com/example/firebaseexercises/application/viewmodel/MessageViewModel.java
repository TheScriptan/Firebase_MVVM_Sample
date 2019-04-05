package com.example.firebaseexercises.application.viewmodel;

import com.example.firebaseexercises.business.model.Message;
import com.example.firebaseexercises.business.repository.MessageRepository;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

public class MessageViewModel extends ViewModel {

    private LiveData<List<Message>> messageListData;
    private final MessageRepository repository;

    public MessageViewModel(MessageRepository repository){
        this.repository = repository;
        messageListData = repository.getMessageList();
    }

    public LiveData<List<Message>> getMessageList() {
        return this.messageListData;
    }

    public void addMessage(Message message){
        repository.addMessage(message);
    }

    public void updateMessage(Message message) {repository.updateMessage(message);}

    public void deleteMessage(String uid){
        repository.deleteMessage(uid);
    }

}
