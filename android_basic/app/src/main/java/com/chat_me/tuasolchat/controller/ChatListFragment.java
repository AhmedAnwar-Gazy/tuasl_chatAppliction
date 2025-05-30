package com.chat_me.tuasolchat.controller;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chat_me.tuasolchat.R;
import com.chat_me.tuasolchat.controller.subModels.ChatsRecyclerViewAdapter;
import com.chat_me.tuasolchat.models.Chanel;
import com.chat_me.tuasolchat.models.Member;
import com.chat_me.tuasolchat.models.Status;
import com.chat_me.tuasolchat.models.subModels.ChatItem;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ChatListFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ChatListFragment extends Fragment {

    private static final String ARG_PARAM1 = "chats_ids";

    private ArrayList<String> members;
    private RecyclerView recyclerView;
    public ChatListFragment() {
        // Required   public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param chats_ids Parameter 1.
     * @return A new instance of fragment ChatListFragment.
     */
    public static ChatListFragment newInstance(ArrayList<String> chats_ids) {
        ChatListFragment fragment = new ChatListFragment();
        Bundle args = new Bundle();
        args.putStringArrayList(ARG_PARAM1,chats_ids);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            members = getArguments().getStringArrayList(ARG_PARAM1);
        }
        // TODO: replace this array list by the list of chats
        ArrayList<ChatItem> items = new ArrayList<ChatItem>();
        items.add(
            new Member(
                // String id,String name, String profilepicture, String rule, Status status
                "1",
                "John Doe",
                "null",
                "10:30 AM",
                Status.ONLINE
            ));
        items.add(
                new Chanel(
                        //String profilepicture, String bio, ArrayList<Member> members, String content, ArrayList<Message> messages
                        "2",
                        "hello this is a bio",
                        null,
                        "this is a content",
                        null
                ));
        ChatsRecyclerViewAdapter adapter = new ChatsRecyclerViewAdapter(items, getChildFragmentManager());
        RecyclerView.LayoutManager ln = new LinearLayoutManager(this.getContext());

        recyclerView = requireActivity().findViewById(R.id.chats_recyclaerview);
        if(recyclerView != null){
            recyclerView.setHasFixedSize(true);
            recyclerView.setLayoutManager(ln);
            recyclerView.setAdapter(adapter);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.chat_list_recyclerview, container, false);
    }
}