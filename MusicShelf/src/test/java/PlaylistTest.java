package org.example.music;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class PlaylistTest {

    @Test
    public void testNewPlaylistIsEmpty() {
        Playlist playlist = new Playlist();
        assertTrue(playlist.isEmpty());
    }

    @Test
    public void testAddToPlaylistListLengthIsOne() {
        Playlist playlist = new Playlist();
        playlist.add(new Song("Alice Smith", "Developer", 100));
        assertTrue(playlist.size() == 1);
    }

    @Test
    public void testAddSongToPlaylistSongIsTheSameAsAdded() {
        Playlist playlist = new Playlist();
        Song alice = new Song("Alice Smith", "Developer", 100);
        playlist.add(alice);
        assertTrue(playlist.get(0) == alice);
    }

    @Test
    public void testAddSongToPlaylistSongHasEverythingTheSameAsAdded() {
        Playlist playlist = new Playlist();
        Song alice = new Song("Alice Smith", "Developer", 100);
        playlist.add(alice);
        assertEquals(playlist.get(0).artist(), alice.artist());
        assertEquals(playlist.get(0).title(), alice.title());
        assertTrue(playlist.get(0).timeInSecond() == alice.timeInSecond());
    }

    @Test
    public void testAtSecond() {
        Playlist playlist = new Playlist();
        Song david = new Song("David Miller", "Designer", 300);
        Song bob = new Song("Bob Johnson", "Engineer", 350);
        Song carol = new Song("Carol Brown", "Vet", 400);
        playlist.add(david);
        playlist.add(bob);
        playlist.add(carol);
        int totalSecond = 350;
        assertTrue(bob == playlist.atSecond(totalSecond));
    }

    @Test
    public void testAtSecondTooBigTime() {
        Playlist playlist = new Playlist();
        Song david = new Song("David Miller", "Designer", 300);
        Song bob = new Song("Bob Johnson", "Engineer", 350);
        Song carol = new Song("Carol Brown", "Vet", 400);
        playlist.add(david);
        playlist.add(bob);
        playlist.add(carol);
        int totalSecond = 2000;
        Exception exception = assertThrows(IndexOutOfBoundsException.class, () -> {
            playlist.atSecond(totalSecond);
        });
        assertEquals("Podano za duży czas\n", exception.getMessage());
    }

    @Test
    public void testAtSecondNegativeTime() {
        Playlist playlist = new Playlist();
        Song david = new Song("David Miller", "Designer", 300);
        Song bob = new Song("Bob Johnson", "Engineer", 350);
        Song carol = new Song("Carol Brown", "Vet", 400);
        playlist.add(david);
        playlist.add(bob);
        playlist.add(carol);
        int totalSecond = -5;
        Exception exception = assertThrows(IndexOutOfBoundsException.class, () -> {
            playlist.atSecond(totalSecond);
        });
        assertEquals("Podano ujemny czas\n", exception.getMessage());
    }
}