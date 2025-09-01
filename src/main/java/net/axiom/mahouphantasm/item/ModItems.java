package net.axiom.mahouphantasm.item;

import net.axiom.mahouphantasm.MahouPhantasm;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, MahouPhantasm.MOD_ID);

    public static final RegistryObject<Item> DRAGONSLAYER = ITEMS.register("dragonslayer",
            ()-> new Item(new Item.Properties()));

    public static final RegistryObject<Item> ADAMANTINE = ITEMS.register("adamantine",
            ()-> new Item(new Item.Properties()));

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}