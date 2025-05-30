package com.chat_me.tuasolchat.controller.subModels;

import android.graphics.Bitmap;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.chat_me.tuasolchat.R;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class ChatListItemFragment extends Fragment {

    private static final String ARG_CHAT_ID = "chat_id";
    private static final String ARG_USER_NAME = "userName";
    private static final String ARG_LAST_MESSAGE = "lastMessage";
    private static final String ARG_LAST_MESSAGE_DATE = "lastMessageDate"; // Assuming this will be a Date object passed as long
    private static final String ARG_UNREAD_COUNT = "unreadCount";
    private static final String ARG_PROFILE_PICTURE_BITMAP = "profilePictureBitmap";
    private static final String ARG_CHAT_TYPE = "chat";

    private TextView userNameTextView;
    private TextView lastMessageTextView;
    private TextView lastMessageDateTextView;
    private TextView unreadMessagesCountTextView;
    private ImageView profileImageView;

    private String chat_id;
    private String chat_type;

    public ChatListItemFragment() {
        // Required empty public constructor
    }

    public static ChatListItemFragment newInstance(
            String userId,
            String userName,
            String lastMessage,
            Date lastMessageDate,
            int unreadCount,
            Bitmap profilePicture,
            String chatType
    ) {
        ChatListItemFragment fragment = new ChatListItemFragment();
        Bundle args = new Bundle();
        args.putString(ARG_CHAT_ID, userId);
        args.putString(ARG_USER_NAME, userName);
        args.putString(ARG_LAST_MESSAGE, lastMessage);
        if (lastMessageDate != null) {
            args.putLong(ARG_LAST_MESSAGE_DATE, lastMessageDate.getTime());
        }
        args.putInt(ARG_UNREAD_COUNT, unreadCount);
        args.putParcelable(ARG_PROFILE_PICTURE_BITMAP, profilePicture);
        args.putString(ARG_CHAT_TYPE,chatType);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_chat_list_item, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        profileImageView = view.findViewById(R.id.ProfileImageImageView);
        userNameTextView = view.findViewById(R.id.UserNameTextView);
        lastMessageTextView = view.findViewById(R.id.LastMessageTextView);
        lastMessageDateTextView = view.findViewById(R.id.LastMessageDateTextView);
        unreadMessagesCountTextView = view.findViewById(R.id.UnreadMessagesCountTextView);

        if (getArguments() != null) {
            populateViewsFromArguments(getArguments());
        }
    }

    private void populateViewsFromArguments(Bundle args) {
        String name = args.getString(ARG_USER_NAME);
        String lastMessage = args.getString(ARG_LAST_MESSAGE);
        long dateMillis = args.getLong(ARG_LAST_MESSAGE_DATE, -1);
        int unreadCount = args.getInt(ARG_UNREAD_COUNT, 0);
        Bitmap profileBitmap = args.getParcelable(ARG_PROFILE_PICTURE_BITMAP);
        String type = args.getString(ARG_CHAT_TYPE);
        if (name != null) {
            userNameTextView.setText(name);
        } else {
            userNameTextView.setText("");
        }
        if (type != null) {
            this.chat_type = type;
        } else {
            this.chat_type = "chat";
        }

        if (lastMessage != null) {
            lastMessageTextView.setText(lastMessage);
        } else {
            lastMessageTextView.setText("");
        }

        if (dateMillis != -1) {
            Date lastTime = new Date(dateMillis);
            SimpleDateFormat sdf = new SimpleDateFormat("hh:mm a", Locale.getDefault());
            lastMessageDateTextView.setText(sdf.format(lastTime));
        } else {
            lastMessageDateTextView.setText("");
        }

        if (unreadCount > 0) {
            unreadMessagesCountTextView.setText(String.valueOf(unreadCount));
            unreadMessagesCountTextView.setVisibility(View.VISIBLE);
        } else {
            unreadMessagesCountTextView.setVisibility(View.GONE);
        }

        if (profileBitmap != null) {
            profileImageView.setImageBitmap(profileBitmap);
        } else {
            if(this.chat_type.equals("chat"))
                profileImageView.setImageResource(R.drawable.icon_profile);
            else
                profileImageView.setImageResource(R.drawable.icon_group);
        }
    }
     public void updateDisplayedData(
             String userId,
             String userName,
             String lastMessage,
             Date lastMessageDate,
             int unreadCount,
             Bitmap profilePicture,
             String chatType
     ) {
         ChatListItemFragment fragment = new ChatListItemFragment();
         Bundle args = new Bundle();
         args.putString(ARG_CHAT_ID, userId);
         args.putString(ARG_USER_NAME, userName);
         args.putString(ARG_LAST_MESSAGE, lastMessage);
         if (lastMessageDate != null) {
             args.putLong(ARG_LAST_MESSAGE_DATE, lastMessageDate.getTime());
         }
         args.putInt(ARG_UNREAD_COUNT, unreadCount);
         args.putParcelable(ARG_PROFILE_PICTURE_BITMAP, profilePicture);
         args.putString(ARG_CHAT_TYPE,chatType);

         populateViewsFromArguments(args);
     }
}