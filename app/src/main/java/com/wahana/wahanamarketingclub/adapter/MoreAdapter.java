package com.wahana.wahanamarketingclub.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import com.wahana.wahanamarketingclub.R;
import com.wahana.wahanamarketingclub.activities.ActivityActivity;
import com.wahana.wahanamarketingclub.fragments.MoreFragment;

/**
 * Created by Nina on 1/31/2018.
 */

public class MoreAdapter  extends BaseAdapter {

    private Context ctx;
    private Context context;

    public MoreAdapter(Context c)
    {
        ctx=c;
    }
    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return pics.length;
    }

    @Override
    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        ImageView iv;
        if (convertView == null) {  // if it's not recycled, initialize some attributes
            iv = new ImageView(ctx);
            iv.setLayoutParams(new GridView.LayoutParams(195, 195));
//            iv.setScaleType(ImageView.ScaleType.CENTER_CROP);
        } else {
            iv = (ImageView) convertView;
        }

//        switch (position) {
//            case 0:
//                Intent intent = new Intent(getActivity().getApplicationContext(), ActivityActivity.class);
//                break;
//        }
        iv.setImageResource(pics[position]);
        return iv;

    }



    private Integer[] pics={
            R.drawable.activity,
            R.drawable.event,
            R.drawable.history,
            R.drawable.quiz,
            R.drawable.catalog,
            R.drawable.reward,
            R.drawable.report,
            R.drawable.training,
            R.drawable.survey
    };

}
