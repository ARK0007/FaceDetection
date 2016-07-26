package com.example.kabinkale.myapplication;

        import android.graphics.Bitmap;
        import android.graphics.Color;
        import android.util.Log;



public class HaarFilterAll{


    public void main(Bitmap primaryImg)  {

        int[] twoHoriFea = new int[43200]; //36,432 features, excluiding  top row and left column pixels.
        int[] twoVertFea = new int[43200];
        int[] fourRectFea = new int[43200];
        int[][] values = new int[24][24];
        int[][] intValues = new int[24][24];


        Features features = new Features();
        IntegralImage integralImage = new IntegralImage();

//        imageView.setImageBitmap(img);

        HistEq histEq = new HistEq();                                           //call histogram equilization
        Bitmap img = histEq.Change(primaryImg);                                               //retreive equilized image


        int count = 0;                                                          //for naming convention as well as keeping track of the number of sub images.

        for (int i = 0; i <= (img.getHeight() - 24); i++) {                     //For a 24x24 Pixel image define height such that the last image to be extracted has atleast 24 Pixels to work with

            for (int j = 0; j <= (img.getWidth() - 24); j++) {                  //*For a 24x24 Pixel image define width such that the last image to be extracted has atleast 24 Pixels to work with
                Bitmap skinCheck = Bitmap.createBitmap(primaryImg, j, i, 24, 24);
                count++;
                if(SkinTest.hasEnoughSkin(skinCheck)==false)
                    continue;


                Bitmap sub = Bitmap.createBitmap(img, j, i, 24, 24);            //sub image starting point defined by i and j & 24x4 is the needed size for the sub image.



                for (int k = 0; k < 24; k++) {
                    for (int l = 0; l < 24; l++) {
                        int p = sub.getPixel(k, l);
                        int r = Color.red(p);
                        values[k][l] = r;
                    }
                }


                intValues = integralImage.Integral(values);


                ParallelRun R1 = new ParallelRun("Filter1", 1, intValues);
                R1.start();
                ParallelRun R2 = new ParallelRun("Filter2", 2, intValues);
                R2.start();
//                ParallelRun R3 = new ParallelRun("Filter1", 3, intValues);
//                R3.start();
//                ParallelRun R4 = new ParallelRun("Filter2", 4, intValues);
//                R4.start();

//                twoHoriFea = features.FeatureA(2, 1, intValues);
//                twoVertFea = features.FeatureB(1, 2, intValues);
//                fourRectFea = features.FeatureE(2, 2, intValues);

            }

            Log.i("TAG",Integer.toString(count));
            System.out.println("IMAGE NUMBER = " + count);
        }

    }


}
