package com.rsherminasamarinda.herminahealtcenter.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.rsherminasamarinda.herminahealtcenter.fragment.HomeFragment;
import com.rsherminasamarinda.herminahealtcenter.fragment.InformationFragment;
import com.rsherminasamarinda.herminahealtcenter.fragment.ProfileFragment;

public class VpAdapterClass extends FragmentPagerAdapter {

    public VpAdapterClass(@NonNull FragmentManager fm) {
        super(fm);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        Fragment FVPlayout = new Fragment();
        switch (position) {
            case 0:
                FVPlayout = (Fragment) (new HomeFragment());
                break;
            case 1:
                FVPlayout = (Fragment) (new InformationFragment());
                break;
            case 2:
                FVPlayout = (Fragment) (new ProfileFragment());
        }
        return FVPlayout;
    }

    @Override
    public int getCount() {
        return 3;
    }
}
