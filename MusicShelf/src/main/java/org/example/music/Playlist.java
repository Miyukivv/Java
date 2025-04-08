package org.example.music;

import java.util.ArrayList;

public class Playlist extends ArrayList<Song> {

    public Song atSecond(int totalSeconds) throws IndexOutOfBoundsException{

        if (totalSeconds<0){
            throw new IndexOutOfBoundsException("Podano ujemny czas\n");
        }

        int totalSecondsOfSongs=0;
        for (Song song : this){
            totalSecondsOfSongs+=song.timeInSecond();

            if (totalSecondsOfSongs>=totalSeconds) {
                return song;
            }
        }
        throw new IndexOutOfBoundsException("Podano za duży czas\n");
    }
}