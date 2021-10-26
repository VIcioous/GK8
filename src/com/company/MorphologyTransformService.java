package com.company;

import java.awt.*;
import java.awt.image.BufferedImage;

public class MorphologyTransformService {
    private final Color[] segment = new Color[]{
            Color.WHITE, Color.BLACK, Color.WHITE,
            Color.BLACK, Color.BLACK, Color.BLACK,
            Color.WHITE, Color.BLACK, Color.WHITE
    };

    private final Color[][] HOM;

    {
        Color color1 = Color.BLACK;
        Color color2 = Color.WHITE;
        HOM = new Color[][]{
                        {color1, color1, color1,
                        null, color2, null,
                        color2, color2, color2},

                         {null, color1, color1,
                        color2, color2, color1,
                        color2, color2, null},

                        {color2, null, color1,
                        color2, color2, color1,
                        color2, null, color1},

                        {color2, color2, null,
                        color2, color2, color1,
                        null, color1, color1},

                        {color2, color2, color2,
                        null, color2, null,
                        color1, color1, color1},

                        {null, color2, color2,
                        color1, color2, color2,
                        color1, color1, null},

                        {color1, null, color2,
                        color1, color2, color2,
                        color1, null, color2},

                        {color1, color1, null,
                        color1, color2, color2,
                        null, color2, color2}
        };
    }

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

    public BufferedImage thinImage(BufferedImage imageJPG) {

        for (int k = 0; k < 16; k++) {
            for (int y = 1; y < imageJPG.getHeight() - 1; y++) {
                for (int x = 1; x < imageJPG.getWidth() - 1; x++) {
                    Color[] tab = new Color[9];
                    tab[0] = new Color(imageJPG.getRGB(x - 1, y - 1));
                    tab[1] = new Color(imageJPG.getRGB(x, y - 1));
                    tab[2] = new Color(imageJPG.getRGB(x + 1, y - 1));
                    tab[3] = new Color(imageJPG.getRGB(x - 1, y));
                    tab[4] = new Color(imageJPG.getRGB(x, y));
                    tab[5] = new Color(imageJPG.getRGB(x + 1, y));
                    tab[6] = new Color(imageJPG.getRGB(x - 1, y + 1));
                    tab[7] = new Color(imageJPG.getRGB(x, y + 1));
                    tab[8] = new Color(imageJPG.getRGB(x + 1, y + 1));
                    boolean status = true;
                    for (int i = 0; i < 9; i++) {
                        if(HOM[k%8][i]==null)
                            continue;
                        if(!HOM[k%8][i].equals(tab[i]))
                        {
                            status=false;
                            break;
                        }
                    }

                    if (status
                    ) {
                        int r = 0;
                        int g = 0;
                        int b = 0;

                        int color = (r << 16) | (g << 8) | b;
                        imageJPG.setRGB(x, y, color);
                    } else {
                        int r = tab[4].getRed();
                        int g = tab[4].getGreen();
                        int b = tab[4].getBlue();

                        int color = (r << 16) | (g << 8) | b;
                       imageJPG.setRGB(x, y,color );

                    }


                }
            }
        }
        return imageJPG;


    }

}
