package com.cvalera.doggos.model;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.cvalera.doggos.R;

import java.util.List;

public class AdaptadorDogs extends RecyclerView.Adapter<AdaptadorDogs.ViewHolderDogs> {

    private List<DogVo> listaDogs;

    public AdaptadorDogs(List<DogVo> listaDogs) {
        this.listaDogs = listaDogs;
    }

    public void setListaDogs(List<DogVo> listaDogs) {
        this.listaDogs = listaDogs;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolderDogs onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_dogs, null, false);
        return new ViewHolderDogs(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderDogs holder, int position) {
        holder.bind(listaDogs.get(position));
    }

    @Override
    public int getItemCount() {
        return listaDogs.size();
    }

    public class ViewHolderDogs extends RecyclerView.ViewHolder {
        private ImageView fotoDog;

        public ViewHolderDogs(@NonNull View itemView) {
            super(itemView);
            fotoDog = itemView.findViewById(R.id.ivDog);
        }

        void bind(DogVo dog) {
            Glide.with(this.itemView.getContext()).load(dog.getUrl()).into(this.fotoDog);
            Log.d("URL: ", dog.getUrl());
        }
    }
}
