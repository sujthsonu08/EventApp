package uk.ac.tees.aad.sujith.eventplanner;

import android.content.Context;

public interface ChatItemListener {
    void onChatItemClick(int position, String userName, String userEmail, Context context);
}
