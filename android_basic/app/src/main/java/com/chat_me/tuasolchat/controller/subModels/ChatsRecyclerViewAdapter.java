package com.chat_me.tuasolchat.controller.subModels;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.chat_me.tuasolchat.R;
import com.chat_me.tuasolchat.models.Message;
import com.chat_me.tuasolchat.models.Status;
import com.chat_me.tuasolchat.models.subModels.ChatItem;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.logging.Logger;

public class ChatsRecyclerViewAdapter extends RecyclerView.Adapter<ChatsRecyclerViewAdapter.ChatViewHolder> {

    private List<ChatItem> chatItems;
    private OnChatItemClickListener listener;

    public interface OnChatItemClickListener {
        void onChatItemClick(ChatItem item, int position);
        void onChatItemLongClick(ChatItem item, int position);
    }

    public ChatsRecyclerViewAdapter(List<ChatItem> chatItems, OnChatItemClickListener listener) {
        this.chatItems = chatItems != null ? chatItems : new ArrayList<>();
        this.listener = listener;
    }

    public ChatsRecyclerViewAdapter(List<ChatItem> chatItems) {
        this(chatItems, null);
    }

    @NonNull
    @Override
    public ChatViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_chat_list_item, parent, false);
        return new ChatViewHolder(itemView, listener, this.chatItems); // Pass chatItems to ViewHolder for click listener access
    }

    @Override
    public void onBindViewHolder(@NonNull ChatViewHolder holder, int position) {
        if (chatItems == null || position < 0 || position >= chatItems.size()) {
            Logger.getLogger(ChatsRecyclerViewAdapter.class.getName()).warning("Invalid position: " + position + " for chatItems size: " + chatItems.size() + "or items is empty");
            return;
        }
        ChatItem currentItem = chatItems.get(position);
        if (currentItem != null) {
            holder.bind(currentItem);
        }
    }

    @Override
    public int getItemCount() {
        return chatItems != null ? chatItems.size() : 0;
    }

    public void updateData(List<ChatItem> newChatItems) {
        this.chatItems.clear();
        if (newChatItems != null) {
            this.chatItems.addAll(newChatItems);
        }
        notifyDataSetChanged();
    }

    public void addChatItem(ChatItem item) {
        if (this.chatItems == null) {
            this.chatItems = new ArrayList<>();
        }
        this.chatItems.add(item);
        notifyItemInserted(this.chatItems.size() - 1);
    }

    static class ChatViewHolder extends RecyclerView.ViewHolder {
        private TextView userNameTextView;
        private TextView lastMessageTextView;
        private TextView lastMessageDateTextView;
        private TextView unreadMessagesTextView;
        private ImageView profileImageView;

        private List<ChatItem> itemsListRef;

        public ChatViewHolder(@NonNull View itemView, final OnChatItemClickListener clickListener, List<ChatItem> itemsList) {
            super(itemView);
            this.itemsListRef = itemsList;

            userNameTextView = itemView.findViewById(R.id.UserNameTextView);
            lastMessageTextView = itemView.findViewById(R.id.LastMessageTextView);
            lastMessageDateTextView = itemView.findViewById(R.id.LastMessageDateTextView);
            unreadMessagesTextView = itemView.findViewById(R.id.UnreadMessagesCountTextView);
            profileImageView = itemView.findViewById(R.id.ProfileImageImageView);


            itemView.setOnClickListener(v -> {
                if (clickListener != null) {
                    int position = getAdapterPosition();
                    if (position != RecyclerView.NO_POSITION && itemsListRef != null && position < itemsListRef.size()) {
                        clickListener.onChatItemClick(itemsListRef.get(position), position);
                    }
                }
            });
            itemView.setOnLongClickListener(v -> {
                if (clickListener != null) {
                    int position = getAdapterPosition();
                    if (position != RecyclerView.NO_POSITION && itemsListRef != null && position < itemsListRef.size()) {
                        clickListener.onChatItemLongClick(itemsListRef.get(position), position);
                        return true;
                    }
                }
                return false;
            });
        }

        public void bind(ChatItem item) {
            if (item.getName() != null) {
                userNameTextView.setText(item.getName());
            } else {
                userNameTextView.setText("NoName");
            }
            String messageText = "";
            Date messageDate = null;
             if (item.getMessages() != null && !item.getMessages().isEmpty()) {
                 Message lastMsg = item.getMessages().get(item.getMessages().size() - 1);
                 if (lastMsg.getText() != null) {
                     messageText = new String(lastMsg.getText(), java.nio.charset.StandardCharsets.UTF_8);
                 }
                 messageDate = lastMsg.getDateSent();
             }


            lastMessageTextView.setText(messageText);

            if(item.getStatus() == Status.ONLINE){
                lastMessageDateTextView.setText("Online");
                lastMessageDateTextView.setTextColor(Color.BLUE);
            }else if (messageDate != null) {
                SimpleDateFormat sdf = new SimpleDateFormat("hh:mm a", Locale.getDefault());
                lastMessageDateTextView.setText(sdf.format(messageDate));
                lastMessageDateTextView.setTextColor(null);
            } else {
                lastMessageDateTextView.setText("");
            }

            if (item.getUnreadMessagesCount() > 0) {
                unreadMessagesTextView.setText(String.valueOf(item.getUnreadMessagesCount()));
                unreadMessagesTextView.setVisibility(View.VISIBLE);
            } else {
                unreadMessagesTextView.setVisibility(View.GONE);
            }

            if (item.getBitmapProfilepicture() != null) {
                profileImageView.setImageBitmap(item.getBitmapProfilepicture());
                profileImageView.setVisibility(View.VISIBLE);
            } else {
                if(item.getType().equals("channel") || item.getType().equals("group"))
                    profileImageView.setImageResource(R.drawable.icon_group);
                else
                    profileImageView.setImageResource(R.drawable.icon_profile);
            }
            profileImageView.setOnClickListener(v -> {
                //TODO open Chat detals view
                //OpenDetails(item);
            });
        }
    }
}