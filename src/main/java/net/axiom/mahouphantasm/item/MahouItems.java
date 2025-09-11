package net.axiom.mahouphantasm.item;

import io.redspace.ironsspellbooks.api.registry.SpellDataRegistryHolder;
import io.redspace.ironsspellbooks.api.registry.SpellRegistry;
import net.axiom.mahouphantasm.MahouPhantasm;
import net.axiom.mahouphantasm.item.custom.DragonSlayerItem;
import net.axiom.mahouphantasm.item.custom.MimicryItem;
import net.axiom.mahouphantasm.item.custom.WaterlooSaberItem;
import net.axiom.mahouphantasm.spell.MahouSpells;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class MahouItems {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, MahouPhantasm.MOD_ID);

    public static final RegistryObject<Item> DRAGONSLAYER = ITEMS.register("dragonslayer",
            ()-> new DragonSlayerItem(SpellDataRegistryHolder.of(new SpellDataRegistryHolder(SpellRegistry.GUST_SPELL, 9))));
    public static final RegistryObject<Item> WATERLOOSABER = ITEMS.register("waterloosaber",
            ()-> new WaterlooSaberItem(SpellDataRegistryHolder.of(new SpellDataRegistryHolder(SpellRegistry.FORTIFY_SPELL, 8))));
    public static final RegistryObject<Item> MIMICRY = ITEMS.register("mimicry",
            ()-> new MimicryItem(SpellDataRegistryHolder.of(new SpellDataRegistryHolder(MahouSpells.UPSTANDINGSLASH, 1))));

    public static final RegistryObject<Item> ADAMANTINE = ITEMS.register("adamantine",
            ()-> new Item(new Item.Properties()));

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}