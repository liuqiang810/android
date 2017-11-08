package com.liuqiang.customview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import com.liuqiang.customviewlibrary.CustomButton;

public class MainActivity extends AppCompatActivity {
    CustomButton customButton_code;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        customButton_code = (CustomButton) findViewById(R.id.customButton1);
        customButton_code.setShapeType(CustomButton.RECTANGLE)
                .setBgNormalColor(R.color.colorPrimary)
                .setBgPressedColor(R.color.colorPrimaryDark)
                .setBgDisableColor(R.color.white)
                .setCornersRadius(20)
                .setStrokeColor(R.color.black)
                .setStrokeWidth(10)
                .setTextNormalColor(R.color.white)
                .setTextPressedColor(R.color.black)
                .setTextDisableColor(R.color.gray)
                .setRippleColor(R.color.colorAccent)
                .use();
    }
}
