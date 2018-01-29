package com.example.m_7el.training.country.adapters;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.m_7el.training.R;
import com.example.m_7el.training.country.models.CountryInfo;
import com.example.m_7el.training.country.utils.PhotoManager;

import java.util.List;


public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private final List<CountryInfo> countries;
    private OnItemClickListener mListener;
    private Activity activity;

    public interface OnItemClickListener {
        void onItemClick(CountryInfo countryInfo);
    }

    public RecyclerViewAdapter(RecyclerView recyclerView, List<CountryInfo> countries, Activity activity) {
        this.countries = countries;
        this.activity = activity;
        this.mListener = (OnItemClickListener) activity;

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

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
            userViewHolder.name.setText(country.getName());
            userViewHolder.region.setText(country.getRegion());

            PhotoManager.loadImage(activity, userViewHolder.mCountryImage);

            userViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                   mListener.onItemClick(country);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return countries.size();
    }


    private static class UserViewHolder extends RecyclerView.ViewHolder {
        private TextView name;
        private TextView region;
        private ImageView mCountryImage;


        private UserViewHolder(View view) {
            super(view);
            name = view.findViewById(R.id.country_date);
            region = view.findViewById(R.id.region);
            mCountryImage = view.findViewById(R.id.imageView3);
        }
    }
}