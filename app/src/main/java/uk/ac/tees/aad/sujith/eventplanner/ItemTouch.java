package uk.ac.tees.aad.sujith.eventplanner;

import androidx.recyclerview.widget.RecyclerView;

public interface ItemTouch {

    void moveOnHold(int oldPosition, int newPosition);

    void onSwiped(RecyclerView.ViewHolder viewHolder, int position);
}
