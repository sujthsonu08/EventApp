package uk.ac.tees.aad.sujith.eventplanner;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import uk.ac.tees.aad.sujith.eventplanner.invite.Invite;

public class SentAdapter extends RecyclerView.Adapter<SentAdapter.ViewHolder>{

    private final List<SentItem> sentList;
    private final Context context;
    private final Map<String ,List<String>> invMap;
    private final Map<Integer ,String> invMap2;

    public SentAdapter(List<SentItem> sentList, Context context, Map<String,List<String>> inviteesMap1, Map<Integer ,String> invMap2) {
        this.sentList = sentList;
        this.context = context;
        this.invMap= inviteesMap1;
        this.invMap2 = invMap2;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.sent_card,parent,false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        SentItem item = sentList.get(position);
        holder.sentName.setText(item.getSentName());
        holder.sentVenue.setText(item.getSentVenue());
        holder.sentTime.setText(item.getSentTime());
        String eventName = invMap2.get(position);

        holder.view.setOnClickListener(view -> {
            Intent intent = new Intent(context,ShowSentCard.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            Bundle extras = new Bundle();
            extras.putString("eventName",eventName);
            intent.putExtras(extras);
            context.startActivity(intent);
        });

        holder.invite.setOnClickListener(view -> {
            Intent intent = new Intent(context, Invite.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            Bundle extras = new Bundle();
            extras.putStringArrayList("invitees",(ArrayList<String>) invMap.get(eventName));
            extras.putString("eventName",eventName);
            intent.putExtras(extras);
            context.startActivity(intent);
        });

        holder.edit.setOnClickListener(view -> {
            Intent intent = new Intent(context,EditActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            Bundle extras = new Bundle();
            extras.putString("eventName",eventName);
            extras.putString("type","sentFrag");

            intent.putExtras(extras);
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return sentList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView sentName;
        public TextView sentVenue;
        public TextView sentTime;
        public Button invite;
        public Button edit;
        public View view;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            sentName = itemView.findViewById(R.id.sentEventName);
            sentVenue = itemView.findViewById(R.id.sentVenue);
            sentTime = itemView.findViewById(R.id.sentEventTime);
            invite = itemView.findViewById(R.id.invite);
            edit = itemView.findViewById(R.id.editButton);
            this.view = itemView;
        }
    }

    public int getEditId(int position){
        SentItem x = sentList.get(position);
        return x.getEditId();
    }

}
