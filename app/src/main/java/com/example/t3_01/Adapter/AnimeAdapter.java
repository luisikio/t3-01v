package com.example.t3_01.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.t3_01.Entities.Animes;
import com.example.t3_01.R;
import com.example.t3_01.services.servicesWeb;
import com.squareup.picasso.Picasso;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class AnimeAdapter extends RecyclerView.Adapter<AnimeAdapter.AnimeViewHolder> {

    List<Animes> animes;
    //public ImageView imageView;

    public AnimeAdapter(List<Animes> animes) {
        this.animes = animes;
    }

    @NonNull
    @Override
    public AnimeAdapter.AnimeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_animes,parent,false);
        AnimeAdapter.AnimeViewHolder vh= new AnimeAdapter.AnimeViewHolder(view);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull AnimeAdapter.AnimeViewHolder holder, int position) {
        View vw=holder.itemView;

        Animes anime=animes.get(position);
        TextView itemName=holder.itemView.findViewById(R.id.textViewName);
        TextView itemDescription=holder.itemView.findViewById(R.id.textViewDescription);
        ImageView itemImg=holder.itemView.findViewById(R.id.logo);

        itemName.setText(anime.name);
        itemDescription.setText(anime.description);
        String url="https://www.cinemascomics.com/wp-content/uploads/2020/08/goku-dragon-ball-super-ultra-instinto.jpg";
        Picasso.get().load(url).into(itemImg);

        vw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Retrofit retrofit = new  Retrofit.Builder()
                        .baseUrl("https://6284e8f8a48bd3c40b77c373.mockapi.io/api/v1/")
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();

                servicesWeb services = retrofit.create(servicesWeb.class);
                Call<List<Animes>> call=services.getContacts();
            }
        });
    }

    @Override
    public int getItemCount() {
        return animes.size();
    }

    public class AnimeViewHolder extends RecyclerView.ViewHolder {
        public AnimeViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
