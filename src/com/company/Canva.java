package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class Canva extends JPanel {

    private final JTextField pathFile = new JTextField();
    private final JButton readButton = new JButton();

    private final JButton erosionButton = new JButton();
    private final JButton dilatationButton = new JButton();
    private final JButton openingButton = new JButton();
    private final JButton closingButton = new JButton();
    private final JButton hitOrMissButton = new JButton();
    private final JSlider binarizationSlider = new JSlider(0,255);
    private BufferedImage imageJPG;
    private final JPGService jpgService;
    private final MorphologyTransformService morphologyTransformService ;
    private final BinarizationService binarizationService;


    Canva()
    {
        setButtons();
        jpgService = new JPGService();
        morphologyTransformService = new MorphologyTransformService();
        binarizationService= new BinarizationService();
    }

    private void setButtons() {
        readButton.addActionListener(e -> readFile(pathFile.getText()));
        readButton.setText("Read");
        readButton.setBounds(30, 730, 100, 25);
        pathFile.setBounds(30, 700, 100, 25);

        binarizationSlider.setBounds(400,700,250,25);

        erosionButton.setBounds(150, 730, 100, 25);
        erosionButton.addActionListener(e->makeErosionOnImage());

        dilatationButton.setBounds(280, 730, 100, 25);
        dilatationButton.addActionListener(e->makeDilitationOnImage());

        openingButton.setBounds(410, 730, 100, 25);
        openingButton.addActionListener(e->makeOpeningOnImage());

        closingButton.setBounds(530, 730, 100, 25);
        closingButton.addActionListener(e->makeClosingOnImage());

        hitOrMissButton.setBounds(660, 730, 100, 25);



        erosionButton.setText("Erosion");
        dilatationButton.setText("Dilitaion");
        openingButton.setText("Opening");
        closingButton.setText("Closing");
        hitOrMissButton.setText("Hit-Or-Miss");

        this.setLayout(null);

        this.add(pathFile);
        this.add(readButton);
        this.add(erosionButton);
        this.add(dilatationButton);
        this.add(openingButton);
        this.add(closingButton);
        this.add(hitOrMissButton);
        this.add(binarizationSlider);

    }

    private void makeClosingOnImage() {
        BufferedImage img = binarizationService.getManualyThresholdedImage(imageJPG,binarizationSlider.getValue());
        img = morphologyTransformService.eroseImage(morphologyTransformService.dilitateImage(img));
        NewWindow window = new NewWindow(img);
    }

    private void makeOpeningOnImage() {
        BufferedImage img = binarizationService.getManualyThresholdedImage(imageJPG,binarizationSlider.getValue());
        img = morphologyTransformService.dilitateImage(morphologyTransformService.eroseImage(img));
        NewWindow window = new NewWindow(img);
    }

    private void makeDilitationOnImage() {
        BufferedImage img = binarizationService.getManualyThresholdedImage(imageJPG,binarizationSlider.getValue());
        img = morphologyTransformService.dilitateImage(img);
        NewWindow window = new NewWindow(img);
    }

    private void makeErosionOnImage() {
        BufferedImage img = binarizationService.getManualyThresholdedImage(imageJPG,binarizationSlider.getValue());
        NewWindow window1 = new NewWindow(img);
        img = morphologyTransformService.eroseImage(img);
        NewWindow window = new NewWindow(img);
    }

    private void readFile(String text) {
        imageJPG = jpgService.readJPG(text);
    }
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(imageJPG, 0, 0, this);
        repaint();
    }
}
