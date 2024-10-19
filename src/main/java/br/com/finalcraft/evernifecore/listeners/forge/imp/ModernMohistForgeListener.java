package br.com.finalcraft.evernifecore.listeners.forge.imp;

import br.com.finalcraft.evernifecore.listeners.base.ECListener;
import br.com.finalcraft.evernifecore.listeners.forge.IForgeListener;
import br.com.finalcraft.evernifecore.reflection.MethodInvoker;
import br.com.finalcraft.evernifecore.util.FCReflectionUtil;
import net.minecraftforge.common.MinecraftForge;
import org.bukkit.plugin.Plugin;

public class ModernMohistForgeListener implements IForgeListener {

    private static MethodInvoker<Object> MohistEventBus_register = FCReflectionUtil.getMethod(
            FCReflectionUtil.getClass("com.mohistmc.forge.MohistEventBus"),
            "register"
    );

    @Override
    public void registerListener(Plugin plugin, ECListener listener, Object... eventBus) {
        for (Object bus : eventBus) {
            MohistEventBus_register.invoke(null, bus, listener);
        }
    }

    @Override
    public void registerListener(Plugin plugin, ECListener listener) {
        MohistEventBus_register.invoke(null, MinecraftForge.EVENT_BUS, listener);
    }

}
