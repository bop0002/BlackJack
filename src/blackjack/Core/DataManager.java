package blackjack.Core;

import blackjack.Object.Player;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.lang.reflect.Type;
import com.google.gson.reflect.TypeToken;

public class DataManager {
    //!!!
    private static final String SLOT_1 = "saves/slot1.json";
    private static final String SLOT_2 = "saves/slot2.json";
    private static final String SLOT_3 = "saves/slot3.json";

    public void saveData(Player player, String slot) {
        Gson gson = new Gson();
        try (FileWriter writer = new FileWriter(slot)) {
            PlayerData data = new PlayerData(player.getName(), player.getBalance());
            gson.toJson(data, writer);
        } catch (Exception e) {
            System.out.println("Save file error");
        }
    }

    public Player loadData(String slot) {
        Gson gson = new Gson();
        try (FileReader reader = new FileReader(slot)) {
            PlayerData data = gson.fromJson(reader, PlayerData.class);

            if (data == null) {
                return new Player("Empty", 0);
            }

            return new Player(data.getName(), data.getBalance());
        } catch (Exception e) {
            return new Player("Empty", 0);
        }
    }

    public void saveSlot(int slotNum, Player player) {
        switch (slotNum) {
            case 1:
                saveData(player, SLOT_1);
                break;
            case 2:
                saveData(player, SLOT_2);
                break;
            case 3:
                saveData(player, SLOT_3);
                break;
            default:
                System.out.println("Slot does not exit!");
        }
    }

    public Player loadSlot(int slotNum) {
        switch (slotNum) {
            case 1:
                return loadData(SLOT_1);
            case 2:
                return loadData(SLOT_2);
            case 3:
                return loadData(SLOT_3);
            default:
                return new Player("Empty", 0);
        }
    }

}
