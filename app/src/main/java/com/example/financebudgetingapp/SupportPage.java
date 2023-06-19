package com.example.financebudgetingapp;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.media.Image;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class SupportPage extends AppCompatActivity {

    boolean expanded = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.support_page);

        LinearLayout llBody = (LinearLayout) findViewById(R.id.llBody);
        //Container drawable
        @SuppressLint("UseCompatLoadingForDrawables") Drawable llContainerDrawable = getResources().getDrawable(R.drawable.support_foreground);

        String[] values = getResources().getStringArray(R.array.my_array);



        //selected number of times
        for (String value: values){
            //container for holding all components
            LinearLayout llContainer = new LinearLayout(this);
            LinearLayout.LayoutParams containerLayoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            containerLayoutParams.setMargins(0, 0, 0, dpToPx(20));
            llContainer.setLayoutParams(containerLayoutParams);
            llContainer.setOrientation(LinearLayout.VERTICAL);
            llContainer.setBackground(llContainerDrawable);
            llContainer.setPadding(dpToPx(10), dpToPx(10), dpToPx(10), dpToPx(10));

            LinearLayout llContainerHeader = new LinearLayout(this);
            LinearLayout.LayoutParams headerLayoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            headerLayoutParams.setMargins(0, 0, 0, 20);
            llContainerHeader.setLayoutParams(headerLayoutParams);
            llContainerHeader.setOrientation(LinearLayout.HORIZONTAL);

            //adding ContainerHeader to Main Container
            llContainer.addView(llContainerHeader);


            ImageView ivHeaderIcon = new ImageView(this);
            ivHeaderIcon.setLayoutParams(new ViewGroup.LayoutParams(dpToPx(24), dpToPx(24)));
            ivHeaderIcon.setImageDrawable(getResources().getDrawable(R.drawable.investion_icon));

            TextView tvHeading = new TextView(this);
            tvHeading.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT));
            tvHeading.setText("Investing");
            tvHeading.setTextColor(getResources().getColor(R.color.text));
            tvHeading.setGravity(Gravity.CENTER);

            View vSpacer = new View(this);
            LinearLayout.LayoutParams spacerLayoutParams = new LinearLayout.LayoutParams(0, 0, 1.0f);
            vSpacer.setLayoutParams(spacerLayoutParams);

            ImageButton ibExpandText = new ImageButton(this);
            ibExpandText.setLayoutParams(new ViewGroup.LayoutParams(dpToPx(24), dpToPx(24)));
            ibExpandText.setImageDrawable(getResources().getDrawable(R.drawable.arrow_downwards));
            int selectableItemBackgroundBorderlessId = getResources().getIdentifier(
                    "selectableItemBackgroundBorderless", "attr", getPackageName()
            );
            if (selectableItemBackgroundBorderlessId != 0) {
                TypedValue typedValue = new TypedValue();
                getTheme().resolveAttribute(selectableItemBackgroundBorderlessId, typedValue, true);
                ibExpandText.setBackgroundResource(typedValue.resourceId);
            }



            //adding header compnents to header container
            llContainerHeader.addView(ivHeaderIcon);
            llContainerHeader.addView(tvHeading);
            llContainerHeader.addView(vSpacer);
            llContainerHeader.addView(ibExpandText);


        //Text Container
            LinearLayout llTextContainer = new LinearLayout(this);
            llTextContainer.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            llTextContainer.setOrientation(LinearLayout.HORIZONTAL);

            TextView tvText = new TextView(this);
            tvText.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            tvText.setMaxLines(3);
            tvText.setText(value);
            tvText.setTextColor(getResources().getColor(R.color.text));

            //expand tvText's max lines when ibExpand is clicked
            ibExpandText.setOnClickListener(v ->{
                if (!expanded) {
                    tvText.setMaxLines(Integer.MAX_VALUE);
                    ibExpandText.setImageDrawable(getResources().getDrawable(R.drawable.arrow_left));
                    expanded = true;
                }else{
                    tvText.setLines(3);
                    ibExpandText.setImageDrawable(getResources().getDrawable(R.drawable.arrow_downwards));
                    expanded = false;
                }
            });

            llTextContainer.addView(tvText);

            llContainer.addView(llTextContainer);
            llBody.addView(llContainer);


        }

        //navigation Components
        ImageView btnSupportPage = (ImageView) findViewById(R.id.btnHomePage);
        ImageView btnGoalsPage = (ImageView) findViewById(R.id.btnGoalsPage);
        ImageButton ibProfileButton = (ImageButton) findViewById(R.id.ibProfileButton);
        ImageView ibCalculatorsPage = (ImageView) findViewById(R.id.ibCalculatorsPage);

        //navigation
        btnSupportPage.setOnClickListener(v -> startOverviewPage());
        btnGoalsPage.setOnClickListener(v -> startGoalsPage());
        ibProfileButton.setOnClickListener(v -> startSupport());
        ibCalculatorsPage.setOnClickListener(v -> startCalculatorsPage());

    }

    public void startOverviewPage(){
        startActivity(new Intent(this, OverviewPage.class));
    }

    public void startGoalsPage(){
        startActivity(new Intent(this, GoalsPage.class));
    }

    public void startSupport(){
        startActivity(new Intent(this, SupportPage.class));
    }

    public void startCalculatorsPage(){
        startActivity(new Intent(this, CalculatorsPage.class));
    }

    private int dpToPx(int dp) {
        float scale = getApplicationContext().getResources().getDisplayMetrics().density;
        return (int) (dp * scale + 0.5f);
    }

    private List<String> test(){
        Field[] fields = R.string.class.getFields();
        List<String> stringList = new ArrayList<>();

        for (Field field : fields) {
            if (field.getType() == int.class) {
                try {
                    int resourceId = field.getInt(null);
                    String value = getResources().getString(resourceId);
                    stringList.add(value);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }

        return stringList;

    }

}