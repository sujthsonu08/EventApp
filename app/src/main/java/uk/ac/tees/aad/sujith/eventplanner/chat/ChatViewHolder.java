package uk.ac.tees.aad.sujith.eventplanner.chat;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import uk.ac.tees.aad.sujith.eventplanner.R;

public class ChatViewHolder extends RecyclerView.ViewHolder {
    public TextView userName;
    public TextView email;
    public ImageView profilePicture;

    public ChatViewHolder(View itemView, final ChatItemListener listener) {
        super(itemView);
        userName = itemView.findViewById(R.id.username);
        email = itemView.findViewById(R.id.email);
        profilePicture = itemView.findViewById(R.id.profilePicture);

        itemView.setOnClickListener(v -> {
            if (listener != null) {
                int position = getLayoutPosition();
                if (position != RecyclerView.NO_POSITION) {
                    listener.onChatItemClick(position, userName.getText().toString(),
                            email.getText().toString(),
                            itemView.getContext());
                }
            }
        });
    }
}
