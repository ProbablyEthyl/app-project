package net.ethyl.project.mod.instances.data;

import com.google.gson.annotations.Expose;
import net.minecraft.core.BlockPos;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.NotNull;

public class LastDeath {
    @Expose private int x = 0;
    @Expose private int y = 0;
    @Expose private int z = 0;
    @Expose private String dimension = "";

    public LastDeath(Vec3 vec3, String dimension) {
        this(toBlockPos(vec3), dimension);
    }

    public LastDeath(BlockPos blockPos, String dimension) {
        this.x = blockPos.getX();
        this.y = blockPos.getY();
        this.z = blockPos.getZ();
        this.dimension = dimension;
    }

    public String format() {
        return "[" + this.x + " " + this.y + " " + this.z + "] in '" + this.dimension + "'";
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getZ() {
        return z;
    }

    public String getDimension() {
        return this.dimension;
    }

    private static BlockPos toBlockPos(@NotNull Vec3 vec3) {
        return new BlockPos((int) Math.floor(vec3.x()), (int) Math.floor(vec3.y()), (int) Math.floor(vec3.z()));
    }
}
