package id.ac.awesomeapp.adapter;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

import id.ac.awesomeapp.R;
import id.ac.awesomeapp.model.ImageModel;

public class ImageAdapter extends RecyclerView.Adapter<ImageViewHolder>{
    private Context context;
    private List<ImageModel> imageModels;

    public ImageAdapter(Context context, List<ImageModel> imageModels) {
        this.context = context;
        this.imageModels = imageModels;
    }

    @NonNull
    @Override
    public ImageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.list_image_item, parent, false);
        return new ImageViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ImageViewHolder holder, int position) {
        Glide.with(context)
                .load(imageModels.get(position).getUrlImage())
                .into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return imageModels.size();
    }
}

class ImageViewHolder extends RecyclerView.ViewHolder {
    ImageView imageView;
    String photograph;
    String photograph_url;
    public ImageViewHolder(@NonNull View itemView) {
        super(itemView);
        imageView = itemView.findViewById(R.id.imageList);

    }
}
