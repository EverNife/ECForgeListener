# ECForgeListener

This is a wrapper intended to be used inside https://github.com/EverNife/EverNifeCore to make my life easier!

It creates a easy way to register event handlers on different types of Forge Platforms.

Right now supports Mohist and Archligh.

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

On Mohist it does not require the BUS, but on Archlight it requires the BUS. So, its a good idea to always let the bus over there :D
