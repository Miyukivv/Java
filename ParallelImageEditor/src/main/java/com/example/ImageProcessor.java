package com.example;

import com.example.ThreadJob;
import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ImageProcessor {
    private BufferedImage image;

    public void readImage(String path){
        File imgFile = new File(path);

        try {
            image=ImageIO.read(imgFile);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void writeImage(String path){
        File imgFileToSave = new File(path);

        try {
            ImageIO.write(image, "jpg", imgFileToSave);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static int clamp(int value, int min, int max){
        if (value > max)
            return max;
        if (value<min)
            return min;
        return value;
    }

    public void  increaseBrigthness(int level) {

        for (int x = 0; x < image.getWidth(); x++) {
            for (int y = 0; y < image.getHeight(); y++) {
                int rgb = image.getRGB(x, y);


                int b = rgb & 0XFF;
                int g = (rgb & 0XFF00) >> 8;
                int r = (rgb & 0XFF0000) >> 16;


                int newB = clamp(b + level, 0, 255);
                int newG = clamp(g + level, 0, 255);
                int newR = clamp(r + level, 0, 255);

                image.setRGB(x, y, (newR << 16) + (newG << 8) + newB);
            }
        }
    }

    public void increaseBrightnessWithThreads(int level){
        int threadCount = Runtime.getRuntime().availableProcessors();
        int chunk = image.getHeight()/threadCount;

        Thread[] threads=new Thread[threadCount];

        for (int i=0; i<threadCount; i++){

            int begin =i*chunk;
            int end;

            if (i==threadCount-1){
                end=image.getHeight()-(threadCount-1)*chunk;
            } else{
                end=begin+chunk;
            }
            threads[i]=new Thread(new ThreadJob(begin,end,level,image));
        }

        for (int i=0; i<threadCount; i++){
            try {
                threads[i].join();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void setBrightnessThreadPool(int level){
        int threadsCount = Runtime.getRuntime().availableProcessors();

        ExecutorService executor = Executors.newFixedThreadPool(threadsCount);

        for (int i=0; i<image.getHeight(); i++) {
            final int y=i;
            executor.execute(() -> {
                for (int x=0; x<image.getWidth(); x++){
                    int rgb = image.getRGB(x,y);
                    int b = rgb & 0xFF;
                    int g = (rgb & 0xFF00) >> 8;
                    int r = (rgb & 0xFF0000) >> 16;
                    b = clamp(b + level, 0, 255);
                    g = clamp(g + level, 0, 255);
                    r = clamp(r + level, 0, 255);
                    rgb = (r << 16) + (g << 8) + b;
                    image.setRGB(x, y, rgb);
                }
            });
        }
        executor.shutdown();

        try {
            boolean b = executor.awaitTermination(5, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public int [] calculateHistogram(String channel){
        int threadCount=Runtime.getRuntime().availableProcessors();
        ExecutorService executor = Executors.newFixedThreadPool(threadCount);
        int[] histogram=new int[256];

        for (int y=0; y<image.getHeight(); y++){
            final int row = y;
            executor.execute(() -> {
                for (int x = 0; x < image.getWidth(); x++) {
                    int rgb = image.getRGB(x, row);

                    int value;

                    switch (channel.toLowerCase()) {
                        case "red":
                            value = (rgb >> 16) & 0xFF;
                            break;
                        case "green":
                            value = (rgb >> 8) & 0xFF;
                            break;

                        case "blue":
                            value = (rgb & 0xFF);
                            break;
                        default:
                            throw new IllegalArgumentException("Nieprawidłowy kanał: " + channel);
                    }


                    synchronized (histogram) {
                        histogram[value]++;
                    }
                }
            });
        }
        executor.shutdown();
        try {
            executor.awaitTermination(10, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return histogram;
    }
    public BufferedImage generateHistogramImage(int [] histogram){
        int width=256;
        int height=500;

        BufferedImage histogramImage = new BufferedImage(width,height,BufferedImage.TYPE_INT_RGB);
        Graphics2D g2d = histogramImage.createGraphics();

        g2d.setColor(Color.WHITE);
        g2d.fillRect(0,0,width,height);

        int max = 0;
        for (int value : histogram){
            if (value > max) {
                max = value;
            }
        }

        g2d.setColor(Color.BLACK);

        for (int i=0; i<histogram.length; i++){
            int value = histogram[i];

            int barHeight=(int)((double) value / max * height);
            g2d.drawLine(i,height,i,height - barHeight);
        }
        g2d.dispose();
        return histogramImage;
    }

    public void saveHistogramImage(String path, BufferedImage histogramImage){
        File imgFileToSave = new File(path);

        try {
            ImageIO.write(histogramImage,"jpg",imgFileToSave);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
