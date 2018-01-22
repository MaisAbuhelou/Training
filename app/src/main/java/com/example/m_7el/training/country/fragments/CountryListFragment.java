package com.example.m_7el.training.country.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.m_7el.training.R;
import com.example.m_7el.training.country.utils.RecyclerViewAdapter;
import com.example.m_7el.training.country.utils.SimpleDividerItemDecoration;
import com.example.m_7el.training.net.models.CountryInfo;
import com.example.m_7el.training.net.clients.CountryApiClient;
import com.example.m_7el.training.net.clients.RetrofitInterface;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CountryListFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private List<CountryInfo> countryInfo;
    private OnSelectedListener mListener;
    private RetrofitInterface retrofitInterface;

    public CountryListFragment() {
        // Required empty public constructor
    }


    // TODO: Rename and change types and number of parameters


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        if (getArguments() != null) {
//            mParam1 = getArguments().getString(ARG_PARAM1);
//            mParam2 = getArguments().getString(ARG_PARAM2);
//        }


    }


    private void getCountriesInfo() {


        retrofitInterface = CountryApiClient.getClient().create(RetrofitInterface.class);
        Call<List<CountryInfo>> call2 = retrofitInterface.getCountyInfo();
        call2.enqueue(new Callback<List<CountryInfo>>() {


            @Override
            public void onResponse(Call<List<CountryInfo>> call, Response<List<CountryInfo>> response) {

                countryInfo = response.body();
                RecyclerView recyclerView = (RecyclerView) getActivity().findViewById(R.id.recycler_view);
                recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
                recyclerView.addItemDecoration(new SimpleDividerItemDecoration(getActivity()));

                recyclerView.setAdapter(new RecyclerViewAdapter(recyclerView, countryInfo, getActivity()));

            }

            @Override
            public void onFailure(Call<List<CountryInfo>> call, Throwable t) {

                t.getMessage();
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_country_list, container, false);
        getCountriesInfo();

        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
//        if (mListener != null) {
//            mListener.onFragmentInteraction(uri);
//        }
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

    // Container Activity must implement this interface
    public interface OnSelectedListener {
        public void onCountrySelected(int position);
    }

}
