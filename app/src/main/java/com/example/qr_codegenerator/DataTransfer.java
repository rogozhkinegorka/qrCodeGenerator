package com.example.qr_codegenerator;

import android.graphics.Bitmap;
import android.graphics.Color;

public class DataTransfer {
    private static String text;
    private static Bitmap qrcode;
    private static int blackColor = Color.BLACK;
    private static int whiteColor = Color.WHITE;
    public static void setText(String text) {
        DataTransfer.text = text;
    }
    public static String getText() {
        return text;
    }
    public static void setQRcode(Bitmap qrcode) {
        DataTransfer.qrcode = qrcode;
    }
    public static Bitmap getQRcode() {
        return qrcode;
    }
    public static void setBlackColor(int blackColor) {
        DataTransfer.blackColor = blackColor;
    }
    public static int getBlackColor() {
        return blackColor;
    }
    public static void setWhiteColor(int whiteColor) {
        DataTransfer.whiteColor = whiteColor;
    }
    public static int getWhiteColor() {
        return whiteColor;
    }
}
