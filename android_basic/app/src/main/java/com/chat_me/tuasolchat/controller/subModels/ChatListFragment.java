package com.chat_me.tuasolchat.controller.subModels;

import android.graphics.Bitmap;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chat_me.tuasolchat.R;
import com.chat_me.tuasolchat.controller.subModels.ChatsRecyclerViewAdapter;
import com.chat_me.tuasolchat.models.Member;
import com.chat_me.tuasolchat.models.Status;
import com.chat_me.tuasolchat.models.subModels.ChatItem;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class ChatListFragment extends Fragment {

    private RecyclerView chatsRecyclerView;
    private ChatsRecyclerViewAdapter chatsAdapter;
    private List<ChatItem> chatItemList;

    public ChatListFragment() {
        // Required empty public constructor
    }

    public static ChatListFragment newInstance(

    ) {
        ChatListFragment fragment = new ChatListFragment();
        Bundle args = new Bundle();

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        chatItemList = new ArrayList<>();
        chatItemList.add(new Member(
                "1",
                "Mubarak",
                "String profilepicture",
                "Android Developer",
                Status.ONLINE
        ));
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.chat_list_recyclerview, container, false);
        this.chatsRecyclerView = view.findViewById(R.id.chats_recyclaerview);
        this.chatsRecyclerView.setHasFixedSize(true); // Optimization if item sizes don't change
        this.chatsRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        chatsRecyclerView = view.findViewById(R.id.chats_recyclaerview);
        chatsRecyclerView.setHasFixedSize(true); // Optimization if item sizes don't change
        chatsRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        // TODO: Populate chatItemList with data before initializing the adapter
        // For demonstration, adding some dummy data. Replace with your actual data source.
        loadDummyData(); // TODO: Remove or replace this with your actual data loading logic

        chatsAdapter = new ChatsRecyclerViewAdapter(chatItemList); // TODO: Pass a click listener if needed by your adapter
        chatsRecyclerView.setAdapter(chatsAdapter);

        // TODO: Implement data loading (e.g., from a ViewModel or API) and update the adapter
        // e.g., observeViewModel();
    }

    private void loadDummyData() {
        // TODO: This is placeholder data. Replace with your actual data source and models.
        // Ensure your ChatItem model and its Message sub-model are defined correctly.
        // Also, ensure your ChatsRecyclerViewAdapter knows how to handle your ChatItem structure.

        // Example: Creating a dummy message list for a ChatItem
        // List<com.chat_me.tuasolchat.models.Message> messages1 = new ArrayList<>();
        // messages1.add(new com.chat_me.tuasolchat.models.Message("msg1", "userA", "userB", "Hello!".getBytes(), new Date(), null, false));

        // chatItemList.add(new ChatItem("chat1", "Alice", messages1, null, com.chat_me.tuasolchat.models.Status.OFFLINE, 0, "private", null));
        // chatItemList.add(new ChatItem("chat2", "Bob", new ArrayList<>(), null, com.chat_me.tuasolchat.models.Status.ONLINE, 2, "group", null));

        if (chatsAdapter != null) {
            chatsAdapter.notifyDataSetChanged();
        }
    }

    // TODO: Add methods for updating data in the adapter, handling item clicks (if any),
    // and interacting with a ViewModel if you're using one.

    // Example for item click listener (if your adapter supports it)
    // private ChatsRecyclerViewAdapter.OnChatItemClickListener chatItemClickListener = new ChatsRecyclerViewAdapter.OnChatItemClickListener() {
    //     @Override
    //     public void onChatItemClick(ChatItem item, int position) {
    //         // TODO: Handle chat item click, e.g., navigate to chat screen
    //     }
    //     @Override
    //     public void onChatItemLongClick(ChatItem item, int position) {
    //         // TODO: Handle long click
    //     }
    // };
}