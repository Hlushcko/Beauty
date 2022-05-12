package com.example.beauty.recyclerview;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.beauty.R;
import com.example.beauty.WorkPhoto.PostPhoto;

import java.util.ArrayList;
import java.util.List;

public class RecycleViewFragment extends RecyclerView.Adapter<RecycleViewFragment.AdapterRecycle> {

    public List<PostPhoto> post = new ArrayList<>();
    private Context context;


    public RecycleViewFragment(Context _context){
        context = _context;
    }

    @SuppressLint("NotifyDataSetChanged")
    public void setList(List<PostPhoto> _post) {
        post = _post;
        notifyDataSetChanged();
    }


    @NonNull
    @Override
    public AdapterRecycle onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new AdapterRecycle( LayoutInflater.from(context).inflate(R.layout.images_user, parent,false));
    }


    @Override
    public void onBindViewHolder(@NonNull AdapterRecycle holder, int position) {
        holder.downloadImage(post.get(position).uriPhoto);
    }


    @Override
    public int getItemCount() {
        return post.size();
    }



    public static class AdapterRecycle extends RecyclerView.ViewHolder{

        private View view;
        private ImageView imageUser;


        public AdapterRecycle(@NonNull View itemView) {
            super(itemView);
            view = itemView;
            initView();
        }


        private void initView(){
            imageUser = view.findViewById(R.id.PhotoPeople);
        }


        public void downloadImage(String urlPhoto){

            Glide
                    .with(view.getContext())
                    .load(urlPhoto)
                    .error(R.drawable.ic_launcher_foreground)
                    .centerCrop()
                    .into(imageUser);
        }


    }

}
