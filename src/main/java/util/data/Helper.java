package util.data;

import com.google.gson.Gson;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Objects;

public class Helper {

    private final FileReader filereader;

    public Helper() throws FileNotFoundException {
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