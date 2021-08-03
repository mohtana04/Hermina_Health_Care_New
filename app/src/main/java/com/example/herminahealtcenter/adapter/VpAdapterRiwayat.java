package com.example.herminahealtcenter.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.herminahealtcenter.fragment.RiwayatRwi;
import com.example.herminahealtcenter.fragment.RiwayatRwj;

public class VpAdapterRiwayat extends FragmentPagerAdapter {

    public VpAdapterRiwayat(@NonNull FragmentManager fm) {
        super(fm);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        Fragment FVPRiwLayout = new Fragment();
        switch (position) {
            case 0:
                FVPRiwLayout = (Fragment) (new RiwayatRwi());
                break;
            case 1:
                FVPRiwLayout = (Fragment) (new RiwayatRwj());
                break;
        }
        return FVPRiwLayout;
    }

    @Override
    public int getCount() {
        return 2;
    }
}
