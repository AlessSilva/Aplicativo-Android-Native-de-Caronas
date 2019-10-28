package com.example.appcaronamobile.ui.main;

import android.content.Context;

import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.appcaronamobile.Fragments.CaronasParticipacao;
import com.example.appcaronamobile.Fragments.MinhasCaronas;
import com.example.appcaronamobile.R;

/**
 * A [FragmentPagerAdapter] that returns a fragment corresponding to
 * one of the sections/tabs/pages.
 */
public class SectionsPagerAdapter extends FragmentPagerAdapter {

    private final Context mContext;

    public SectionsPagerAdapter(Context context, FragmentManager fm) {
        super(fm);
        mContext = context;
    }

    @Override
    public Fragment getItem(int position) {

        Fragment fragment = null;

        if ( position == 0 ){
            fragment = new CaronasParticipacao();
        }else if ( position == 1 ){
            fragment = new MinhasCaronas();
        }

        return fragment;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {

        switch ( position ){

            case 0:
                return "Participações";

            case 1:
                return "Minhas Caronas";


        }

        return null;
    }

    @Override
    public int getCount() {

        return 2;
    }
}