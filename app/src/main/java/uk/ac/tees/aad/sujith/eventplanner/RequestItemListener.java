package uk.ac.tees.aad.sujith.eventplanner;

import android.content.Context;

public interface RequestItemListener {
    void onRequestItemClick(int position, String userName, String userEmail, String requestStatus, Context context);
}
