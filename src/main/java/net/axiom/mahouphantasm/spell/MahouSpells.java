package net.axiom.mahouphantasm.spell;

import io.redspace.ironsspellbooks.api.registry.SpellRegistry;
import io.redspace.ironsspellbooks.api.spells.AbstractSpell;
import net.axiom.mahouphantasm.MahouPhantasm;
import net.axiom.mahouphantasm.spell.redmist.UpstandingSlashSpell;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class MahouSpells {
    public static final DeferredRegister<AbstractSpell> SPELLS =
            DeferredRegister.create(SpellRegistry.SPELL_REGISTRY_KEY, MahouPhantasm.MOD_ID);

    public static RegistryObject<AbstractSpell> registerSpell(AbstractSpell spell) {
        return SPELLS.register(spell.getSpellName(), () -> spell);
    }

    public static final RegistryObject<AbstractSpell> UPSTANDINGSLASH = registerSpell(new UpstandingSlashSpell());


//    public static final RegistryObject<AbstractSpell> REDMIST1 =
//            SPELLS.register ("redmist1", UpstandingSlashSpell::new);

    public static void register(IEventBus eventBus) { SPELLS.register(eventBus); }
}