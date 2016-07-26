package com.example.kabinkale.myapplication;

import android.graphics.Bitmap;
import android.graphics.Color;

/**
 * Created by Kabin Kale on 7/26/2016.
 */
public class SkinTest {


    public static  boolean hasEnoughSkin(Bitmap tempBmp){
        int i;
        int j;
        int nSkinPixels = 0;
//        Tag.("ANATOR",tempBmp.getHeight());
//        Log.i("TAG",Integer.toString(tempBmp.getHeight()));
//        Log.i("TAG",Integer.toString(tempBmp.getWidth()));

        for( i = 0; i < tempBmp.getWidth() ; i++)
            for( j = 0; j < tempBmp.getHeight(); j++){
                int p= tempBmp.getPixel(i,j);
                int G= Color.green(p);
                int R= Color.red(p);
                int B= Color.blue(p);
                int diff = Math.max(R, Math.max(G, B))- Math.min(R, Math.min(G, B));
                if(R > 95 && G > 40 && B > 20 && R > G && R > B && R-G > 15 && diff > 15)
                    nSkinPixels++;
            }

        return nSkinPixels/(float)(tempBmp.getHeight()*tempBmp.getWidth()) > 0.4;
    }
}
