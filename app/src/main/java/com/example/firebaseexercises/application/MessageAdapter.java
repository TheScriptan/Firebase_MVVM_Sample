package com.example.firebaseexercises.application;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import com.example.firebaseexercises.application.viewmodel.MessageViewModel;
import com.example.firebaseexercises.business.model.Message;
import com.example.firebaseexercises.R;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MessageAdapter extends RecyclerView.Adapter<MessageAdapter.ViewHolder> {

    //MessageAdapter
    private View.OnClickListener onItemClickListener;
    private View.OnLongClickListener onLongClickListener;
    private MessageViewModel messageViewModel;
    private List<Message> messageList;

    public MessageAdapter(MessageViewModel viewModel){
        messageList = new ArrayList<>();
        this.messageViewModel = viewModel;
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

    public Message getMessageByUsername(String username){
        Message msg = null;
        int i = 0;
        while(msg == null){
            if(messageList.get(i).getUsername().equals(username)){
                msg = messageList.get(i);
            }
            i++;
        }
        return msg;
    }

    @Override
    public int getItemCount() {
        return messageList.size();
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.messageUser.setText(messageList.get(position).getUsername());
        holder.messageContent.setText(messageList.get(position).getContent());
        onLongClickListener = holder.bindPopupMenu(position);

        holder.itemView.setOnClickListener(onItemClickListener);
        holder.itemView.setOnLongClickListener(onLongClickListener);
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
        }

        private View.OnLongClickListener bindPopupMenu(int pos){
            Message message = messageList.get(pos);
            View.OnLongClickListener listener = v -> {
                PopupMenu popupMenu = new PopupMenu(v.getContext(), v);
                popupMenu.inflate(R.menu.message_popup_menu);
                popupMenu.setOnMenuItemClickListener(item -> {
                    int id = item.getItemId();
                    switch(id){
                        case R.id.option_edit:
                            Log.v("TEST", "OPTION EDIT " + message.toString() );
                            return true;
                        case R.id.option_delete:
                            messageViewModel.deleteMessage(message.getId());
                            Log.v("TEST", "OPTION DELETE " + getAdapterPosition());
                            return true;
                    }
                    return true;
                });
                popupMenu.show();
                return true;
            };
            return listener;
        }
    }


}
