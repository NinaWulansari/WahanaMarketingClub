/**
 * Created by Nina
 */

package com.wahana.wahanamarketingclub.fragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

import com.wahana.wahanamarketingclub.R;
import com.wahana.wahanamarketingclub.activities.ActivitySalesmanActivity;
import com.wahana.wahanamarketingclub.activities.CatalogActivity;
import com.wahana.wahanamarketingclub.activities.CustomerActivity;
import com.wahana.wahanamarketingclub.activities.EventActivity;
import com.wahana.wahanamarketingclub.activities.HistoryActivity;
import com.wahana.wahanamarketingclub.activities.MainActivity;
import com.wahana.wahanamarketingclub.activities.QuizActivity;
import com.wahana.wahanamarketingclub.activities.ReportActivity;
import com.wahana.wahanamarketingclub.activities.SurveyActivity;
import com.wahana.wahanamarketingclub.activities.TrainingScheduleActivity;
import com.wahana.wahanamarketingclub.adapter.MoreAdapter;
import com.wahana.wahanamarketingclub.model.CustomerProspectIndex;

/**
 * Created by Nina on 2/19/2018.
 */

public class MoreFragmentSupervisor extends BaseFragment{

    GridView grid;
    String[] text = {
            "Catalog", "Activity", "Customer Prospect",
            "History", "Event", "Quiz",
            "Switch Salesman", "Customer", "Training Schedule",
            "Report", "Survey"
    };

    int[] images = {
            R.drawable.ic_galery, R.drawable.ic_activity, R.drawable.ic_customer_prospect,
            R.drawable.ic_history, R.drawable.ic_event,  R.drawable.ic_quiz,
            R.drawable.ic_switch, R.drawable.customer,R.drawable.ic_training_schedule,
            R.drawable.ic_report, R.drawable.ic_survey
    };


    public static BaseFragment newInstance(Context context) {
        MoreFragment f = new MoreFragment();
        return f;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup view = (ViewGroup) inflater.inflate(R.layout.fragment_more, null);
        MoreAdapter adapter = new MoreAdapter(getActivity(), text, images);
        GridView gv1=(GridView)view.findViewById(R.id.gridview);
        gv1.setAdapter(adapter);
        gv1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                final Intent intent;
                switch (position){
                    case 0:
                        intent = new Intent(getContext(), CatalogActivity.class);
                        break;
                    case 1:
                        intent = new Intent(getContext(), ActivitySalesmanActivity.class);
                        break;
                    case 2:
                        intent = new Intent(getContext(), CustomerProspectIndex.class);
                        break;
                    case 3:
                        intent = new Intent(getContext(), HistoryActivity.class);
                        break;
                    case 4:
                        intent = new Intent(getContext(), EventActivity.class);
                        break;
                    case 5:
                        intent = new Intent(getContext(), QuizActivity.class);
                        break;
                    case 6:
                        intent = new Intent(getContext(), CustomerActivity.class);
                        break;
                    case 7:
                        intent = new Intent(getContext(), CustomerActivity.class);
                        break;
                    case 8:
                        intent = new Intent(getContext(), TrainingScheduleActivity.class);
                        break;
                    case 9:
                        intent = new Intent(getContext(), ReportActivity.class);
                        break;
                    case 10:
                        intent = new Intent(getContext(), SurveyActivity.class);
                        break;
                    default:
                        intent = new Intent(getContext(), MoreFragment.class);
                        break;
                }
                startActivity(intent);
            }
        });


        ( (MainActivity)getActivity()).updateToolbarTitle("More");

        return view;
    }

    @Override
    public int getCount() {
        return 0;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View convertView, ViewGroup parent) {
        return null;
    }
}

