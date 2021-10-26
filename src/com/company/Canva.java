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
    private final JButton thinButton = new JButton();
    private final JButton thickButton = new JButton();
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

        erosionButton.setBounds(140, 730, 100, 25);
        erosionButton.addActionListener(e->makeErosionOnImage());

        dilatationButton.setBounds(250, 730, 100, 25);
        dilatationButton.addActionListener(e->makeDilitationOnImage());

        openingButton.setBounds(360, 730, 100, 25);
        openingButton.addActionListener(e->makeOpeningOnImage());

        closingButton.setBounds(470, 730, 100, 25);
        closingButton.addActionListener(e->makeClosingOnImage());

        thinButton.setBounds(580, 730, 100, 25);
        thinButton.addActionListener(e-> thinImage());
        thickButton.setBounds(690, 730, 100, 25);
        thickButton.addActionListener(e->thickImage());



        erosionButton.setText("Erosion");
        dilatationButton.setText("Dilitaion");
        openingButton.setText("Opening");
        closingButton.setText("Closing");
        thinButton.setText("Thin");
        thickButton.setText("Thick");

        this.setLayout(null);

        this.add(pathFile);
        this.add(readButton);
        this.add(erosionButton);
        this.add(dilatationButton);
        this.add(openingButton);
        this.add(closingButton);
        this.add(thinButton);
        this.add(thickButton);
        this.add(binarizationSlider);

    }



    private void makeClosingOnImage() {
        BufferedImage img = binarizationService.getManuallyThresholdImage(imageJPG,binarizationSlider.getValue());
        img = morphologyTransformService.eroseImage(morphologyTransformService.dilitateImage(img));
        NewWindow window = new NewWindow(img);
    }

    private void makeOpeningOnImage() {
        BufferedImage img = binarizationService.getManuallyThresholdImage(imageJPG,binarizationSlider.getValue());
        img = morphologyTransformService.dilitateImage(morphologyTransformService.eroseImage(img));
        NewWindow window = new NewWindow(img);
    }

    private void makeDilitationOnImage() {
        BufferedImage img = binarizationService.getManuallyThresholdImage(imageJPG,binarizationSlider.getValue());
        img = morphologyTransformService.dilitateImage(img);
        NewWindow window = new NewWindow(img);
    }

    private void makeErosionOnImage() {
        BufferedImage img = binarizationService.getManuallyThresholdImage(imageJPG,binarizationSlider.getValue());
        NewWindow window1 = new NewWindow(img);
        img = morphologyTransformService.eroseImage(img);
        NewWindow window = new NewWindow(img);
    }

    private void thinImage() {
        BufferedImage img = binarizationService.getManuallyThresholdImage(imageJPG,binarizationSlider.getValue());

        NewWindow window = new NewWindow(morphologyTransformService.thinImage(img));
    }

    private void thickImage() {
        BufferedImage img = binarizationService.getManuallyThresholdImage(imageJPG,binarizationSlider.getValue());
        img = binarizationService.reverseImage(img);
        img=morphologyTransformService.thinImage(img);
        NewWindow window = new NewWindow(binarizationService.reverseImage(img));
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
