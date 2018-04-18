/**
 * Created by Nina
 */

package com.wahana.wahanamarketingclub.fragments;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;
import com.wahana.wahanamarketingclub.R;
import com.wahana.wahanamarketingclub.activities.EditProfileActivity;
import com.wahana.wahanamarketingclub.activities.MainActivity;
import com.wahana.wahanamarketingclub.model.LoginUser;
import com.wahana.wahanamarketingclub.utils.BlurImage;

import java.io.File;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;

import static android.app.Activity.RESULT_OK;
import static com.wahana.wahanamarketingclub.activities.LoginActivity.MY_LOGIN_PREF;
import static com.wahana.wahanamarketingclub.activities.LoginActivity.MY_LOGIN_PREF_KEY;
import static com.wahana.wahanamarketingclub.connect.API.baseURL;
import static com.wahana.wahanamarketingclub.connect.API.baseURLPicasso;


/**
 * A simple {@link Fragment} subclass.
 */

//@RuntimePermissions
public class ProfileFragment extends BaseFragment{

    @BindView(R.id.user_profile_photo)
    CircleImageView user_photo;
    @BindView(R.id.textView_display_name)
    TextView user_displayName;
    @BindView(R.id.user_group)
    TextView user_group;
    @BindView(R.id.textView_username)
    TextView user_username;
    @BindView(R.id.textView_phone_number)
    TextView user_phoneNumber;
    @BindView(R.id.textView_email)
    TextView user_email;
    @BindView(R.id.textView_birth)
    TextView user_birth;
    @BindView(R.id.textView_gender)
    TextView user_gender;

    @BindView(R.id.header_cover_image)
    ImageView backgroundIV;
    @BindView(R.id.refreshLayout)
    SwipeRefreshLayout swipe;

    public File tempFile;
    public ProfileFragment fragment;

    private int BLUR_PRECENTAGE = 50;

    protected final int REQUEST_CAMERA = 0;
    protected final int SELECT_FILE = 1;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        ButterKnife.bind(this, view);

        ( (MainActivity)getActivity()).updateToolbarTitle("Profile");

        profileData();

        swipe.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                profileData();
            }
        });

        return view;
    }

    public void profileData() {
        LoginUser savedUser = new Gson()
                .fromJson(getActivity()
                        .getSharedPreferences(MY_LOGIN_PREF, Context.MODE_PRIVATE)
                        .getString(MY_LOGIN_PREF_KEY, ""), LoginUser.class);

        String display_name = savedUser.getName();
        String group = savedUser.getTitle();
        String username = savedUser.getUsername();
        String phone_number = savedUser.getPhoneNumber();
        String email = savedUser.getEmail();
        String gender = savedUser.getGender();
        String photo = (String) savedUser.getImage();


        String birthDates = savedUser.getBirthDate();
        DateFormat outputFormat = new SimpleDateFormat("dd/MM/yyyy");
        DateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
        inputFormat.setTimeZone(TimeZone.getTimeZone("GMT"));
        try {
            Date dates = inputFormat.parse(birthDates);
            String outputText = outputFormat.format(dates);
            user_birth.setText(outputText);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        user_displayName.setText(display_name);
        user_group.setText(group);
        user_username.setText(username);
        user_phoneNumber.setText(phone_number);
        user_email.setText(email);
        user_gender.setText(gender);

        Picasso.with(getActivity())
                .load(baseURLPicasso + photo)
                .error(R.drawable.profile)
                .into(user_photo);

        Target target = new Target() {
            @Override
            public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
                backgroundIV.setImageBitmap(BlurImage.fastblur(bitmap, 1f, BLUR_PRECENTAGE));
            }

            @Override
            public void onBitmapFailed(Drawable errorDrawable) {

            }

            @Override
            public void onPrepareLoad(Drawable placeHolderDrawable) {

            }
        };
        backgroundIV.setTag(target);
        Picasso.with(getActivity())
                .load(baseURLPicasso + photo)
                .error(R.drawable.header_image)
                .into(target);

        swipe.setRefreshing(false);
    }


    @OnClick(R.id.user_edit_button)
    void editProfile(){
        EditProfileActivity.startActivity(getActivity());
    }

    @OnClick(R.id.user_camera_floating_button)
    void chooseProfilePic(){
        selectImage();
    }

    private void selectImage() {
        final String camera = "Take Photo";
        final String gallery = "Choose from Gallery";
        final CharSequence[] items = { camera, gallery};

        AlertDialog.Builder builder = new AlertDialog.Builder(ProfileFragment.this.getContext());
        builder.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int item) {
                if (items[item].equals(camera)) {
                    Intent takePicture = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

                    File file = new File(Environment.getExternalStorageDirectory(),

                            ".jpg");

                    String  outputFileUri= String.valueOf(Uri.fromFile(file));

                    Log.d("TAG", "outputFileUri intent" + outputFileUri);

                    takePicture.putExtra(MediaStore.EXTRA_OUTPUT, outputFileUri);

                    startActivityForResult(takePicture, 0);

                } else if (items[item].equals(gallery)) {

                    Intent pickPhoto = new Intent(Intent.ACTION_PICK,
                            android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    startActivityForResult(pickPhoto , 1);
                }
            }
        });
        builder.show();
    }



    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent imageReturnedIntent) {
        super.onActivityResult(requestCode, resultCode, imageReturnedIntent);
        switch (requestCode) {
            case 0:
                if (resultCode == RESULT_OK) {
                    Uri capturedImage = imageReturnedIntent.getData();
                    user_photo.setImageURI(capturedImage);
                }

                break;
            case 1:
                if (resultCode == RESULT_OK) {
                    Uri selectedImage = imageReturnedIntent.getData();
                    user_photo.setImageURI(selectedImage);
                }

                break;
        }
    }
}