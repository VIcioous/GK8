package com.company;

import java.awt.*;
import java.awt.image.BufferedImage;

public class BinarizationService {


    public BufferedImage getManuallyThresholdImage(BufferedImage image, int value) {

        BufferedImage newImage = new BufferedImage(
                image.getWidth(), image.getHeight(), BufferedImage.TYPE_INT_BGR);
        int c = 0;
        for (int y = 0; y < image.getHeight(); y++) {
            for (int x = 0; x < image.getWidth(); x++) {

                int sum = getGrayScale(image.getRGB(x, y));


                int r;
                int g;
                int b;
                if (sum >= value) {
                    r = 255;
                    g = 255;
                    b = 255;
                } else {
                    r = 0;
                    g = 0;
                    b = 0;
                }
                c = (r << 16) | (g << 8) | b;
                newImage.setRGB(x, y, c);
            }
        }
        return newImage;
    }

    public BufferedImage reverseImage(BufferedImage image) {
        BufferedImage newImage = new BufferedImage(
                image.getWidth(), image.getHeight(), BufferedImage.TYPE_INT_BGR);
        int c = 0;
        for (int y = 0; y < image.getHeight(); y++) {
            for (int x = 0; x < image.getWidth(); x++) {

                Color color = new Color(image.getRGB(x, y));


                int r;
                int g;
                int b;
                if (color.equals(Color.BLACK)) {
                    r = 255;
                    g = 255;
                    b = 255;
                } else {
                    r = 0;
                    g = 0;
                    b = 0;
                }
                c = (r << 16) | (g << 8) | b;
                newImage.setRGB(x, y, c);
            }
        }
        return newImage;

    }

    private int getGrayScale(int rgb) {
        int r = (rgb >> 16) & 0xff;
        int g = (rgb >> 8) & 0xff;
        int b = (rgb) & 0xff;


        int gray = (int) (0.2126 * r + 0.7152 * g + 0.0722 * b);


        return gray;
    }
}
