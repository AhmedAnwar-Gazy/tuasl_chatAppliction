package com.chat_me.tuasolchat.controller;

import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.chat_me.tuasolchat.R; // Make sure this points to your R file



public class MainFragmentMainActivity extends Fragment {

    private FrameLayout startMenuLayout;
    private FrameLayout mainFragmentContentContainer;
    private TextView headerNameTextView;
    private BottomNavigationView bottomNavigationView;
    private ConstraintLayout header;
    private boolean isStartMenuOpen = false;
    private ImageButton profileImageButton;
    private EditText  searchEditText;
    private ImageButton searchImageButton;

    int lastPage;

    // Required empty public constructor
    public MainFragmentMainActivity() {
    }

    public static MainFragmentMainActivity newInstance() {
        MainFragmentMainActivity fragment = new MainFragmentMainActivity();
        Bundle args = new Bundle();
        // Add any necessary arguments here
        args.putString("param1", "value1");
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Handle arguments if any
        if (getArguments() != null) {
            // Retrieve arguments here
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_main_main_activity, container, false);

        startMenuLayout = view.findViewById(R.id.StartMenu);
        mainFragmentContentContainer = view.findViewById(R.id.MainFragmentContentContainer);
        headerNameTextView = view.findViewById(R.id.headerName);
        bottomNavigationView = view.findViewById(R.id.bottomContainerNavigation);
        headerNameTextView.setText(R.string.header_main_messages_fragment_name);
        header = view.findViewById(R.id.headerContainerNavigation);
        profileImageButton = view.findViewById(R.id.ProfileImageButton);
        searchEditText = view.findViewById(R.id.search);
        searchImageButton = view.findViewById(R.id.SearchImageButton);


        // Load the default fragment (e.g., Chats) initially
        if (savedInstanceState == null) {
            loadFragment(new com.chat_me.tuasolchat.controller.subModels.ChatListFragment(), R.id.MainFragmentContentContainer);
            loadFragment(new ProfileFragment(), R.id.StartMenu);
        }

        searchImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        bottomNavigationView.setOnItemSelectedListener(item -> {
            int itemId = item.getItemId();
            if (itemId == R.id.Profile) {
                UserClickProfile();
                return true;
            } else if (itemId == R.id.Calls) {
                UserClickCalls();
                lastPage = R.id.Calls;
                return true;
            } else if (itemId == R.id.Groups) {
                UserClickGroups();
                lastPage = R.id.Groups;
                return true;
            } else if (itemId == R.id.Chats) {
                UserClickChats();
                lastPage = R.id.Chats;
                return true;
            }
            return false;
        });

        // Set a click listener on the root layout to close the StartMenu when clicked outside
        header.setOnClickListener(v -> {
            if (isStartMenuOpen) {
                closeStartMenu();
            }
        });
        this.mainFragmentContentContainer.setOnClickListener(v -> {
            if (isStartMenuOpen) {
                closeStartMenu();
            }
        });


        // Prevent clicks on the StartMenu from closing it
        startMenuLayout.setOnClickListener(v -> {
            // Do nothing, consume the click
        });

        return view;
    }

    private void loadFragment(Fragment fragment, int fragment_id) {
        // Use getChildFragmentManager() to manage fragments within this fragment's container
        FragmentManager fragmentManager = getChildFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(fragment_id, fragment);
        fragmentTransaction.commit();
    }

    // 1_ UserClickProfile() - Show the StartMenu with animation
    public void UserClickProfile() {
        if (!isStartMenuOpen) {
            startMenuLayout.setVisibility(View.VISIBLE);

            // Get the width of the parent view (ConstraintLayout) to calculate guideline position
            startMenuLayout.post(() -> { // Use post to ensure layout is measured
                int parentWidth = header.getWidth();
                int targetX = (int) (parentWidth);

                // Animate the StartMenu from off-screen left to the target position
                ObjectAnimator animator = ObjectAnimator.ofFloat(startMenuLayout, "translationX", startMenuLayout.getWidth(), targetX - startMenuLayout.getWidth());
                animator.setDuration(300); // Adjust duration as needed
                animator.start();
            });

            isStartMenuOpen = true;
        }
    }

    private void closeStartMenu() {
        if (isStartMenuOpen) {
            isStartMenuOpen = false;
            this.bottomNavigationView.setSelectedItemId(lastPage);
            // Animate the StartMenu back to the left (off-screen)
            ObjectAnimator animator = ObjectAnimator.ofFloat(startMenuLayout, "translationX", startMenuLayout.getTranslationX(), startMenuLayout.getWidth());
            animator.setDuration(300); // Adjust duration as needed
            animator.start();

            // Hide the StartMenu after the animation completes
            startMenuLayout.postDelayed(() -> startMenuLayout.setVisibility(View.INVISIBLE), 300); // Hide after animation
        }
    }


    // 2_ UserClickCalls() - Load Calls Fragment and update header
    public void UserClickCalls() {
        headerNameTextView.setText(R.string.calls); // Replace with your string resource for Calls
        // loadFragment(new CallsFragment()); // Uncomment and replace with your Calls Fragment
        if (isStartMenuOpen) {
            closeStartMenu();
        }
    }

    // 3_ UserClickGroups() - Load Groups Fragment and update header
    public void UserClickGroups() {
        headerNameTextView.setText(R.string.groups); // Replace with your string resource for Groups
        // loadFragment(new GroupsFragment()); // Uncomment and replace with your Groups Fragment
        if (isStartMenuOpen) {
            closeStartMenu();
        }
    }

    // 4_ UserClickChats() - Load Chats Fragment and update header
    public void UserClickChats() {
        headerNameTextView.setText(R.string.header_main_messages_fragment_name); // Your default chat header
        // loadFragment(new ChatsFragment()); // Uncomment and replace with your Chats Fragment
        if (isStartMenuOpen) {
            closeStartMenu();
        }
    }
}