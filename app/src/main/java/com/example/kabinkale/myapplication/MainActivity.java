package com.example.kabinkale.myapplication;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {
    TextView TxtDisp;
    static ImageView ImgDisp;
    Button Start;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TxtDisp = (TextView) findViewById(R.id.resultText);
        ImgDisp = (ImageView) findViewById(R.id.imageView);
        Start = (Button) findViewById(R.id.startButton);

    }


    public void StartProcess(View view)  {
        HaarFilterAll start = new HaarFilterAll();

        Bitmap img;
        img = BitmapFactory.decodeResource(getResources(), R.drawable.david);
//
//        Bitmap original = null;
//        try {
//            original = BitmapFactory.decodeStream(getAssets().open("david.jpg"));
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        ByteArrayOutputStream out = new ByteArrayOutputStream();
//        original.compress(Bitmap.CompressFormat.PNG, 100, out);
//        Bitmap decoded = BitmapFactory.decodeStream(new ByteArrayInputStream(out.toByteArray()));
//
//
//        ByteArrayOutputStream out = new ByteArrayOutputStream();
//        img.compress(Bitmap.CompressFormat.JPEG, 100, out);
//        Bitmap decoded = BitmapFactory.decodeStream(new ByteArrayInputStream(out.toByteArray()));

        start.main(img);

    }

    public static void setImg(Bitmap bi){
        ImgDisp.setImageBitmap(bi);
    }

}
