package com.alirezaahmadi.test;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.alirezaahmadi.progressbutton.ProgressButtonComponent;
import com.alirezaahmadi.progressbutton.SomeKotlinClass;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    ProgressButtonComponent progressButtonComponent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        progressButtonComponent = (ProgressButtonComponent) findViewById(R.id.testBtn);

//        progressButtonComponent.setOnClickListener(this);

        new SomeKotlinClass().doNothing();

    }

    @Override
    public void onClick(View view) {
        if(view.getId() == R.id.testBtn){
            progressButtonComponent.setInProgress(true);

            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    progressButtonComponent.setInProgress(false);
                }
            }, 3000);
        }
    }
}
