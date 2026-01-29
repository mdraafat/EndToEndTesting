package util.helper;

import com.google.gson.Gson;
import util.data.User;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

public class FileHandler {

    public User[] getRegisterUser() throws Exception {
        return readUsersFromFile("register_user.json");
    }

    public User[] getLoginUser() throws Exception {
        return readUsersFromFile("login_user.json");
    }

    public User[] getMainUser() throws Exception {
        return readUsersFromFile("main_user.json");
    }

    private User[] readUsersFromFile(String fileName) throws Exception {
        InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream(fileName);
        if (inputStream == null) {
            throw new RuntimeException("File not found: " + fileName);
        }

        try (InputStreamReader reader = new InputStreamReader(inputStream, StandardCharsets.UTF_8)) {
            Gson gson = new Gson();
            return gson.fromJson(reader, User[].class);
        }
    }
}