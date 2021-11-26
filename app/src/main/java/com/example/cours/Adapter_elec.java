package com.example.cours;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;


import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class Adapter_elec extends RecyclerView.Adapter<Adapter_elec.ViewHolder_elec> {

    int images[];
    Context context;

    public  Adapter_elec(Context ct, String s1[], String s2 [], int img[]){
        context = ct;
        images = img;

    }
    @NonNull
    @Override
    public ViewHolder_elec onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.one_row,parent,false);
        return new ViewHolder_elec(view);

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder_elec holder, int position) {
        holder.imagebutton_elctro.setImageResource(images[position]);
    }

    @Override
    public int getItemCount() {
        return images.length;
    }

    public class ViewHolder_elec extends RecyclerView.ViewHolder{

        ImageButton imagebutton_elctro;

        public ViewHolder_elec(@NonNull View itemView) {
            super(itemView);

            imagebutton_elctro = itemView.findViewById(R.id.imagebutton_elctro);
        }
    }
}
