package net.axiom.mahouphantasm.item.custom;

import io.redspace.ironsspellbooks.api.item.weapons.MagicSwordItem;
import io.redspace.ironsspellbooks.api.registry.AttributeRegistry;
import io.redspace.ironsspellbooks.api.registry.SpellDataRegistryHolder;
import io.redspace.ironsspellbooks.util.ItemPropertiesHelper;
import net.axiom.mahouphantasm.item.MahouToolTiers;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.item.Rarity;

import java.util.Map;
import java.util.UUID;

public class MimicryItem extends MagicSwordItem {
    public MimicryItem(SpellDataRegistryHolder[] spellDataRegistryHolders) {
        super(MahouToolTiers.MIMICRY, MahouToolTiers.MIMICRY.getAttackDamageBonus(), MahouToolTiers.MIMICRY.getSpeed(),
                spellDataRegistryHolders,
                Map.of(
                        AttributeRegistry.BLOOD_SPELL_POWER.get(), new AttributeModifier(UUID.fromString("c0af0076-6028-4521-a9f5-66f7501cc758"), "Blood Spell Power", 0.25f, AttributeModifier.Operation.MULTIPLY_TOTAL)
//                        AttributeRegistry.COOLDOWN_REDUCTION.get(), new AttributeModifier(UUID.fromString("fc7c1cba-586a-4ebc-a189-f5378747ab1b"), "Cooldown Reduction", 0.10f, AttributeModifier.Operation.MULTIPLY_TOTAL)
                ), ItemPropertiesHelper.equipment(1).rarity(Rarity.EPIC));

    }
}