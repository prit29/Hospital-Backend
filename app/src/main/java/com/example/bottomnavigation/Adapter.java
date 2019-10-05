package com.example.bottomnavigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.progViewHolder> {

    private ArrayList<String> data;

    public Adapter(ArrayList<String> data){
        this.data = data;
    }

    @NonNull
    @Override
    public progViewHolder onCreateViewHolder(@NonNull final ViewGroup viewGroup, int i) {
        LayoutInflater l1 = LayoutInflater.from(viewGroup.getContext());
        View view = l1.inflate(R.layout.layers,viewGroup,false);
        final progViewHolder view2 = new progViewHolder(view);



        return view2;
    }

    @Override
    public void onBindViewHolder(@NonNull progViewHolder progViewHolder, int i) {
        String title = data.get(i);
        progViewHolder.t1.setText(title);
    }


    @Override
    public int getItemCount() {
        return data.size();
    }

    public  void removeItem(int position) {
        data.remove(position);
        notifyItemRemoved(position);
    }

    public class progViewHolder extends RecyclerView.ViewHolder{
        TextView t1;
        LinearLayout l1;
        public progViewHolder(View itemView){
            super(itemView);
            t1 = itemView.findViewById(R.id.t1);
            l1 = itemView.findViewById(R.id.l1);
        }
    }
}
