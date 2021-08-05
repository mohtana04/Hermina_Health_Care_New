package com.example.herminahealtcenter.adapter;

import android.content.Context;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.example.herminahealtcenter.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class VpImageSliderAdapter extends PagerAdapter {

    private List<String> stringList;
    private LayoutInflater inflater;
    private Context context;

    public VpImageSliderAdapter(Context context, List<String> stringList) {
        this.context = context;
        this.stringList = stringList;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    @Override
    public int getCount() {
        return stringList.size();
    }

    @Override
    public Object instantiateItem(ViewGroup view, int position) {
        View imageLayout = inflater.inflate(R.layout.image_banner_adapter, view, false);
        assert imageLayout != null;
        final ImageView imageView = imageLayout.findViewById(R.id.IVimagebanneradapter);
        Picasso.get().load(stringList.get(position))
                .placeholder(R.drawable.img_no_images)
                .error(R.drawable.img_no_images)
                .into(imageView);
        view.addView(imageLayout, 0);
        return imageLayout;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view.equals(object);
    }

    @Override
    public void restoreState(Parcelable state, ClassLoader loader) {

    }

    @Override
    public Parcelable saveState() {
        return null;
    }
}
