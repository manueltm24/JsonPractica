package com.example.jsonpractica.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.jsonpractica.R;
import com.example.jsonpractica.User;
import com.squareup.picasso.Picasso;

import java.util.List;

public class RecycleViewAdapter extends RecyclerView.Adapter<RecycleViewAdapter.Holder> {
    private String[] items;
    private Context context;
    private List<User> userList;

    public RecycleViewAdapter(Context context, List<User> userList) {
        this.userList = userList;
        this.context = context;
    }


    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.layout_type_1, viewGroup, false);
        Holder holder = new Holder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int i) {
//        String item = items[i];
//        String name = String.format("%s - %d", item, i);
        TextView textView_name = holder.itemView.findViewById(R.id.name);
        TextView textView_description = holder.itemView.findViewById(R.id.my_description);
        ImageView imageView = holder.itemView.findViewById(R.id.my_image);
        textView_name.setText(userList.get(i).getName());
        textView_description.setText(userList.get(i).getDescription());

        Picasso.get().load(userList.get(i).getImage_url()).into(imageView);



    }

    @Override
    public int getItemCount() {
        return userList.size();
    }

    @Override
    public int getItemViewType(int position) {
        return position % 2 == 0? 1 : 2;
    }

    public static class Holder extends RecyclerView.ViewHolder{
        public Holder(@NonNull View itemView) {
            super(itemView);
        }
    }

}
