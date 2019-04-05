package com.example.firebaseexercises.application.activity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.EditText;

import com.example.firebaseexercises.R;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

public class EditMessageDialogFragment extends DialogFragment {

    private EditText newMessageText;

    public EditMessageDialogFragment(){

    }

    public static EditMessageDialogFragment newInstance(String title){
        EditMessageDialogFragment frag = new EditMessageDialogFragment();
        Bundle args = new Bundle();
        args.putString("title", title);
        frag.setArguments(args);
        return frag;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.dialog_edit_message, container);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        newMessageText = view.findViewById(R.id.message_new_text);
        String title = getArguments().getString("title", "Edit Message");
        getDialog().setTitle(title);
        newMessageText.requestFocus();
        getDialog().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
    }
}
