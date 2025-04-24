package com.tourism.sanchari;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import java.util.List;

public class PlaceMainAdapter extends RecyclerView.Adapter<PlaceMainAdapter.PlaceViewHolder> {

    private List<PlaceMain> placeList;
    private Context context;

    public PlaceMainAdapter(Context context , List<PlaceMain> placeList) {
        this.context = context;
        this.placeList = placeList;
    }

    @NonNull
    @Override
    public PlaceViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.place_item, parent, false);
        return new PlaceViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PlaceViewHolder holder, int position) {
        final Boolean[] BookMarkStatus={false};

        PlaceMain currentPlace = placeList.get(position);
        holder.placeName.setText(currentPlace.getPlaceName());
        holder.placeAddress.setText(currentPlace.getState());
        holder.BookmarkRL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                BookMarkStatus[0]=!BookMarkStatus[0];

                if(BookMarkStatus[0]){
                    holder.BookmarkRL.setImageDrawable(
                            ContextCompat.getDrawable(context, R.drawable.bookmarkcheckfill)
                    );
                }else{
                    holder.BookmarkRL.setImageDrawable(
                            ContextCompat.getDrawable(context, R.drawable.bookmarkplus)
                    );
                }
            }
        });
        Picasso.get().load("https://sanchariweb.onrender.com/images/"+currentPlace.getImageUrl()).into(new Target() {
            @Override
            public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
                BitmapDrawable background=new BitmapDrawable(holder.itembg.getResources(),bitmap);
                holder.itembg.setBackground(background);
            }

            @Override
            public void onBitmapFailed(Exception e, Drawable errorDrawable) {

            }

            @Override
            public void onPrepareLoad(Drawable placeHolderDrawable) {

            }
        });
        if(currentPlace.getCategory().equals("Spiritual")){
            holder.CatBadge.setImageResource(R.drawable.sp_badge);
        } else if (currentPlace.getCategory().equals("Adventure")) {
            holder.CatBadge.setImageResource(R.drawable.ad_badge);
        }else{
            holder.CatBadge.setImageResource(R.drawable.his_badge);
        }


    }

    @Override
    public int getItemCount() {
        return placeList.size();
    }

    // ✅ Fix: Call notifyDataSetChanged on "this" adapter instance
    public void updateList(List<PlaceMain> newList) {
        placeList.clear();
        placeList.addAll(newList);
        notifyDataSetChanged(); // Corrected
    }

    public static class PlaceViewHolder extends RecyclerView.ViewHolder {
        LinearLayout itembg;
        TextView placeName;
        TextView placeAddress;
        ImageButton BookmarkRL;
        ImageView CatBadge;

        public PlaceViewHolder(@NonNull View itemView) {
            super(itemView);
            placeName = itemView.findViewById(R.id.placeName);
            placeAddress = itemView.findViewById(R.id.placeAddress);
            BookmarkRL=itemView.findViewById(R.id.bookmarkRL);
            itembg=itemView.findViewById(R.id.itembg);
            CatBadge=itemView.findViewById(R.id.catbadge);
        }
    }
}
