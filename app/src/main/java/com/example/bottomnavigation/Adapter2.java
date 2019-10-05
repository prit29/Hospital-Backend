package com.example.bottomnavigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Adapter2 extends RecyclerView.Adapter<Adapter2.progViewHolder> {

    private ArrayList<Medicine> data;

    public Adapter2(ArrayList<Medicine> data){
        this.data = data;
    }

    @NonNull
    @Override
    public progViewHolder onCreateViewHolder(@NonNull final ViewGroup viewGroup, int i) {
        LayoutInflater l1 = LayoutInflater.from(viewGroup.getContext());
        View view = l1.inflate(R.layout.layers3,viewGroup,false);
        final progViewHolder view2 = new progViewHolder(view);



        return view2;
    }

    @Override
    public void onBindViewHolder(@NonNull progViewHolder progViewHolder, int i) {

        progViewHolder.t1.setText(data.get(i).getMedicine());
        progViewHolder.t2.setText(data.get(i).getA());
        progViewHolder.t3.setText(data.get(i).getB());
        progViewHolder.t4.setText(data.get(i).getC());
    }


    @Override
    public int getItemCount() {
        try {
            return data.size();
        }catch (Exception e)
        {
            return 0;
        }
    }

    public  void removeItem(int position) {
        data.remove(position);
        notifyItemRemoved(position);
    }

    public class progViewHolder extends RecyclerView.ViewHolder{
        TextView t1,t2,t3,t4;
        public progViewHolder(View itemView){
            super(itemView);
            t1 = itemView.findViewById(R.id.t1);
            t2 = itemView.findViewById(R.id.t2);
            t3 = itemView.findViewById(R.id.t3);
            t4 = itemView.findViewById(R.id.t4);

        }
    }
}
