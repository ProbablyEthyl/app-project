package net.ethyl.project.mod.instances;

import com.google.gson.annotations.Expose;
import net.ethyl.project.mod.instances.data.LastDeath;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.NotNull;

import java.awt.*;

public class ClientData {
    private static ClientData instance;

    public static ClientData getInstance() {
        if (instance == null) {
            instance = new ClientData();
        }

        return instance;
    }

    public static void loadInstance(ClientData data) {
        instance = data;
    }

    public static void destroy() {
        instance = null;
    }

    @Expose private LastDeath lastDeath = null;

    public void setLastDeath(@NotNull Vec3 vec3, ResourceKey<Level> dimension) {
        this.lastDeath = new LastDeath(vec3, dimension.location().toString());
    }

    public LastDeath getLastDeath() {
        return this.lastDeath;
    }
}
