package net.ethyl.project;

import org.slf4j.Logger;

import com.mojang.logging.LogUtils;

import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.ModContainer;

@Mod(AppMod.MOD_ID)
public class AppMod {
    public static final String MOD_ID = "app_mod";
    public static final Logger LOGGER = LogUtils.getLogger();

    public AppMod(IEventBus modEventBus, ModContainer modContainer) {

    }
}
