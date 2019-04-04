package com.example.firebaseexercises.application.viewmodel;

import com.example.firebaseexercises.business.repository.MessageRepository;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

public class MessageViewModelFactory extends ViewModelProvider.NewInstanceFactory {

    private final MessageRepository repository;

    public MessageViewModelFactory(MessageRepository repository){
        this.repository = repository;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        //noinspection unchecked
        return (T) new MessageViewModel(repository);
    }
}
