package com.example.agent;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class Activities_Per_Client_Adapter extends RecyclerView.Adapter<Activities_Per_Client_Adapter.ViewHolder> {
    private List<MyItem> itemList;
    private Context context;
    private  String date;

    // Constructor to initialize the dataset
    public Activities_Per_Client_Adapter(Context context, List<MyItem> itemList) {
        this.context = context;
        this.itemList = itemList;
        this.date = date;
    }


    // Create new views (invoked by the layout manager)
    @NonNull
    @Override
    public Activities_Per_Client_Adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_2, parent, false);
        return new Activities_Per_Client_Adapter.ViewHolder(view);
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(@NonNull Activities_Per_Client_Adapter.ViewHolder holder, int position) {
        MyItem item = itemList.get(position);
        holder.activity_name.setText(item.getActivityName());
        holder.time.setText(item.getTime());
        holder.clientName.setText(item.getClientName());

        // Bind other views as needed
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return itemList.size();
    }

    // Provide a reference to the views for each data item
    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView time;
        TextView clientName;
        TextView activity_name;

        public ViewHolder(View view) {
            super(view);
            time = view.findViewById(R.id.timeId);
            clientName = view.findViewById(R.id.clientName);
            activity_name = view.findViewById(R.id.activityName);
            // Initialize other views here

            // Set OnClickListener to handle item clicks
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if (position != RecyclerView.NO_POSITION) {
                        MyItem item = itemList.get(position);
                        // Create an Intent to start the next activity
                        Intent intent = new Intent(context, Activity_Details.class);
                        // Pass data to the intent
                        intent.putExtra("clientName", item.getClientName());
                        intent.putExtra("activityName", item.getActivityName());
                        intent.putExtra("time", item.getTime());
                        intent.putExtra("date", date);
                        // Start the activity
                        context.startActivity(intent);
                    }
                }
            });
        }
    }
}
