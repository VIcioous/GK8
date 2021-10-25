package com.company;

import java.awt.image.BufferedImage;

public class BinarizationService {



    public BufferedImage getManualyThresholdedImage(BufferedImage image, int value) {

        BufferedImage newImage = new BufferedImage(
                image.getWidth(),image.getHeight(),BufferedImage.TYPE_INT_BGR);
        int c=0;
        for (int y =0; y< image.getHeight();y++)
        {
            for (int x=0;x< image.getWidth();x++)
            {

                int sum = getGrayScale(image.getRGB(x,y));


                if(sum>=value)
                {
                    int r=255;
                    int g=255;
                    int b=255;
                    c =(r << 16) | (g << 8) |b;
                }
                else
                {
                    int r=0;
                    int g=0;
                    int b=0;
                    c =(r << 16) | (g << 8) |b;
                }
                newImage.setRGB(x,y,c);
            }
        }
        return newImage;
    }

    private int  getGrayScale(int rgb) {
        int r = (rgb >> 16) & 0xff;
        int g = (rgb >> 8) & 0xff;
        int b = (rgb) & 0xff;


        int gray = (int)(0.2126 * r + 0.7152 * g + 0.0722 * b);


        return gray;
    }
}
