package com.example.qr_codegenerator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidmads.library.qrgenearator.QRGContents;
import androidmads.library.qrgenearator.QRGEncoder;

public class DesignChooseActivity extends AppCompatActivity {

    private Button designButton1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_design_choose);

        designButton1 = findViewById(R.id.designButton1);

        designButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(DesignChooseActivity.this, ShapeColorActivity.class);
                startActivity(i);
            }
        });
    }
}