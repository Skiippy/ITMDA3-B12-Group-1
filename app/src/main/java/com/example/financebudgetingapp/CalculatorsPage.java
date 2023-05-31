package com.example.financebudgetingapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.android.material.slider.Slider;

public class CalculatorsPage extends AppCompatActivity {

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.calculators_page);

        //all calculators
        LinearLayout ll50_30_20 = findViewById(R.id.ll50_30_20);
        LinearLayout llEmergencyFundCalculator = findViewById(R.id.llEmergencyFundCalculator);

        //spinner code for changing calculators
        Spinner spCalculatorType = findViewById(R.id.spCalculatorType);
        String[] spinnerContent = new String[] {
                "50/30/20 Rule", "Emergency Fund", "Car Affordability", "House Affordability"
        };
         ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, spinnerContent);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spCalculatorType.setAdapter(adapter);
        spCalculatorType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position == 0){
                    ll50_30_20.setVisibility(View.VISIBLE);
                }else{
                    ll50_30_20.setVisibility(View.GONE);
                }
                if (position == 1){
                    llEmergencyFundCalculator.setVisibility(View.VISIBLE);
                }else{
                    llEmergencyFundCalculator.setVisibility(View.GONE);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        //50/30/20
        EditText edtGrossIncome = findViewById(R.id.edtGrossIncome);
        Button btnCalculate503020 = findViewById(R.id.btnCalculate503020);
        EditText edtBreakdownNeeds = findViewById(R.id.edtBreakdownNeeds);
        EditText edtBreakdownWants = findViewById(R.id.edtBreakdownWants);
        EditText edtBreakdownSavingInvesting = findViewById(R.id.edtBreakdownSavingInvesting);

        btnCalculate503020.setOnClickListener(v -> {
            String grossIncome = edtGrossIncome.getText().toString();
            if (!grossIncome.isEmpty()){
                edtBreakdownNeeds.setText(Double.toString(Integer.parseInt(grossIncome) * 0.5));
                edtBreakdownWants.setText(Double.toString(Integer.parseInt(grossIncome) * 0.3));
                edtBreakdownSavingInvesting.setText(Double.toString(Integer.parseInt(grossIncome) * 0.2));
            }
        });

        //Emergency Fund
        EditText edtMonthlyIncome = findViewById(R.id.edtMonthlyIncome);
        TextView tvEmergencyFundAmount = findViewById(R.id.tvEmergencyFund);

        Slider sdNumMonths = findViewById(R.id.sdNumMonths);
        sdNumMonths.addOnChangeListener(new Slider.OnChangeListener() {
            @Override
            public void onValueChange(@NonNull Slider slider, float value, boolean fromUser) {
                if (!edtMonthlyIncome.toString().isEmpty()){
                    int MonthlyIncome = Integer.parseInt(edtMonthlyIncome.getText().toString());
                    tvEmergencyFundAmount.setText(Integer.toString((int) (value * MonthlyIncome)));
                }
            }
        });

        //create on change for edt
    }
}