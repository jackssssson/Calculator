package com.example.calculator.fragments;


import android.annotation.SuppressLint;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.calculator.R;
import com.example.calculator.activities.OperationNumbers;
import com.squareup.picasso.Picasso;

/**
 * A simple {@link Fragment} subclass.
 */
public class OperationFragment extends Fragment {
    private EditText firstText;
    private EditText secondText;
    private TextView resultView;
    private Button button;
    private String sign;

    public OperationFragment() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_operation, container, false);
        sign = ((OperationNumbers) getActivity()).getResult();

        loadImage(view, sign);

        button = view.findViewById(R.id.btnId);
        firstText = view.findViewById(R.id.enterFirstNumber);
        secondText = view.findViewById(R.id.enterSecondNumber);
        resultView = view.findViewById(R.id.textViewResult);

        setButtonText(sign);

        button.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View view) {
                if (firstText.getText().toString().equals("")
                        || secondText.getText().toString().equals("")){
                    return;
                }
                int firstNumber = Integer.parseInt(firstText.getText().toString());
                int secondNumber = Integer.parseInt(secondText.getText().toString());
                int result = 0;

                switch (sign){
                    case "+":
                        result = firstNumber + secondNumber;
                        break;
                    case "-":
                        result = firstNumber - secondNumber;
                        break;
                    case "*":
                        result = firstNumber * secondNumber;
                        break;
                    case "/":
                        result = firstNumber / secondNumber;
                        break;
                }

                resultView.setText(Integer.toString(result));
            }
        });

        return view;
    }

    @SuppressLint("SetTextI18n")
    private void setButtonText(String sign) {
        switch (sign){
            case "+":
                button.setText("ADD");
                break;
            case "-":
                button.setText("SUBTRACT");
                break;
            case "*":
                button.setText("PRODUCT");
                break;
            case "/":
                button.setText("DIVIDE");
                break;
        }
    }

    private void loadImage(View view, String sign) {
        ImageView imageView = view.findViewById(R.id.imageView);
        String image = "";
        switch (sign){
            case "+":
                image = "https://firebasestorage.googleapis.com/v0/b/blog-fcf89.appspot.com/o/plus.png?alt=media&token=f3750956-6f41-4788-a64f-14a0302b591b";
                break;
            case "-":
                image = "https://firebasestorage.googleapis.com/v0/b/blog-fcf89.appspot.com/o/minus.png?alt=media&token=28501d3e-b750-41d5-a660-e58e1f94dc4a";
                break;
            case "*":
                image = "https://firebasestorage.googleapis.com/v0/b/blog-fcf89.appspot.com/o/product.png?alt=media&token=62405061-4ab1-4d74-adc6-df8a85ef0393";
                break;
            case "/":
                image = "https://firebasestorage.googleapis.com/v0/b/blog-fcf89.appspot.com/o/divide.jpg?alt=media&token=ae812e41-65e8-4305-8b01-4c9dc09e149d";
                break;
        }

        Picasso.get().load(image).into(imageView);
    }

}
