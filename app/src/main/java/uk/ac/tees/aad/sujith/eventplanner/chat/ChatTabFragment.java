package uk.ac.tees.aad.sujith.eventplanner.chat;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import uk.ac.tees.aad.sujith.eventplanner.R;
import uk.ac.tees.aad.sujith.eventplanner.user.User;

public class ChatTabFragment extends Fragment {
    private final ArrayList<ChatTabItem> linkItemCardArrayList = new ArrayList<>();
    private ViewGroup viewGroup;
    private RecyclerView linkCollectorRecyclerView;
    private ChatViewAdapter itemviewAdapter;
    private ProgressBar progressBar;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        viewGroup = (ViewGroup) inflater.inflate(R.layout.fragment_chattab, container, false);
        return viewGroup;
    }

    @Override
    public void onStart() {
        super.onStart();
        linkCollectorRecyclerView = viewGroup.findViewById(R.id.chats_recycler_view);
        progressBar = viewGroup.findViewById(R.id.chatsprogressBar);
        init(viewGroup);
    }

    private void init(ViewGroup container) {
        initialItemData();
        createRecyclerView(container);
    }

    private void initialItemData() {
        progressBar.setVisibility(View.VISIBLE);
        FirebaseAuth authentication = FirebaseAuth.getInstance();
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference();
        DatabaseReference dataSnapshot = reference.child("Users");
        String userId = authentication.getCurrentUser().getUid();
        final int[] usersCount = new int[1];


    }

    private String cleanEmail(String email) {
        return email.replaceAll("\\,", ".");
    }

    private void createRecyclerView(ViewGroup container) {
        RecyclerView.LayoutManager rLayoutManger = new LinearLayoutManager(container.getContext());
        linkCollectorRecyclerView.setHasFixedSize(true);
        itemviewAdapter = new ChatViewAdapter(linkItemCardArrayList, container.getContext());
        ChatItemListener itemClickListener = (position, userName, userEmail, context) -> {
            linkItemCardArrayList.get(position).onChatItemClick(position, userName, userEmail,
                    context);
            itemviewAdapter.notifyItemChanged(position);
        };
        itemviewAdapter.setOnItemClickListener(itemClickListener);
        linkCollectorRecyclerView.setAdapter(itemviewAdapter);
        linkCollectorRecyclerView.setLayoutManager(rLayoutManger);
    }
}