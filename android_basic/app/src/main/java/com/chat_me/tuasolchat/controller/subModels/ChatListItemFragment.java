package com.chat_me.tuasolchat.controller.subModels;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.ImageView;
import com.chat_me.tuasolchat.R;

import java.io.ByteArrayOutputStream;

/**
 * A Fragment representing a single chat item in the chat list.
 * Displays user information, last message, timestamp, and unread count.
 */
public class ChatListItemFragment extends Fragment {

    // Argument keys
    private static final String ARG_USER_ID = "userId";
    private static final String ARG_USER_NAME = "userName";
    private static final String ARG_LAST_MESSAGE = "lastMessage";
    private static final String ARG_LAST_MESSAGE_DATE = "lastMessageDate";
    private static final String ARG_UNREAD_COUNT = "unreadCount";
    private static final String ARG_PROFILE_IMAGE = "profileImage";
    private static final String ARG_TYPE = "chat_type";
    // UI components
    private TextView userNameTextView;
    private TextView lastMessageTextView;
    private TextView lastMessageDateTextView;
    private TextView unreadMessagesTextView;
    private ImageView profileImageView;
    private Bitmap currentProfileBitmap;
    private String Type;
    private String id;
    /**
     * Required empty public constructor.
     * Fragments should be instantiated through newInstance().
     */
    public ChatListItemFragment() {
        // Fragments should have empty constructors
    }
    public ChatListItemFragment(View view) {
        initializeViews(view);

        // Populate data if arguments exist
        if (getArguments() != null) {
            populateChatItemData();
        }
    }

    /**
     * Factory method to create a new instance of this fragment.
     *
     * @param userId The unique identifier for the chat user
     * @param userName The display name of the chat user
     * @param lastMessage The text of the last message in the chat
     * @param lastMessageDate The timestamp of the last message
     * @param unreadCount The number of unread messages
     * @param profileImage The profile image of user Image
     * @return A new instance of ChatListItemFragment with the provided parameters
     */
    public static ChatListItemFragment newInstance(
            String userId,
            String userName,
            String lastMessage,
            String lastMessageDate,
            int unreadCount,
            Bitmap profileImage,
            String type
    ) {
        if (userId == null ) {
            throw new IllegalArgumentException("User ID cannot be null");
        }
        ChatListItemFragment fragment = new ChatListItemFragment();
        Bundle args = new Bundle();
        args.putString(ARG_USER_ID, userId);
        args.putString(ARG_USER_NAME, userName);
        args.putString(ARG_LAST_MESSAGE, lastMessage);
        args.putString(ARG_LAST_MESSAGE_DATE, lastMessageDate);
        args.putInt(ARG_UNREAD_COUNT, unreadCount);
        args.putString(ARG_TYPE,type);

        if (profileImage != null) {
            // Scale down large images (adjust 200 to your needs)
            Bitmap scaledBitmap = Bitmap.createScaledBitmap(
                    profileImage,
                    200,
                    200,
                    true
            );
            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            scaledBitmap.compress(Bitmap.CompressFormat.WEBP, 85, stream); // WEBP is more efficient
            args.putByteArray(ARG_PROFILE_IMAGE, stream.toByteArray());
            if (!profileImage.isRecycled()) {
                profileImage.recycle();
            }
        }
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_chat_list_item, container, false);

        // Initialize UI components
        initializeViews(view);

        // Populate data if arguments exist
        if (getArguments() != null) {
            populateChatItemData();
        }

        return view;
    }
    @Override
    public void onDestroyView() {
        if (currentProfileBitmap != null && !currentProfileBitmap.isRecycled()) {
            currentProfileBitmap.recycle();
        }
        super.onDestroyView();
    }
    /**
     * Initializes all view components by finding them in the inflated layout.
     *
     * @param view The root view of the fragment
     */
    private void initializeViews(View view) {
        userNameTextView = view.findViewById(R.id.UserNameTextView);
        lastMessageTextView = view.findViewById(R.id.LastMessageTextView);
        lastMessageDateTextView = view.findViewById(R.id.LastMessageDateTextView);
        unreadMessagesTextView = view.findViewById(R.id.UnreadMessagesCountTextView);
        profileImageView = view.findViewById(R.id.ProfileImageImageView);
    }

    /**
     * Populates the chat item with data from fragment arguments.
     */
    private void populateChatItemData() {
        Bundle args = getArguments();
        if (args != null) {
            userNameTextView.setText(args.getString(ARG_USER_NAME));
            lastMessageTextView.setText(args.getString(ARG_LAST_MESSAGE));
            lastMessageDateTextView.setText(args.getString(ARG_LAST_MESSAGE_DATE));
            this.id = args.getString(ARG_USER_ID,"unknown");
            this.Type = args.getString(ARG_TYPE);
            int unreadCount = args.getInt(ARG_UNREAD_COUNT, 0);
            if (unreadCount > 0) {
                unreadMessagesTextView.setText(String.valueOf(unreadCount));
                unreadMessagesTextView.setVisibility(View.VISIBLE);
            } else {
                unreadMessagesTextView.setVisibility(View.GONE);
            }
            byte[] byteArray = getArguments().getByteArray(ARG_PROFILE_IMAGE);
            if (byteArray != null) {
                try {
                    Bitmap profileImage = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);
                    if (profileImageView != null) {
                        profileImageView.setImageBitmap(profileImage);
                        // Store reference if needed later
                        currentProfileBitmap = profileImage;
                    }
                } catch (Exception e) {
                    Log.e("ChatListItem", "Error loading profile image", e);
                    profileImageView.setImageResource(R.drawable.default_profile);
                }
            }
        }
    }

    /**
     * Updates the last message information displayed in the chat item.
     *
     * @param message The new last message text
     * @param date The new last message date
     */
    public void updateLastMessage(String message, String date) {
        lastMessageTextView.setText(message);
        lastMessageDateTextView.setText(date);
    }
    public void setData(String id, String name, String LastMessage, String LastTime,int UnreadMessages,Bitmap profile , String type){
        this.id = id;
        this.Type = type;
        this.updateUnreadCount(UnreadMessages);
        this.updateLastMessage(LastMessage,LastTime);
        this.updateProfile(profile);
        this.userNameTextView.setText(name);
    }
    public String getChatId(){
        return this.id;
    }
    public String getChatType(){
        return  this.Type;
    }

    public void updateProfile(Bitmap profileImage){
        try {
            if (profileImageView != null) {
                profileImageView.setImageBitmap(profileImage);
                // Store reference if needed later
                currentProfileBitmap = profileImage;
            }
        } catch (Exception e) {
            Log.e("ChatListItem", "Error loading profile image", e);
            if(this.Type == "channel"){
                this.profileImageView.setImageResource(R.drawable.icon_group);
            }else{
            profileImageView.setImageResource(R.drawable.default_profile);
            }
        }
    }


    public Bitmap getCurrentProfileBitmap() {
        return currentProfileBitmap;
    }

    public String getType() {
        return Type;
    }

    public void setType(String type) {
        Type = type;
    }

    /**
     * Updates the unread messages count.
     *
     * @param count The new count of unread messages
     */
    public void updateUnreadCount(int count) {
        if (count > 0) {
            unreadMessagesTextView.setText(String.valueOf(count));
            unreadMessagesTextView.setVisibility(View.VISIBLE);
        } else {
            unreadMessagesTextView.setVisibility(View.GONE);
        }
    }
}
// Creating a new chat item example
/*
 * ChatListItemFragment fragment = ChatListItemFragment.newInstance(
 *        "user123",
 *         "John Doe",
 *         "Hey there!",
 *         "10:30 AM",
 *         3,
 *          null
 * );
*/
// Later updating the item
/*
 * fragment.updateLastMessage("See you tomorrow!", "2:45 PM");
 * fragment.updateUnreadCount(0);
 */