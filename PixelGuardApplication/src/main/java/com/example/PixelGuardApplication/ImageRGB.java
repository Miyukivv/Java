package com.example.PixelGuardApplication;


import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class ImageRGB {

    private BufferedImage image;
    private static ImageRGB instance;


    private ImageRGB() {
        this.image=new BufferedImage(512,512,BufferedImage.TYPE_INT_RGB);
    }

    public BufferedImage getImage() {
        return image;
    }

    public static ImageRGB getInstance(){
        if (instance==null){
            instance=new ImageRGB();
        }
        return instance;
    }

    public void setPixelOfImage(int x, int y, String hexColor){
        Color color = Color.decode(hexColor);
        int rgb = color.getRGB();
        image.setRGB(x, y, rgb);
    }

    public void setImageBasedOnPixels(){
        Database database=Database.getInstance();

        ArrayList<Pixel> pixels = database.getListOfPixelsFromDatabase();

        for (Pixel pixel : pixels){
            setPixelOfImage(pixel.getX(),pixel.getY(), pixel.getColor());
        }
    }


}