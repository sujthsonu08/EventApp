package uk.ac.tees.aad.sujith.eventplanner;

import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

public class RequestViewHolder extends RecyclerView.ViewHolder {
    public TextView userName;
    public TextView email;
    public TextView requestStatus;

    public RequestViewHolder(View itemView, final RequestItemListener listener) {
        super(itemView);
        userName = itemView.findViewById(R.id.user_name);
        email = itemView.findViewById(R.id.email);
        requestStatus = itemView.findViewById(R.id.request_status);

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    int position = getLayoutPosition();
                    if (position != RecyclerView.NO_POSITION) {
                        listener.onRequestItemClick(position, userName.getText().toString(),
                                email.getText().toString(),
                                requestStatus.getText().toString(),
                                itemView.getContext());
                    }
                }
            }
        });
    }
}
