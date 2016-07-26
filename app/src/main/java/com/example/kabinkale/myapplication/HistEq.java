
package com.example.kabinkale.myapplication;


import android.graphics.Bitmap;
import android.graphics.Color;
import android.util.Log;


public class HistEq {

    private static final String TAG=HistEq.class.getSimpleName();
    public Bitmap Change(Bitmap bi1) {

        //all required parameters declaration
        int[] histogram = new int[256];                                         //to store intensity values from supplied image
        int i = 0;
        int sum = 0;
        ;
        /*
         *
         **RBG TO GRAY CONVERSION
         * And keep track of histogram[] initial values;
         */
        Bitmap bi;
//        operation1 = Bitmap.createBitmap(bi.getWidth(),bi.getHeight(), bi.getConfig());
        bi=bi1.copy(Bitmap.Config.ARGB_8888, true);
        int width = bi.getWidth();
        int height = bi.getHeight();
        int anzpixel = width * height;

        Log.i(TAG,"HEIGHT"+Integer.toString(height));
        Log.i(TAG,"WIDTH"+Integer.toString(width));
        for (i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                int p = bi.getPixel(j, i);
                int r = Color.red(p);
                int g = Color.green(p);
                int b = Color.blue(p);

//                Log.i(TAG,Integer.toString(j)+","+Integer.toString(i));

                r = (int)(0.299 * r + 0.587 * g + 0.114 * b);
                bi.setPixel(j, i,Color.argb(Color.alpha(p), r, r, r));                             //set GRAY values to the image
                if(i!=0&&j!=0){
                    int valueBefore = r;

                    histogram[valueBefore]++;
                }
            }
        }

        MainActivity.setImg(bi);
        /*
         *
         **HISTOGRAM EQUALIZATION SECTION
         *
         */
        //calculate min frequency of occurrence

        int minFrequencyCount = anzpixel;
        for (int k = 0; k < 255; k++) {
            if (histogram[k] < minFrequencyCount && histogram[k] != 0) {
                minFrequencyCount = histogram[k];
                //indexNo = k;
            }
        }

        //System.out.println(minFrequencyCount + " " + indexNo);
        // build a Lookup table LUT containing scale factor
        float[] lut = new float[anzpixel];

        for (i = 0; i < 255; ++i) {

            sum += histogram[i];
//          lut[i] = sum * 255 / anzpixel;
            lut[i] = ((sum - minFrequencyCount) * 255) / (anzpixel - minFrequencyCount);
        }

        // transform image using sum histogram as a Lookup table
        i = 0;
        for (int x = 0; x < bi.getWidth(); x++) {
            for (int z = 0; z < bi.getHeight(); z++) {
                int p = bi.getPixel(x, z);
                int valueBefore = Color.red(p);

//                int valueBefore = bi.getRaster().getPixel(x, y, iarray)[0];
                int valueAfter = (int) lut[valueBefore];

                int iarray = Color.argb(Color.alpha(p),valueAfter,valueAfter,valueAfter);
                bi.setPixel(x, z, iarray);
                i = i + 1;
            }
        }
        return bi;                                                              //return buffered image to main
    }
}
