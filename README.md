# ECForgeListener

This is a wrapper intended to be used inside https://github.com/EverNife/EverNifeCore to make my life easier!

It creates a easy way to register event handlers on different types of Forge Platforms.

Right now supports:
- 1.7.10 : Crucible
- 1.12.2 : Mohist
- 1.16.5 : Mohist and Arclight
- 1.20.1 : Mohist and Arclight
- 1.20.2 : Mohist and Arclight
- 1.21.1 : Mohist and Arclight

The idea is to register Handlers at a bukkit plugin like this 

```java
    @Override
    public void onRegister() {
        ForgeListener.registerClass(MyPlugin.instance, this, Pixelmon.EVENT_BUS, MinecraftForge.EVENT_BUS);
    }
    
    @EventHandler(priority = EventPriority.MONITOR) //Normal bukkit Event
    public void onBukkitPickup(EntityPickupItemEvent event) {
        //blabla
    }

    @SubscribeEvent(priority = net.minecraftforge.eventbus.api.EventPriority.LOWEST) //Forge Event Handler
    public void onPixelmonPickup(PickupEvent event) {
        net.minecraft.item.ItemStack mcStack = event.stack;
        
        //blabla
    }
```

On some versions of Mohist it does not require the Event.BUS to register events, but on Arclight it is always required.

So, its a good idea to always let the bus over there :D
