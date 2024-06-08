package br.com.finalcraft.evernifecore.listeners.forge;

import br.com.finalcraft.evernifecore.listeners.base.ECListener;
import br.com.finalcraft.evernifecore.listeners.forge.imp.ArclightForgeListener;
import br.com.finalcraft.evernifecore.listeners.forge.imp.MohistForgeListener;
import br.com.finalcraft.evernifecore.util.FCReflectionUtil;
import net.minecraftforge.eventbus.api.IEventBus;
import org.bukkit.plugin.Plugin;

public class ForgeListener {

    private static IForgeListener INSTANCE; static {
        if (FCReflectionUtil.isClassLoaded("io.izzel.arclight.api.Arclight")){
            INSTANCE = new ArclightForgeListener();
        }else if (FCReflectionUtil.isClassLoaded("com.mohistmc.api.event.BukkitHookForgeEvent")){
            INSTANCE = new MohistForgeListener();
        }
    }

    public static void registerClass(Plugin plugin, ECListener listener, IEventBus... eventBus){
        if (INSTANCE == null){
            throw new IllegalStateException("Tried to register ForgeEvents but there is no IForgeListener available for EverNifeCore on this Server.");
        }
        INSTANCE.registerClass(plugin, listener, eventBus);
    }

    public static void registerClass(Plugin plugin, ECListener listener){
        if (INSTANCE == null){
            throw new IllegalStateException("Tried to register ForgeEvents but there is no IForgeListener available for EverNifeCore on this Server.");
        }
        INSTANCE.registerClass(plugin, listener);
    }

}
