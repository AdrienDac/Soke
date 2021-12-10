package com.example.cours;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;


import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Adapter_elec extends RecyclerView.Adapter<Adapter_elec.ViewHolder_elec> {

    int images[];
    Context context;
    ImageButton fav_button;
    DatabaseReference favouriteref;
    FirebaseDatabase database = FirebaseDatabase.getInstance();

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
    public void onBindViewHolder(@NonNull ViewHolder_elec holder, final int position) {
        holder.MyImage.setImageResource(images[holder.getAdapterPosition()]);

        holder.mainlayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, infos_mainsquare.class);
                intent.putExtra("mylogo", images[holder.getAdapterPosition()]);
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
            mainlayout = itemView.findViewById(R.id.Constraintlayout);
        }
    }
}
