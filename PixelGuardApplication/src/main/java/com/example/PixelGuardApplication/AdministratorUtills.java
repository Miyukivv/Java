package com.example.PixelGuardApplication;
public class AdministratorUtills {

    public static int ban(int token){

        Token.removeToken(token);

        Database database=Database.getInstance();
        int numberOfDeletedRecords=database.removeRecords(token);

        ImageRGB image=ImageRGB.getInstance();
        image.setImageBasedOnPixels();

        return numberOfDeletedRecords;
    }
}