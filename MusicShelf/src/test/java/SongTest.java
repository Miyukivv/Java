package org.example.music;

import org.example.database.DatabaseConnection;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.MethodSource;

import java.sql.SQLException;
import java.util.*;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

class SongTest {

    @BeforeAll
    static void openDatabase(){

        DatabaseConnection.connect("src/main/java/org/example/music/songs.db","songs");
    }
    @Test
    public void testRead_readFromDatabaseWhenIndexExists() throws SQLException {
        int id = 9;
        Song.Persistence persistence = new Song.Persistence();
        Optional<Song> mySong  =persistence.read(9);

        Song elvis=new Song("Elvis Presley","Suspicious Minds", 262);
        assertTrue(mySong.isPresent()); //sprawdzanie czy jest pusty
        assertEquals(elvis.artist(),mySong.get().artist(), "Artysta");
        assertEquals(elvis.title(),mySong.get().title(), "Tytuł");
        assertTrue(elvis.timeInSecond()==mySong.get().timeInSecond(), "Czas");
    }

    @Test
    public void testRead_readFromDatabaseWhenIndexNotExists() throws SQLException {
        int id=100;

        Song.Persistence persistence = new Song.Persistence();
        Optional<Song> mySong  =persistence.read(id);
        assertTrue(mySong.isEmpty(), "Wyciągnięto obiekt z nieistniejącym indexem");
    }

    @ParameterizedTest
    @MethodSource("getSongStream")
    public void test_TestsIndexExists_returnSong(Map.Entry<Integer, String> entry) throws SQLException {
        System.out.println(entry.getKey());
        Song.Persistence persistence = new Song.Persistence();
        Optional<Song> mySong  =persistence.read(entry.getKey());

        assertTrue(mySong.isPresent());
        assertEquals(entry.getValue(), mySong.get().title());
    }


    @ParameterizedTest
    @CsvFileSource(files = "src/main/resources/songs.csv", numLinesToSkip = 1)
    public void test_TestsIndexExists_returnSongCsv(int id, String artist, String title, int timeInSecond) throws SQLException {
        Song.Persistence persistence = new Song.Persistence();
        Optional<Song> mySong = persistence.read(id);
        assertTrue(mySong.isPresent());
        assertEquals(artist,mySong.get().artist());
        assertEquals(title,mySong.get().title());
        assertTrue(timeInSecond==mySong.get().timeInSecond());
    }

    @AfterAll
    static void CloseDatabase(){
        DatabaseConnection.disconnect("songs");
    }

    private static Stream<Map.Entry<Integer, String>> getSongStream() {
        Map<Integer, String> songs = new HashMap<>();

        songs.put(22, "Space Oddity");
        songs.put(35, "Ruby Tuesday");
        songs.put(4, "Like a Rolling Stone");
        return songs.entrySet().stream();
    }

}