package com.example.m_7el.training.country.utils;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.m_7el.training.R;
import com.example.m_7el.training.net.models.CountryInfo;

import java.util.List;


public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private final int VIEW_TYPE_ITEM = 0;
    private final int VIEW_TYPE_LOADING = 1;
    private final List<CountryInfo> countries;
    private OnItemClickListener listener;

    private boolean isLoading;
    private Activity activity;

    private int visibleThreshold = 5;
    private int lastVisibleItem, totalItemCount;
    private Context mContext;

    public interface OnItemClickListener {
        void onItemClick(CountryInfo countryInfo);
    }


    public RecyclerViewAdapter(RecyclerView recyclerView, List<CountryInfo> countries, Activity activity) {
        this.countries = countries;
        this.activity = activity;
        this.listener = (OnItemClickListener) activity;


        final LinearLayoutManager linearLayoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                totalItemCount = linearLayoutManager.getItemCount();
                lastVisibleItem = linearLayoutManager.findLastVisibleItemPosition();

            }
        });
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(activity).inflate(R.layout.country_card, parent, false);
        return new UserViewHolder(view);

    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        if (holder instanceof UserViewHolder) {
            final CountryInfo country = countries.get(position);
            UserViewHolder userViewHolder = (UserViewHolder) holder;
            userViewHolder.name.setText(country.getName().toString());
            userViewHolder.region.setText(country.getRegion().toString());
//            RequestOptions options = new RequestOptions()
//                    .centerCrop()
//                    .placeholder(R.mipmap.ic_launcher_round)
//                    .error(R.mipmap.ic_launcher_round);
//
//

       PhotoManager.loadImage(activity,userViewHolder.mCountryImage);

            userViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onItemClick(country);
                }
            });
        }
    }

    @Override
    public int getItemCount() {

        return countries.size();
    }


    private class UserViewHolder extends RecyclerView.ViewHolder {
        public TextView name;
        public TextView region;
        public ImageView mCountryImage;


        public UserViewHolder(View view) {
            super(view);
            name = (TextView) view.findViewById(R.id.country_name);
            region = (TextView) view.findViewById(R.id.region);
            mCountryImage = (ImageView) view.findViewById(R.id.imageView3);
        }
    }
}