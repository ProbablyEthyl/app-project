package net.ethyl.project.mod.instances.logic;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import net.ethyl.project.AppMod;
import net.ethyl.project.mod.instances.ClientData;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ServerData;
import net.minecraft.client.server.IntegratedServer;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.storage.LevelResource;
import net.neoforged.fml.loading.FMLPaths;
import org.jetbrains.annotations.NotNull;

import java.io.*;

public class DataHandler {
    private static String saveName = null;

    private static final Gson GSON = new GsonBuilder()
            .setPrettyPrinting()
            .excludeFieldsWithoutExposeAnnotation()
            .create();

    public static void saveData() {
        File file = getSaveFile();

        if (file != null) {
            try (FileWriter writer = new FileWriter(file)) {
                GSON.toJson(ClientData.getInstance(), writer);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        saveName = null;
    }

    public static void loadData() {
        File file = getSaveFile();

        if (file != null) {
            if (file.length() != 0) {
                try (FileReader reader = new FileReader(file)) {
                    ClientData loadedData = GSON.fromJson(reader, ClientData.class);

                    if (loadedData != null) {
                        ClientData.loadInstance(loadedData);
                    }
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            } else {
                ClientData.getInstance();
            }
        }
    }

    public static File getSaveFile() {
        if (saveName != null) {
            File saveFile = FMLPaths.GAMEDIR.get().resolve(AppMod.MOD_ID).resolve(saveName).resolve("data.json").toFile();
            createFile(saveFile);

            return saveFile;
        }

        return null;
    }

    private static void createFile(File file) {
        if (file != null && !file.exists()) {
            try {
                File parentFile = file.getParentFile();

                if (!parentFile.exists() && parentFile.mkdirs()) {
                    AppMod.LOGGER.info("Made the files '{}'!", parentFile.getAbsolutePath());
                }

                if (file.createNewFile()) {
                    AppMod.LOGGER.info("Made the file '{}'!", file.getName());
                }
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }

    public static void cacheWorldName() {
        Minecraft minecraft = Minecraft.getInstance();

        if (minecraft.isLocalServer()) {
            IntegratedServer world = minecraft.getSingleplayerServer();

            if (world != null) {
                saveName = "sp_" + format(world.getWorldPath(LevelResource.ROOT).getParent().getFileName().toString());
            }
        } else if (minecraft.getConnection() != null) {
            saveName = "mp_" + format(minecraft.getConnection().getConnection().getRemoteAddress().toString());
        }
    }

    private static String format(@NotNull String string) {
        return string.toLowerCase().replaceAll("[^a-z0-9()._-]", "-");
    }
}
