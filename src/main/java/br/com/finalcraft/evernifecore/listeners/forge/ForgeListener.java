package br.com.finalcraft.evernifecore.listeners.forge;

import br.com.finalcraft.evernifecore.listeners.base.ECListener;
import br.com.finalcraft.evernifecore.listeners.forge.imp.ArclightForgeListener;
import br.com.finalcraft.evernifecore.listeners.forge.imp.CrucibleForgeListener;
import br.com.finalcraft.evernifecore.listeners.forge.imp.ModernMohistForgeListener;
import br.com.finalcraft.evernifecore.listeners.forge.imp.MohistForgeListener;
import br.com.finalcraft.evernifecore.util.FCReflectionUtil;
import net.minecraftforge.eventbus.api.IEventBus;
import org.bukkit.plugin.Plugin;

public class ForgeListener {

    private static IForgeListener INSTANCE; static {
        if (FCReflectionUtil.isClassLoaded("io.github.crucible.api.CrucibleEventBus")){
            //Present on 1.7.10
            INSTANCE = new CrucibleForgeListener();
        }else if (FCReflectionUtil.isClassLoaded("io.izzel.arclight.api.Arclight")){
            //Present on 1.12.2 and 1.16.5 and 1.20.1
            INSTANCE = new ArclightForgeListener();
        }else if (FCReflectionUtil.isClassLoaded("com.mohistmc.forge.MohistEventBus")){
            //Present on 1.20.1
            INSTANCE = new ModernMohistForgeListener();
        }else if (FCReflectionUtil.isClassLoaded("com.mohistmc.api.event.BukkitHookForgeEvent")){
            //present on 1.12.2 and 1.16.5
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
