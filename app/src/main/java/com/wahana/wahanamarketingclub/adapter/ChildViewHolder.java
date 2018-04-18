package com.wahana.wahanamarketingclub.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.wahana.wahanamarketingclub.R;

/**
 * Created by Lely
 */

public class ChildViewHolder extends RecyclerView.ViewHolder {

    TextView name;
    ImageView icon;
    TextView alamat;
    TextView no_hp;
    TextView status;
    public ChildViewHolder(View itemView) {
        super(itemView);
        name = (TextView) itemView.findViewById(R.id.child);
        icon = (ImageView) itemView.findViewById(R.id.image_view);
        alamat = (TextView) itemView.findViewById(R.id.custAlamatTextview);
        no_hp = (TextView) itemView.findViewById(R.id.custHpTextview);
        status = (TextView) itemView.findViewById(R.id.statusTextview);
    }
}