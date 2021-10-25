package com.company;

import java.awt.*;
import java.awt.image.BufferedImage;

public class MorphologyTransformService {
    private final Color[] segment = new Color[]{
            Color.WHITE, Color.BLACK, Color.WHITE,
            Color.BLACK, Color.BLACK, Color.BLACK,
            Color.WHITE, Color.BLACK, Color.WHITE,
    };

    public BufferedImage eroseImage(BufferedImage imageJPG) {

        BufferedImage newImage = new BufferedImage(imageJPG.getWidth(), imageJPG.getHeight(), BufferedImage.TYPE_INT_BGR);
        for (int y = 1; y < imageJPG.getHeight() - 1; y++) {
            for (int x = 1; x < imageJPG.getWidth() - 1; x++) {

                Color left = new Color(imageJPG.getRGB(x - 1, y));
                Color top = new Color(imageJPG.getRGB(x, y - 1));
                Color mid = new Color(imageJPG.getRGB(x, y));
                Color bot = new Color(imageJPG.getRGB(x, y + 1));
                Color right = new Color(imageJPG.getRGB(x + 1, y));

                if (left.equals(segment[3]) &&
                        top.equals(segment[1]) &&
                        mid.equals(segment[4]) &&
                        right.equals(segment[5]) &&
                        bot.equals(segment[7])

                ) {
                    int r = 0;
                    int g = 0;
                    int b = 0;

                    int color = (r << 16) | (g << 8) | b;
                    newImage.setRGB(x, y, color);
                } else {
                    int r = 255;
                    int g = 255;
                    int b = 255;

                    int color = (r << 16) | (g << 8) | b;
                    newImage.setRGB(x, y, color);

                }


            }
        }
        return newImage;
    }

    public BufferedImage dilitateImage(BufferedImage imageJPG) {
        BufferedImage newImage = new BufferedImage(imageJPG.getWidth(), imageJPG.getHeight(), BufferedImage.TYPE_INT_BGR);
        for (int y = 1; y < imageJPG.getHeight() - 1; y++) {
            for (int x = 1; x < imageJPG.getWidth() - 1; x++) {

                Color left = new Color(imageJPG.getRGB(x - 1, y));
                Color top = new Color(imageJPG.getRGB(x, y - 1));
                Color mid = new Color(imageJPG.getRGB(x, y));
                Color bot = new Color(imageJPG.getRGB(x, y + 1));
                Color right = new Color(imageJPG.getRGB(x + 1, y));

                if (left.equals(segment[3]) ||
                        top.equals(segment[1]) ||
                        mid.equals(segment[4]) ||
                        right.equals(segment[5]) ||
                        bot.equals(segment[7])

                ) {
                    int r = 0;
                    int g = 0;
                    int b = 0;

                    int color = (r << 16) | (g << 8) | b;
                    newImage.setRGB(x, y, color);
                } else {
                    int r = 255;
                    int g = 255;
                    int b = 255;

                    int color = (r << 16) | (g << 8) | b;
                    newImage.setRGB(x, y, color);

                }


            }
        }
        return newImage;


    }
}
