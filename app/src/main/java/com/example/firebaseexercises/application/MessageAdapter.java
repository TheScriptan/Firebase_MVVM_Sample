package com.example.firebaseexercises.application;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.firebaseexercises.business.model.Message;
import com.example.firebaseexercises.R;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MessageAdapter extends RecyclerView.Adapter<MessageAdapter.ViewHolder> {

    //MessageAdapter
    private View.OnClickListener onItemClickListener;
    private List<Message> messageList;

    public MessageAdapter(){
        messageList = new ArrayList<>();
    }

    public void setMessageList(List<Message> newList){
        messageList = newList;
        notifyDataSetChanged();
    }

    public void addMessage(Message message){
        messageList.add(message);
        notifyDataSetChanged();
    }

    public void removeMessage(int position){
        messageList.remove(position);
        notifyDataSetChanged();
    }

    public void setItemClickListener(View.OnClickListener clickListener){
        onItemClickListener = clickListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.message_list_item, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    public Message getMessageByIndex(int position){
        if(messageList.get(position) == null){
            return null;
        }
        return messageList.get(position);
    }

    @Override
    public int getItemCount() {
        return messageList.size();
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.messageUser.setText(messageList.get(position).getId());
        holder.messageUser.setText(messageList.get(position).getUsername());
        holder.messageContent.setText(messageList.get(position).getContent());
    }

    //ViewHolder
    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView messageContent;
        TextView messageUser;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            messageUser = itemView.findViewById(R.id.msg_username);
            messageContent = itemView.findViewById(R.id.msg_content);
            itemView.setTag(this);
            itemView.setOnClickListener(onItemClickListener);
        }
    }
}
