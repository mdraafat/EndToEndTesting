package util.helper;

import com.google.gson.Gson;
import util.data.User;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Objects;

public class FileHandler {

    private final FileReader deleteUsersReader;
    private final FileReader mainUserReader;

    public FileHandler() throws FileNotFoundException {
        deleteUsersReader = new FileReader(
                Objects.requireNonNull(this.getClass()
                        .getClassLoader()
                        .getResource("delete_user.json")
                ).getFile()
        );

        mainUserReader = new FileReader(
                Objects.requireNonNull(this.getClass()
                        .getClassLoader()
                        .getResource("main_user.json")
                ).getFile()
        );
    }

    public User[] getUsersToBeDeleted() {
        Gson gson = new Gson();
        return gson.fromJson(deleteUsersReader, User[].class);
    }

    public User[] getMainUser() {
        Gson gson = new Gson();
        return gson.fromJson(mainUserReader, User[].class);
    }
}