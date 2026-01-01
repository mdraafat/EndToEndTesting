package util;

import com.google.gson.Gson;
import util.data.User;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Objects;

public class FileHelper {

    private final FileReader filereader;

    public FileHelper() throws FileNotFoundException {
        filereader = new FileReader(
                Objects.requireNonNull(this.getClass()
                        .getClassLoader()
                        .getResource("user.json")
                ).getFile()
        );
    }

    public User[] getUsers() {
        Gson gson = new Gson();
        return gson.fromJson(filereader, User[].class);
    }
}