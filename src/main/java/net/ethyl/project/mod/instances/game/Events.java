package net.ethyl.project.mod.instances.game;

import net.ethyl.project.AppMod;
import net.ethyl.project.mod.instances.ClientData;
import net.ethyl.project.mod.instances.data.LastDeath;
import net.ethyl.project.mod.instances.logic.DataHandler;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.ClientPlayerNetworkEvent;
import net.neoforged.neoforge.event.entity.living.LivingDeathEvent;
import net.neoforged.neoforge.event.entity.player.PlayerEvent;
import net.neoforged.neoforge.network.PacketDistributor;

@EventBusSubscriber(modid = AppMod.MOD_ID, value = Dist.CLIENT)
public class Events {
    @SubscribeEvent
    public static void onPlayerJoinEvent(ClientPlayerNetworkEvent.LoggingIn event) {
        DataHandler.cacheWorldName();
        DataHandler.loadData();
        LastDeath lastDeath = ClientData.getInstance().getLastDeath();
        Player player = event.getPlayer();

        if (lastDeath != null && !player.isDeadOrDying()) {
            player.sendSystemMessage(Component.literal("Last death location: " + lastDeath.format()));
        }
    }

    @SubscribeEvent
    public static void onPlayerLeaveEvent(ClientPlayerNetworkEvent.LoggingOut event) {
        DataHandler.saveData();
        ClientData.destroy();
    }

    @SubscribeEvent
    public static void onPlayerDeathEvent(ClientPlayerNetworkEvent.Clone event) {
        Player corpse = event.getOldPlayer();

        if (corpse.isDeadOrDying()) {
            Player player = event.getNewPlayer();
            ClientData.getInstance().setLastDeath(corpse.position(), corpse.level().dimension());
            player.sendSystemMessage(Component.literal("You died at: " + ClientData.getInstance().getLastDeath().format()));
        }
    }
}
