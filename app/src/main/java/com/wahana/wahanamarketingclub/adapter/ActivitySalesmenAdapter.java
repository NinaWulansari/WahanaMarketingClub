package com.wahana.wahanamarketingclub.adapter;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.wahana.wahanamarketingclub.R;
import com.wahana.wahanamarketingclub.model.ActivitySalesmenIndex;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.TimeZone;

/**
 * Created by Nina on 4/9/2018.
 */

public class ActivitySalesmenAdapter extends RecyclerView.Adapter<ActivitySalesmenAdapter.ActivityViewHolder> {
    ArrayList<ActivitySalesmenIndex> datasSet;

    public ActivitySalesmenAdapter(ArrayList<ActivitySalesmenIndex> tempData) {
        this.datasSet = tempData;
    }


    @Override
    public ActivitySalesmenAdapter.ActivityViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.content_activity_salesmen, parent, false);

        return new ActivityViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ActivitySalesmenAdapter.ActivityViewHolder holder, int position) {
        ActivitySalesmenIndex activitySalesmenIndex = datasSet.get(position);
        Log.i("bebet", "onBindViewHolder: "+activitySalesmenIndex);

        holder.subject.setText(activitySalesmenIndex.getActivitySubject());
        holder.status.setText(activitySalesmenIndex.getActivityStatus());
//        holder.tgl.setText(activitySalesmenIndex.getActivityTgl());
        String activity_dates = activitySalesmenIndex.getActivityTgl();
        Log.i("tanggal", "onBindViewHolder: " + activity_dates);
        DateFormat outputFormat = new SimpleDateFormat("dd/MM/yyyy");
        DateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
        inputFormat.setTimeZone(TimeZone.getTimeZone("GMT"));
        try {
            Date dates = inputFormat.parse(activity_dates);
            String outputText = outputFormat.format(dates);
            holder.tgl.setText(outputText);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        holder.note.setText(activitySalesmenIndex.getActivityNote());
    }

    @Override
    public int getItemCount() {
        return datasSet.size();
    }

    public class ActivityViewHolder extends RecyclerView.ViewHolder {
        public TextView subject, status, tgl, note;

        public ActivityViewHolder(View itemView) {
            super(itemView);
            subject = itemView.findViewById(R.id.typeActivity);
            status = itemView.findViewById(R.id.statusActivity);
            tgl = itemView.findViewById(R.id.tglActivity);
            note = itemView.findViewById(R.id.noteActivity);

        }
    }
}
