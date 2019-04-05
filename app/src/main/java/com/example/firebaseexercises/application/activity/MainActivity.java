package com.example.firebaseexercises.application.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.firebaseexercises.application.MessageAdapter;
import com.example.firebaseexercises.application.viewmodel.MessageViewModel;
import com.example.firebaseexercises.application.viewmodel.MessageViewModelFactory;
import com.example.firebaseexercises.business.model.Message;
import com.example.firebaseexercises.R;
import com.example.firebaseexercises.utils.InjectorUtils;
import com.google.firebase.auth.FirebaseAuth;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //UI Setup
        EditText editText = findViewById(R.id.editText);
        Button button = findViewById(R.id.button);
        button.setEnabled(false);

        //ViewModel setup
        MessageViewModelFactory messageViewModelFactory = InjectorUtils.provideMessageViewModelFactory();
        MessageViewModel messageViewModel = ViewModelProviders.of(this, messageViewModelFactory).get(MessageViewModel.class);

        //RecyclerView setup
        RecyclerView recyclerView = findViewById(R.id.stringRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        MessageAdapter mAdapter = new MessageAdapter(messageViewModel);
        recyclerView.setAdapter(mAdapter);

        messageViewModel.getMessageList().observe(this, mAdapter::setMessageList);

        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (TextUtils.isEmpty(s)) {
                    button.setEnabled(false);
                } else {
                    button.setEnabled(true);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        editText.setFilters(new InputFilter[]{new InputFilter.LengthFilter(15)});

        button.setOnClickListener((View v) -> {
            if (!TextUtils.isEmpty(editText.getText())) {
                Message message = new Message(InjectorUtils.provideFirebaseDatabase().getLoggedUser().getEmail(), editText.getText().toString());
                messageViewModel.addMessage(message);
                editText.setText("");
            }
        });
    }

    @Override
    protected void onStop() {
        super.onStop();
        FirebaseAuth.getInstance().signOut();
    }
}
