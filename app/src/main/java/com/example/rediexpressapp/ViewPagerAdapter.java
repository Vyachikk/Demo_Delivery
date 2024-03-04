package com.example.rediexpressapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

public class ViewPagerAdapter extends PagerAdapter {

    Context context;

    int images[] = {

            R.drawable.pic1,
            R.drawable.pic2,
            R.drawable.pic3,
    };

    int headings[] = {

            R.string.heading_start,
            R.string.heading_next,
            R.string.heading_last,
    };

    int description[] = {

            R.string.desc_start,
            R.string.desc_next,
            R.string.desc_last,
    };

    public ViewPagerAdapter(Context context){

        this.context = context;

    }

    @Override
    public int getCount() {
        return headings.length;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == (LinearLayout) object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.slider_layout,container,false);

        ImageView slidetitleimage = (ImageView) view.findViewById(R.id.titleImage);
        TextView slideHeading = (TextView) view.findViewById(R.id.texttitle);
        TextView slideDesciption = (TextView) view.findViewById(R.id.textdeccription);

        slidetitleimage.setImageResource(images[position]);
        slideHeading.setText(headings[position]);
        slideDesciption.setText(description[position]);

        container.addView(view);

        return view;

    }
    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((LinearLayout)object);
    }
}
