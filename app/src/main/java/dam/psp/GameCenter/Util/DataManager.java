package dam.psp.GameCenter.Util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import dam.psp.GameCenter.Model.Game;
import java.lang.reflect.Type;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.util.List;

public class DataManager {
    private static final String FILE_NAME = "games.json";
    private static final Gson gson = new GsonBuilder().setPrettyPrinting().create();
    
    public static void writeGamesToFile(List<Game> games) {
        try (Writer writer = new FileWriter(FILE_NAME)) {
            gson.toJson(games, writer);
        } catch (IOException e) {
            System.err.println("Error in DataManager writting games on file");
            System.err.println(e.getCause());
        }
    }
    
    public static List<Game> readGamesFromFile() {
        try (Reader reader = new FileReader(FILE_NAME)) {
            Type gameListType = new TypeToken<List<Game>>() {}.getType();
            return gson.fromJson(reader, gameListType);
        } catch (IOException e) {
            System.err.println("Error in DataManager reading games on file");
            System.err.println(e.getCause());
            return null;
        }
    }
}
