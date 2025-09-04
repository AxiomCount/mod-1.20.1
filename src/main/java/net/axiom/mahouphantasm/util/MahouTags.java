package net.axiom.mahouphantasm.util;

import net.axiom.mahouphantasm.MahouPhantasm;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagBuilder;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

public class MahouTags {
    public static class Blocks {
//        public static final TagKey<Block> METAL_DETECTOR_ORES = tag("metal_detector_ores");




        private static TagKey<Block> tag(String name) {
            return BlockTags.create(new ResourceLocation(MahouPhantasm.MOD_ID, name));
        }
    }

    public static class Items {
        //Item tags is not implemented


        private static TagKey<Item> tag(String name) {
            return ItemTags.create(new ResourceLocation(MahouPhantasm.MOD_ID, name));
        }
    }
}
