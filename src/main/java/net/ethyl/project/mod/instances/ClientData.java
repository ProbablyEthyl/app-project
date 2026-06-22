package net.ethyl.project.mod.instances;

import com.google.gson.annotations.Expose;
import net.ethyl.project.mod.instances.data.LastDeath;
import net.minecraft.core.BlockPos;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.NotNull;

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

    public void setLastDeath(@NotNull Vec3 vec3) {
        this.lastDeath = new LastDeath(vec3);
    }

    public void setLastDeath(@NotNull BlockPos blockPos) {
        this.lastDeath = new LastDeath(blockPos);
    }

    public LastDeath getLastDeath() {
        return this.lastDeath;
    }
}
