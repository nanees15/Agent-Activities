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

public class Clients_Adapter extends RecyclerView.Adapter<Clients_Adapter.ViewHolderforClients> {
private List<MyItem> itemList;
private Context context;
private  String date;

// Constructor to initialize the dataset
public Clients_Adapter(Context context, List<MyItem> itemList) {
        this.context = context;
        this.itemList = itemList;
        }

// Create new views (invoked by the layout manager)
@NonNull
@Override
public ViewHolderforClients onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.client_item, parent, false);
        return new ViewHolderforClients(view);
        }

// Replace the contents of a view (invoked by the layout manager)
@Override
public void onBindViewHolder(@NonNull ViewHolderforClients holder, int position) {
        MyItem item = itemList.get(position);
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
public class ViewHolderforClients extends RecyclerView.ViewHolder {
    TextView time;
    TextView clientName;

    public ViewHolderforClients(View view) {
        super(view);
        time = view.findViewById(R.id.time_inclientLIst);
        clientName = view.findViewById(R.id.clientname_InclientList);
        // Initialize other views here

//         Set OnClickListener to handle item clicks
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = getAdapterPosition();
                if (position != RecyclerView.NO_POSITION) {
                    MyItem item = itemList.get(position);
                    // Create an Intent to start the next activity
                    Intent intent = new Intent(context, Activities_Per_Client.class);
                    // Pass data to the intent
                    intent.putExtra("clientName", item.getClientName());
                    // Start the activity
                    context.startActivity(intent);
                }
            }
        });
    }
}
}