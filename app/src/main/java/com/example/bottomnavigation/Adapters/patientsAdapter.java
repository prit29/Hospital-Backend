package com.example.bottomnavigation.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bottomnavigation.ActivityUser;
import com.example.bottomnavigation.R;
import com.example.bottomnavigation.pojo_classes.patients;
import com.google.gson.Gson;

import java.util.ArrayList;

public class patientsAdapter extends RecyclerView.Adapter<patientsAdapter.progViewHolder> {

    private ArrayList<patients> data;
    private Context context;

    public patientsAdapter(ArrayList<patients> data, Context context){
        this.data = data;
        this.context = context;
    }

    @NonNull
    @Override
    public progViewHolder onCreateViewHolder(@NonNull final ViewGroup viewGroup, int i) {
        LayoutInflater l1 = LayoutInflater.from(viewGroup.getContext());
        View view = l1.inflate(R.layout.patient_pojo,viewGroup,false);
        final progViewHolder view2 = new progViewHolder(view);
        return view2;
    }

    @Override
    public void onBindViewHolder(@NonNull progViewHolder progViewHolder, int i) {
        final patients user = data.get(i);
        progViewHolder.mName.setText(user.getName());
        progViewHolder.mNumber.setText(user.getContact());
        String time = user.getSlot_date()+"\n"+user.getSlot_index();
        progViewHolder.mTime.setText(time);

        progViewHolder.mLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ActivityUser.class);
                Gson gson = new Gson();
                String patient = gson.toJson(user);
                intent.putExtra("User",patient);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class progViewHolder extends RecyclerView.ViewHolder{
        TextView mName,mNumber,mTime;
        CardView mLayout;
        public progViewHolder(View itemView){
            super(itemView);
            mName = itemView.findViewById(R.id.name);
            mNumber = itemView.findViewById(R.id.contact);
            mTime = itemView.findViewById(R.id.time);
            mLayout = itemView.findViewById(R.id.card);
        }
    }
}
