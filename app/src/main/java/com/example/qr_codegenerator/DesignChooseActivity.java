package com.example.qr_codegenerator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


public class DesignChooseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_design_choose);
    }
    public void onClickButton(View view) {
        switch (view.getId()) {
            case R.id.designButton1:
                Intent i = new Intent(DesignChooseActivity.this, ShapeColorActivity.class);
                startActivity(i);
                break;
            case R.id.designButton2:

                break;
        }
    }
}