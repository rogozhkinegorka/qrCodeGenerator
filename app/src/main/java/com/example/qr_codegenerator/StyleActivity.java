package com.example.qr_codegenerator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

public class StyleActivity extends AppCompatActivity {

    Bitmap tempBitmap, bitmap;
    ImageView qrcode;
    String text = DataTransfer.getText();
    QRGEncoder2 qrgEncoder = new QRGEncoder2(text,"TEXT_TYPE", 4);
    String[] styles = { "Без стилей", "Хоррор"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_style);

        qrcode = findViewById(R.id.qrcode);
        Spinner spinner = (Spinner) findViewById(R.id.spinner);
        tempBitmap = qrgEncoder.getBitmap();
        qrgEncoder = new QRGEncoder2(text,"TEXT_TYPE", tempBitmap.getWidth() * 16);
        bitmap = qrgEncoder.getBitmap();
        qrcode.setImageBitmap(bitmap);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.spinner_style, styles);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        AdapterView.OnItemSelectedListener itemSelectedListener = new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                bitmap = qrgEncoder.getBitmap();
                if(position == 1){
                    Canvas canvas = new Canvas(bitmap);
                    Paint p = new Paint();
                    for(int i=1; i<tempBitmap.getWidth()-1; ++i) {
                        for(int j=1; j<tempBitmap.getWidth()-1; ++j){
                            if(tempBitmap.getPixel(i,j) != -1){
                                p.setColor(Color.BLACK);
                                if(tempBitmap.getPixel(i,j-1) == -1 && tempBitmap.getPixel(i+1,j-1) == -1 && tempBitmap.getPixel(i-1,j-1) == -1 && tempBitmap.getPixel(i-1,j) == -1 && tempBitmap.getPixel(i+1,j) == -1  && tempBitmap.getPixel(i,j+1) != -1){
                                    canvas.drawCircle(i*16+4, j*16, 6, p);
                                    canvas.drawCircle(i*16 + 12, j*16, 6, p);
                                }
                                if(tempBitmap.getPixel(i,j+1) == -1 && tempBitmap.getPixel(i+1,j+1) == -1 && tempBitmap.getPixel(i-1,j+1) == -1 && tempBitmap.getPixel(i-1,j) == -1 && tempBitmap.getPixel(i+1,j) == -1  && tempBitmap.getPixel(i,j-1) != -1){
                                    canvas.drawCircle(i*16+4, j*16+16, 6, p);
                                    canvas.drawCircle(i*16 + 12, j*16+16, 6, p);
                                }
                                if(tempBitmap.getPixel(i+1,j) == -1 && tempBitmap.getPixel(i+1,j+1) == -1 && tempBitmap.getPixel(i+1,j-1) == -1 && tempBitmap.getPixel(i,j+1) == -1 && tempBitmap.getPixel(i,j-1) == -1  && tempBitmap.getPixel(i-1,j) != -1) {
                                    canvas.drawCircle(i * 16 + 16, j * 16 + 12, 6, p);
                                    canvas.drawCircle(i * 16 + 16, j * 16 + 4, 6, p);
                                }
                                if(tempBitmap.getPixel(i-1,j) == -1 && tempBitmap.getPixel(i-1,j+1) == -1 && tempBitmap.getPixel(i-1,j-1) == -1 && tempBitmap.getPixel(i,j-1) == -1 && tempBitmap.getPixel(i,j+1) == -1  && tempBitmap.getPixel(i+1,j) != -1) {
                                    canvas.drawCircle(i * 16, j * 16 + 12, 6, p);
                                    canvas.drawCircle(i * 16, j * 16 + 4, 6, p);
                                }
                                if(tempBitmap.getPixel(i+1,j) == -1 && tempBitmap.getPixel(i-1,j) == -1 && tempBitmap.getPixel(i,j-1) == -1 && tempBitmap.getPixel(i,j+1) == -1) {
                                    for(int i2=16*i; i2<16*i+16; ++i2){
                                        for(int j2=16*j; j2<16*j+16; ++j2)
                                            bitmap.setPixel(i2, j2, Color.RED);
                                    }
                                }
                            }
                        }
                    }
                    Bitmap skull = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.skull), 40, 40, true);
                    canvas.drawBitmap(skull, 52, 52, null);
                    canvas.drawBitmap(skull, bitmap.getWidth()-92, 52, null);
                    canvas.drawBitmap(skull, 52, bitmap.getWidth()-92, null);
                }
                qrcode.setImageBitmap(bitmap);
                qrcode.invalidate();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        };
        spinner.setOnItemSelectedListener(itemSelectedListener);

    }

    public void onClickButton(View view) {
        DataTransfer.setQRcode(bitmap);
        Intent i = new Intent(StyleActivity.this, SaveActivity.class);
        startActivity(i);
    }
}