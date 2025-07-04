package br.com.finalcraft.evernifecore.listeners.forge.imp;

import br.com.finalcraft.evernifecore.EverNifeCore;
import br.com.finalcraft.evernifecore.listeners.base.ECListener;
import br.com.finalcraft.evernifecore.listeners.forge.IForgeListener;
import com.mohistmc.api.event.BukkitHookForgeEvent;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.plugin.Plugin;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.function.Consumer;

public class MohistForgeListener implements IForgeListener, ECListener {

    private static Class SubscribeEventClass;
    static {
        try {
            // Modern +1.16.5 Forge
            SubscribeEventClass = Class.forName("net.minecraftforge.eventbus.api.SubscribeEvent");
        } catch (Throwable e1) {
            try {
                // Old 1.12.2 Forge
                SubscribeEventClass = Class.forName("net.minecraftforge.fml.common.eventhandler.SubscribeEvent");
            } catch (Throwable e2) {
                throw new RuntimeException("Failed to find @SubscribeEvent class for MohistForgeListener", e2);
            }
        }
    }

    public MohistForgeListener() {
        ECListener.register(EverNifeCore.instance, this);
    }

    @Override
    public void registerListener(Plugin plugin, ECListener listener, Object... eventBus) {
        registerListener(plugin, listener); // Mohist does not use BUS to register
    }

    @Override
    public void registerListener(Plugin plugin, ECListener listener) {

        for (Method declaredMethod : listener.getClass().getDeclaredMethods()) {
            declaredMethod.setAccessible(true);

            if (declaredMethod.isAnnotationPresent(SubscribeEventClass)){
                try {
                    Class forgeEvent = declaredMethod.getParameters()[0].getType();
                    this.addEventHandler(forgeEvent, event -> {
                        try {
                            declaredMethod.invoke(listener, event);
                        } catch (Throwable e) {
                            plugin.getLogger().severe("Error while invoking ForgeEvent " + forgeEvent.getSimpleName() + " on " + listener.getClass().getSimpleName());
                            e.printStackTrace();
                        }
                    });
                }catch (Throwable e){
                    plugin.getLogger().severe("Failed to register ForgeEvent listener for method: " + declaredMethod.getName());
                    e.printStackTrace();
                }
            }
        }

    }

    //------------------------------------------------------------------------------------------------------------------
    //  Create Listener for the Core Events
    //------------------------------------------------------------------------------------------------------------------

    private HashMap<Class, List<Consumer>> eventHandlers = new LinkedHashMap<>();

    @EventHandler(ignoreCancelled = false, priority = EventPriority.MONITOR)
    public void onBukkitHookForgeEvent(BukkitHookForgeEvent event) {
        Object forgeEvent = event.getEvent();
        List<Consumer> consumers = eventHandlers.get(forgeEvent.getClass());
        if (consumers != null){
            for (Consumer consumer : consumers){
                try {
                    consumer.accept(forgeEvent);
                }catch (Throwable e){
                    e.printStackTrace();
                }
            }
        }
    }

    public <T> void addEventHandler(Class<T> clazz, Consumer<T> consumer){
        List<Consumer> consumers = eventHandlers.computeIfAbsent(clazz, k -> new ArrayList<>());
        consumers.add(consumer);
    }

}
