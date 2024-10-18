package br.com.finalcraft.evernifecore.listeners.forge.imp;

import br.com.finalcraft.evernifecore.listeners.base.ECListener;
import br.com.finalcraft.evernifecore.listeners.forge.IForgeListener;
import io.izzel.arclight.api.Arclight;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import org.bukkit.plugin.Plugin;

public class ArclightForgeListener implements IForgeListener {

    @Override
    public void registerClass(Plugin plugin, ECListener listener, Object... eventBus) {
        for (IEventBus bus : (IEventBus[]) eventBus) {
            Arclight.registerForgeEvent(plugin, bus, listener);
        }
    }

    @Override
    public void registerClass(Plugin plugin, ECListener listener) {
        Arclight.registerForgeEvent(plugin, MinecraftForge.EVENT_BUS, listener);
    }

}
