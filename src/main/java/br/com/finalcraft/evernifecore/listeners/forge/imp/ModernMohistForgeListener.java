package br.com.finalcraft.evernifecore.listeners.forge.imp;

import br.com.finalcraft.evernifecore.listeners.base.ECListener;
import br.com.finalcraft.evernifecore.listeners.forge.IForgeListener;
import br.com.finalcraft.evernifecore.reflection.MethodInvoker;
import br.com.finalcraft.evernifecore.util.FCReflectionUtil;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.EventBus;
import net.minecraftforge.eventbus.api.IEventBus;
import org.bukkit.plugin.Plugin;

public class ModernMohistForgeListener implements IForgeListener {

    private static MethodInvoker<Object> MohistEventBus_register = FCReflectionUtil.getMethod(
            FCReflectionUtil.getClass("com.mohistmc.forge.MohistEventBus"),
            "register"
    );

    @Override
    public void registerClass(Plugin plugin, ECListener listener, IEventBus... eventBus) {
        for (IEventBus bus : eventBus) {
            MohistEventBus_register.invoke(null, (EventBus) bus, listener);
        }
    }

    @Override
    public void registerClass(Plugin plugin, ECListener listener) {
        MohistEventBus_register.invoke(null, (EventBus) MinecraftForge.EVENT_BUS, listener);
    }

}
