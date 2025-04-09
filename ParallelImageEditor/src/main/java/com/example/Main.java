package com.example;

import java.awt.image.BufferedImage;

public class Main {

    public static void main(String[] args) {
        ImageProcessor imp = new ImageProcessor();

        System.out.println("<ParallelImageEditor>");

        System.out.println("[1] Wczytywanie obrazu...");
        imp.readImage("src/main/resources/gorski.jpg");

        System.out.println("[2] Rozjaśnianie obrazu (jednowątkowo)...");
        long start = System.currentTimeMillis();
        imp.increaseBrigthness(50);
        long duration = System.currentTimeMillis() - start;
        System.out.println("-> Czas rozjaśniania (jednowątkowo): " + duration + " ms");

        System.out.println("[3] Zapis rozjaśnionego obrazu...");
        imp.writeImage("src/main/resources/gorski_bright.jpg");

        System.out.println("[4] Generowanie histogramów...");

        generateAndSaveHistogram(imp, "blue", "src/main/resources/histogram_blue.jpg");
        generateAndSaveHistogram(imp, "green", "src/main/resources/histogram_green.jpg");
        generateAndSaveHistogram(imp, "red", "src/main/resources/histogram_red.jpg");

        System.out.println("Zakończono przetwarzanie.");
    }

    private static void generateAndSaveHistogram(ImageProcessor imp, String channel, String outputPath) {
        System.out.println("-> Kanał: " + channel);

        int[] histogram = imp.calculateHistogram(channel);
        BufferedImage histImage = imp.generateHistogramImage(histogram);
        imp.saveHistogramImage(outputPath, histImage);

        System.out.println(" Histogram zapisany jako: " + outputPath);
    }
}
