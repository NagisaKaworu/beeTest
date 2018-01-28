package com.example.kaworu.beetest;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    EditText oneOne;
    EditText oneTwo;
    TextView oneSum;

    EditText twoEt;
    TextView twoSum;

    MainViewModel vm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        vm = ViewModelProviders.of(this).get(MainViewModel.class);
        oneOne = (EditText) findViewById(R.id.one_et_one);
        oneTwo = (EditText) findViewById(R.id.one_et_two);
        oneSum = (TextView) findViewById(R.id.one_txt);
        twoEt = (EditText) findViewById(R.id.two_et);
        twoSum = (TextView) findViewById(R.id.two_txt);

        /** Set output */
        vm.OneOutput().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                oneSum.setText(s);
            }
        });
        vm.TwoOutput().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                twoSum.setText(s);
            }
        });

        /** Observe input */
        oneOne.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {}

            @Override
            public void afterTextChanged(Editable s) {
                vm.OneOneInput().setValue(s.toString());
            }
        });
        oneTwo.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {}

            @Override
            public void afterTextChanged(Editable s) {
                vm.OneTwoInput().setValue(s.toString());
            }
        });
        twoEt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {}

            @Override
            public void afterTextChanged(Editable s) {
                vm.TwoInput().setValue(s.toString());
            }
        });

        /** Trigger calculations */
        vm.OneOneInput().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                vm.calcOne();
            }
        });
        vm.OneTwoInput().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                vm.calcOne();
            }
        });
        vm.TwoInput().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                vm.calcTwo();
            }
        });
    }
}
