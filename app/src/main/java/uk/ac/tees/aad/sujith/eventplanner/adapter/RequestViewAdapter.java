package uk.ac.tees.aad.sujith.eventplanner.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import uk.ac.tees.aad.sujith.eventplanner.R;
import uk.ac.tees.aad.sujith.eventplanner.RequestItemListener;
import uk.ac.tees.aad.sujith.eventplanner.RequestListItem;
import uk.ac.tees.aad.sujith.eventplanner.RequestViewHolder;

public class RequestViewAdapter extends RecyclerView.Adapter<RequestViewHolder> {

    private final ArrayList<RequestListItem> itemList;
    private RequestItemListener listener;

    public RequestViewAdapter(ArrayList<RequestListItem> itemList) {
        this.itemList = itemList;
    }

    public void setOnItemClickListener(RequestItemListener listener) {
        this.listener = listener;
    }

    @Override
    public RequestViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_request_list_item, parent, false);
        return new RequestViewHolder(view, listener);
    }

    @Override
    public void onBindViewHolder(@NonNull RequestViewHolder holder, int position) {
        RequestListItem currentItem = itemList.get(position);
        holder.userName.setText(currentItem.getUserName());
        holder.email.setText(currentItem.getUserEmail());
        holder.requestStatus.setText(currentItem.getRequestStatus());
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }
}