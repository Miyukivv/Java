package org.example.music;

import org.example.database.DatabaseConnection;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import javax.naming.AuthenticationException;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

class ListenerAccountTest {

    @BeforeAll
    static void openDatabase() throws SQLException {
        DatabaseConnection.connect("src/main/java/org/example/music/songs.db");
        ListenerAccount.Persistence.init();
    }

    @Test
    public void testRegisterUser() throws SQLException, AuthenticationException {
        String username = "User" + System.currentTimeMillis();
        String password = "Pass" + System.currentTimeMillis();
        int id = ListenerAccount.Persistence.register(username, password);
        assertTrue(id != 0);
    }

    @Test
    public void testLoginUser() throws SQLException, AuthenticationException {
        String username = "User" + System.currentTimeMillis();
        String password = "Pass" + System.currentTimeMillis();
        ListenerAccount.Persistence.register(username, password);
        ListenerAccount account = ListenerAccount.Persistence.authenticate(username, password);
        assertEquals(username, account.getUsername());
    }

    @AfterAll
    static void closeDatabase() {
        DatabaseConnection.disconnect("");
    }
}
