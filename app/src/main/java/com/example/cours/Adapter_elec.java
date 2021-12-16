package com.example.cours;

import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;


import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;


public class Adapter_elec extends RecyclerView.Adapter<Adapter_elec.ViewHolder_elec> {

    int images[];
    String doc_name[];
    Context context;


    public  Adapter_elec(Context ct, String s1[], String nam [], int img[]){
        context = ct;
        images = img;
        doc_name = nam;



    }

    @NonNull
    @Override
    public ViewHolder_elec onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.one_row,parent,false);
        return new ViewHolder_elec(view);


    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder_elec holder, final int position) {
        holder.MyImage.setImageResource(images[holder.getAdapterPosition()]);

        holder.mainlayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, infos_mainsquare.class);
                intent.putExtra("mylogo", images[holder.getAdapterPosition()]);
                intent.putExtra("doc_name", doc_name[holder.getAdapterPosition()]);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {

        return images.length;
    }

    public class ViewHolder_elec extends RecyclerView.ViewHolder{

        ImageView MyImage;
        ConstraintLayout mainlayout;


        public ViewHolder_elec(@NonNull View itemView) {
            super(itemView);

            MyImage = itemView.findViewById(R.id.image);
            mainlayout = itemView.findViewById(R.id.row);
        }
    }
}
