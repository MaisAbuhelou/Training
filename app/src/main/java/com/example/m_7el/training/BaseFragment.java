package com.example.m_7el.training;

import android.content.Context;
import android.support.v4.app.Fragment;

public class BaseFragment extends Fragment {

    protected boolean isFragmentVisible = false;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        isFragmentVisible = true;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        isFragmentVisible = false;
    }

    public boolean isFragmentVisible() {
        return isFragmentVisible;
    }
}
