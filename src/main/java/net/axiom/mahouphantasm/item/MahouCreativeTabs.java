package net.axiom.mahouphantasm.item;

import net.axiom.mahouphantasm.MahouPhantasm;
import net.axiom.mahouphantasm.block.MahouBlocks;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class MahouCreativeTabs {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, MahouPhantasm.MOD_ID);

    public static final RegistryObject<CreativeModeTab> MAHOUPHANTASM_TAB = CREATIVE_MODE_TABS.register("mahouphantasm_tab",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(MahouItems.ADAMANTINE.get()))
                    .title(Component.translatable("creativetab.mahouphantasm_tab"))
                    .displayItems((pParameters, pOutput) -> {
                        pOutput.accept(MahouItems.DRAGONSLAYER.get());
                        pOutput.accept(MahouItems.WATERLOOSABER.get());

                        pOutput.accept(MahouItems.ADAMANTINE.get());

                        pOutput.accept(MahouBlocks.ADAMANTINE_BLOCK.get());

//                        pOutput.accept(MahouBlocks.sonuvabitch_BLOCK.get());
                    })
                    .build());


    public static void register(IEventBus eventBus) {
        CREATIVE_MODE_TABS.register(eventBus);
    }
}
