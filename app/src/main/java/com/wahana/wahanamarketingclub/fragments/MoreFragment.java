/**
 * Created by Nina
 */

package com.wahana.wahanamarketingclub.fragments;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.squareup.picasso.Picasso;
import com.wahana.wahanamarketingclub.R;
import com.wahana.wahanamarketingclub.activities.ActivitySalesmanActivity;
import com.wahana.wahanamarketingclub.activities.CatalogActivity;
import com.wahana.wahanamarketingclub.activities.CustomerActivity;
import com.wahana.wahanamarketingclub.activities.CustomerProspectIndexActivity;
import com.wahana.wahanamarketingclub.activities.EventActivity;
import com.wahana.wahanamarketingclub.activities.HistoryActivity;
import com.wahana.wahanamarketingclub.activities.MainActivity;
import com.wahana.wahanamarketingclub.activities.QuizActivity;
import com.wahana.wahanamarketingclub.activities.ReportActivity;
import com.wahana.wahanamarketingclub.activities.RewardActivity;
import com.wahana.wahanamarketingclub.activities.SurveyActivity;
import com.wahana.wahanamarketingclub.activities.TrainingScheduleActivity;
import com.wahana.wahanamarketingclub.adapter.MoreAdapter;
import com.wahana.wahanamarketingclub.model.LoginUser;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.wahana.wahanamarketingclub.activities.LoginActivity.MY_LOGIN_PREF;
import static com.wahana.wahanamarketingclub.activities.LoginActivity.MY_LOGIN_PREF_KEY;
import static com.wahana.wahanamarketingclub.connect.API.baseURLPicasso;


/**
 * A simple {@link Fragment} subclass.
 */

public class MoreFragment extends BaseFragment{

    @BindView(R.id.nameProfile)
    TextView profileName;
    @BindView(R.id.statusProfile)
    TextView status;
    @BindView(R.id.avatar)
    ImageView avatar;

    GridView grid;
    String[] text = {
            "Customer Prospect", "Customer", "Activity",
            "History", "Event", "Training Schedule",
            "Catalog","Quiz", "Reward",
            "Report", "Survey"
    };

    int[] images = {
            R.drawable.ic_customer_prospect, R.drawable.customer, R.drawable.ic_activity,
            R.drawable.ic_history, R.drawable.ic_event,  R.drawable.ic_training_schedule,
            R.drawable.ic_galery, R.drawable.ic_quiz, R.drawable.ic_reward,
            R.drawable.ic_report, R.drawable.ic_survey

    };


    public static BaseFragment newInstance(Context context) {
        MoreFragment f = new MoreFragment();
        return f;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        ViewGroup view = (ViewGroup) inflater.inflate(R.layout.fragment_more, null);
        MoreAdapter adapter = new MoreAdapter(getActivity(), text, images);
        GridView gv1=(GridView)view.findViewById(R.id.gridview);
        gv1.setAdapter(adapter);

        ButterKnife.bind(this, view);
//        gv1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//
//            }
//        });
        gv1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                final Intent intent;
                switch (position){
                    case 0:
                        intent = new Intent(getContext(), CustomerProspectIndexActivity.class);
                        break;
                    case 1:
                        intent = new Intent(getContext(), CatalogActivity.class);
                        break;
                    case 2:
                        intent = new Intent(getContext(), ActivitySalesmanActivity.class);
                        break;
                    case 3:
                        intent = new Intent(getContext(), HistoryActivity.class);
                        break;
                    case 4:
                        intent = new Intent(getContext(), EventActivity.class);
                        break;
                    case 5:
                        intent = new Intent(getContext(), CustomerActivity.class);
                        break;
                    case 6:
                        intent = new Intent(getContext(), QuizActivity.class);
                        break;
                    case 7:
                        intent = new Intent(getContext(), RewardActivity.class);
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
        profileData();
        return view;
    }

    public void profileData() {
        LoginUser savedUser = new Gson()
                .fromJson(getActivity()
                        .getSharedPreferences(MY_LOGIN_PREF, Context.MODE_PRIVATE)
                        .getString(MY_LOGIN_PREF_KEY, ""), LoginUser.class);

        String name = savedUser.getName();
        profileName.setText(name);
        String statuss = savedUser.getTitle();
        status.setText(statuss);

        String photo = (String) savedUser.getImage();
        Picasso.with(getActivity())
                .load(baseURLPicasso+ photo)
                .error(R.drawable.profile)
                .into(avatar);
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
