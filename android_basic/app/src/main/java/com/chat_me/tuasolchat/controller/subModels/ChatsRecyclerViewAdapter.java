package com.chat_me.tuasolchat.controller.subModels;

import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.chat_me.tuasolchat.R;
import com.chat_me.tuasolchat.models.subModels.ChatItem;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class ChatsRecyclerViewAdapter extends RecyclerView.Adapter<ChatsRecyclerViewAdapter.ChatViewHolder> {
    public List<ChatItem> items;
    FragmentManager getChildFragmentManager;

    public ChatsRecyclerViewAdapter(List<ChatItem> items, FragmentManager manager){
        this.items = Objects.requireNonNullElseGet(items, ArrayList::new);
        this.getChildFragmentManager = manager;
    }

    @NonNull
    @Override
    public ChatViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_chat_list_item, null, false);
        return new ChatViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ChatViewHolder holder, int position) {
        ChatItem c = items.get(position);
        holder.userNameTextView.setText(c.getName());
        holder.lastMessageTextView.setText(Arrays.toString(c.getMessages().get(c.getMessages().size() - 1).getText()));
        holder.lastMessageDateTextView.setText(String.format(c.getMessages().get(c.getMessages().size() - 1).getDateSent().toString(),"hh:mm"));
        holder.id = c.getId();
        holder.Type = c.getType();
        holder.updateUnreadCount(c.getUnreadMessages());
        holder.updateProfile(c.getProfileImage());

    }

    @Override
    public int getItemCount() {
        return this.items.size();
    }

    private void loadFragment(Fragment fragment, int fragment_id) {
        // Use getChildFragmentManager() to manage fragments within this fragment's container
        FragmentManager fragmentManager = getChildFragmentManager;
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(fragment_id, fragment);
        fragmentTransaction.commit();
    }

    class ChatViewHolder extends RecyclerView.ViewHolder{

        private TextView userNameTextView;
        private TextView lastMessageTextView;
        private TextView lastMessageDateTextView;
        private TextView unreadMessagesTextView;
        private ImageView profileImageView;
        private Bitmap currentProfileBitmap;
        private String Type;
        private String id;

        public ChatViewHolder(@NonNull View  itemView) {
            super(itemView);
            userNameTextView = itemView.findViewById(R.id.UserNameTextView);
            lastMessageTextView = itemView.findViewById(R.id.LastMessageTextView);
            lastMessageDateTextView = itemView.findViewById(R.id.LastMessageDateTextView);
            unreadMessagesTextView = itemView.findViewById(R.id.UnreadMessagesCountTextView);
            profileImageView = itemView.findViewById(R.id.ProfileImageImageView);
        }

    }
}
