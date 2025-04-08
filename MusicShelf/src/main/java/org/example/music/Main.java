package org.example;

import org.example.auth.Account;
import org.example.database.DatabaseConnection;
import org.example.music.ListenerAccount;
import org.example.music.Playlist;
import org.example.music.Song;

import javax.naming.AuthenticationException;
import java.sql.SQLException;
import java.util.Optional;

public class Main {
    public static void main(String[] args) {
        try {
            DatabaseConnection.connect("src/main/java/org/example/music/songs.db");
            DatabaseConnection.connect("src/main/java/org/example/music/songs.db", "songs");

            Song.Persistence.init();
            ListenerAccount.Persistence.init();
            Account.Persistence.init();

            String username = "User" + System.currentTimeMillis();
            String password = "Pass" + System.currentTimeMillis();

            System.out.println("\nRejestracja nowego użytkownika: \n" + username + "");
            int userId = ListenerAccount.Persistence.register(username, password);
            System.out.println("<Zarejestrowano użytkownika o ID: " + userId + ">");

            System.out.println("\n<Logowanie>");
            ListenerAccount user = ListenerAccount.Persistence.authenticate(username, password);
            System.out.println("Zalogowano jako: " + user.getUsername());

            System.out.println("\n<Pobranie piosenki o ID=9>");
            Optional<Song> song = Song.Persistence.read(9);
            song.ifPresentOrElse(
                    s -> System.out.println("Pobrano z bazy: " + s.artist() + " - " + s.title()),
                    () -> System.out.println("Brak piosenki o ID=9 w bazie")
            );

            Playlist playlist = new Playlist();
            song.ifPresent(playlist::add);
            playlist.add(new Song("Coldplay", "Viva la Vida", 242));
            playlist.add(new Song("Imagine Dragons", "Believer", 204));

            System.out.println("\nPlaylista zawiera " + playlist.size() + " utwory:");
            for (Song track : playlist) {
                System.out.println("<" + track.artist() + " - " + track.title() + " (" + track.timeInSecond() + "s)>");
            }

            int playlistLength = playlist.stream().mapToInt(Song::timeInSecond).sum();
            System.out.println("Całkowity czas playlisty: " + playlistLength + " sekund");

            int checkSecond = 400;
            System.out.println("\n<Sprawdzanie jaki utwór odtwarza się po " + checkSecond + " sekundach>");
            try {
                Song current = playlist.atSecond(checkSecond);
                System.out.println("Odtwarza się: " + current.artist() + " - " + current.title());
            } catch (IndexOutOfBoundsException e) {
                System.out.println("Błąd: " + e.getMessage().trim());
            }

        } catch (AuthenticationException e) {
            System.out.println("<Błąd logowania: " + e.getMessage() + ">");
        } catch (SQLException e) {
            System.out.println("<Błąd SQL: " + e.getMessage() + ">");
        } catch (Exception e) {
            System.out.println("<Inny błąd: " + e.getMessage() + ">");
        } finally {
            DatabaseConnection.disconnect("");
            DatabaseConnection.disconnect("songs");
        }
    }
}
