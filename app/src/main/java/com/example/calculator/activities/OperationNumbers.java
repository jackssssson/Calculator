package com.example.calculator.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

import com.example.calculator.R;
import com.example.calculator.fragments.OperationFragment;
import com.squareup.picasso.Picasso;

public class OperationNumbers extends Activity {
    private String sign;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_operation_numbers);

        Intent intent = getIntent();
        sign = intent.getStringExtra("sign");


        OperationFragment operationFragment = new OperationFragment();

        getFragmentManager().beginTransaction()
                .replace(R.id.operationId, operationFragment)
                .commit();

    }

    public String getResult(){
        return sign;
    }
}
