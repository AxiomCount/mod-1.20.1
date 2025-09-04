package net.axiom.mahouphantasm.item;

import io.redspace.ironsspellbooks.api.item.weapons.MagicSwordItem;
import io.redspace.ironsspellbooks.api.registry.SpellDataRegistryHolder;
import net.axiom.mahouphantasm.MahouPhantasm;
import net.axiom.mahouphantasm.item.custom.DragonSlayerItem;
import net.axiom.mahouphantasm.item.custom.WaterlooSaberItem;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class MahouItems {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, MahouPhantasm.MOD_ID);

    public static final RegistryObject<Item> DRAGONSLAYER = ITEMS.register("dragonslayer",
            ()-> new DragonSlayerItem(SpellDataRegistryHolder.of()));
    public static final RegistryObject<Item> WATERLOOSABER = ITEMS.register("waterloosaber",
            ()-> new WaterlooSaberItem(SpellDataRegistryHolder.of()));

    public static final RegistryObject<Item> ADAMANTINE = ITEMS.register("adamantine",
            ()-> new Item(new Item.Properties()));

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}