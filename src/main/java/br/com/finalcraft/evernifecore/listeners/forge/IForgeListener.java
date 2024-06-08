package br.com.finalcraft.evernifecore.listeners.forge;

import br.com.finalcraft.evernifecore.listeners.base.ECListener;
import net.minecraftforge.eventbus.api.IEventBus;
import org.bukkit.plugin.Plugin;

public interface IForgeListener {

    public void registerClass(Plugin plugin, ECListener listener, IEventBus... eventBus);

    public void registerClass(Plugin plugin, ECListener listener);

}
