package net.ethyl.project.mod.instances.data;

import com.google.gson.annotations.Expose;
import net.ethyl.project.AppMod;
import net.minecraft.core.BlockPos;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.NotNull;

public class LastDeath {
    @Expose private int x = 0;
    @Expose private int y = 0;
    @Expose private int z = 0;

    public LastDeath(Vec3 vec3) {
        this(toBlockPos(vec3));
    }

    public LastDeath(BlockPos blockPos) {
        this.x = blockPos.getX();
        this.y = blockPos.getY();
        this.z = blockPos.getZ();
    }

    public String format() {
        return "[" + this.x + " " + this.y + " " + this.z + "]";
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

    private static BlockPos toBlockPos(@NotNull Vec3 vec3) {
        return new BlockPos((int) Math.floor(vec3.x()), (int) Math.floor(vec3.y()), (int) Math.floor(vec3.z()));
    }
}
