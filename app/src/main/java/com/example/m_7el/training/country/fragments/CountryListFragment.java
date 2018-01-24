package com.example.m_7el.training.country.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.m_7el.training.R;
import com.example.m_7el.training.country.adapters.RecyclerViewAdapter;
import com.example.m_7el.training.country.utils.SimpleDividerItemDecoration;
import com.example.m_7el.training.net.models.CountryInfo;
import com.example.m_7el.training.net.clients.CountryApiClient;
import com.example.m_7el.training.net.clients.RetrofitInterface;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CountryListFragment extends Fragment {


    private List<CountryInfo> mCountryInfo;
    private OnSelectedListener mListener;
    private RetrofitInterface retrofitInterface;

    public CountryListFragment() {
        // Required empty public constructor
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.w("Fragment", "CountryListFragment created  " + this.toString());

        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_country_list, container, false);
        getCountriesInfo();

        return view;
    }


    private void getCountriesInfo() {

// get All countries
        retrofitInterface = CountryApiClient.getClient().create(RetrofitInterface.class);
        Call<List<CountryInfo>> call2 = retrofitInterface.getCountyInfo();
        call2.enqueue(new Callback<List<CountryInfo>>() {


            @Override
            public void onResponse(Call<List<CountryInfo>> call, Response<List<CountryInfo>> response) {

                mCountryInfo = response.body();
                RecyclerView recyclerView = getActivity().findViewById(R.id.recycler_view);
                recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
                recyclerView.addItemDecoration(new SimpleDividerItemDecoration(getActivity()));
                recyclerView.setAdapter(new RecyclerViewAdapter(recyclerView, mCountryInfo, getActivity()));

            }

            @Override
            public void onFailure(Call<List<CountryInfo>> call, Throwable t) {

                t.getMessage();
            }
        });
    }
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

    }


    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface OnSelectedListener {
        public void onCountrySelected(int position);
    }

}
