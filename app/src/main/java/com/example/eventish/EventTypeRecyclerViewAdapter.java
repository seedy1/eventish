package com.example.eventish;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class EventTypeRecyclerViewAdapter extends RecyclerView.Adapter<EventTypeRecyclerViewAdapter.ViewHolder> {
    private List<String> cats;
    private LayoutInflater mInflater;
//    private ItemClickListener mClickListener;


    public EventTypeRecyclerViewAdapter(@NonNull Context context, List<String> cats) {
//        super(context);
        this.mInflater = LayoutInflater.from(context);
        this.cats = cats;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.event_category, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position){

        // bind data
        holder.txt.setText(cats.get(position));

    }

    @Override
    public int getItemCount() {
        return 0;
    }


    // stores and recycles views as they are scrolled off screen
    public class ViewHolder extends RecyclerView.ViewHolder{
//        View myView;
        TextView txt;

        ViewHolder(View itemView) {
            super(itemView);
            txt = itemView.findViewById(R.id.txtView);
//            itemView.setOnClickListener(this);
        }

    }
}
