package com.example.qr_codegenerator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.jaredrummler.android.colorpicker.ColorPickerDialog;
import com.jaredrummler.android.colorpicker.ColorPickerDialogListener;
import com.jaredrummler.android.colorpicker.ColorShape;

import androidmads.library.qrgenearator.QRGContents;
import androidmads.library.qrgenearator.QRGEncoder;

public class ShapeColorActivity extends AppCompatActivity implements ColorPickerDialogListener {

    Bitmap bitmap;
    String text = DataTransfer.getText();
    QRGEncoder qrgEncoder = new QRGEncoder(text, null, QRGContents.Type.TEXT, 500);
    private ImageView qrcode;
    Button colorButton1, colorButton2, nextButton;
    private static final int firstId = 1,secondId = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shape_color);

        qrcode = findViewById(R.id.qrcode);
        colorButton1 = findViewById(R.id.colorButton1);
        colorButton2 = findViewById(R.id.colorButton2);
        nextButton = findViewById(R.id.nextButton);

        try {
            bitmap = qrgEncoder.getBitmap();
            qrcode.setImageBitmap(bitmap);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void createColorPickerDialog(int id) {
        switch (id) {
            case 1:
                ColorPickerDialog.newBuilder()
                        .setColor(DataTransfer.getWhiteColor())
                        .setDialogType(ColorPickerDialog.TYPE_PRESETS)
                        .setAllowCustom(true)
                        .setAllowPresets(true)
                        .setColorShape(ColorShape.SQUARE)
                        .setDialogId(id)
                        .show(this);
                break;
            case 2:
                ColorPickerDialog.newBuilder()
                        .setColor(DataTransfer.getBlackColor())
                        .setDialogType(ColorPickerDialog.TYPE_PRESETS)
                        .setAllowCustom(true)
                        .setAllowPresets(true)
                        .setColorShape(ColorShape.SQUARE)
                        .setDialogId(id)
                        .show(this);
                break;
        }

    }

    public void onClickButton(View view) {
        switch (view.getId()) {
            case R.id.colorButton1:
                createColorPickerDialog(firstId);
                break;
            case R.id.colorButton2:
                createColorPickerDialog(secondId);
                break;
            case R.id.nextButton:
                DataTransfer.setQRcode(bitmap);
                Intent i = new Intent(ShapeColorActivity.this, SaveActivity.class);
                startActivity(i);
                break;
        }
    }

    @Override
    public void onColorSelected(int dialogId, int color) {
        switch (dialogId) { // смотрим, какая кнопка нажата
            case firstId:
                DataTransfer.setWhiteColor(color);
                qrgEncoder.setColorWhite(color);
                break;
            case secondId:
                DataTransfer.setBlackColor(color);
                qrgEncoder.setColorBlack(color);
                break;
        }
        try {
            bitmap = qrgEncoder.getBitmap();
            qrcode.setImageBitmap(bitmap);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onDialogDismissed(int dialogId) {

    }
}