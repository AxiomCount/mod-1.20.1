package net.axiom.mahouphantasm.spell;

import io.redspace.ironsspellbooks.IronsSpellbooks;
import io.redspace.ironsspellbooks.api.registry.SchoolRegistry;
import io.redspace.ironsspellbooks.api.spells.SchoolType;
import io.redspace.ironsspellbooks.damage.ISSDamageTypes;
import io.redspace.ironsspellbooks.registries.SoundRegistry;
import net.axiom.mahouphantasm.MahouPhantasm;
import net.axiom.mahouphantasm.util.MahouTags;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.Style;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class MahouSchools extends SchoolRegistry {
    private static final DeferredRegister<SchoolType> MAHOU_SCHOOLS = DeferredRegister.create(SCHOOL_REGISTRY_KEY, MahouPhantasm.MOD_ID);

    private static RegistryObject<SchoolType> registerSchool(SchoolType type) { return MAHOU_SCHOOLS.register(type.getId().getPath(), () -> type); }

    // Red Mist
    public static final ResourceLocation REDMIST_RESOURCE = MahouPhantasm.id("redmist");

    public static final RegistryObject<SchoolType> REDMIST = registerSchool(new SchoolType
            (
                    REDMIST_RESOURCE,
                    MahouTags.REDMIST_FOCUS,
                    Component.translatable("school.mahouphantasm.redmist").withStyle(Style.EMPTY.withColor(0xc22502)),
                    LazyOptional.empty(),      //of(CSAttributeRegistry.ABYSSAL_MAGIC_POWER::get),
                    LazyOptional.empty(),              //of(CSAttributeRegistry.ABYSSAL_MAGIC_RESIST::get),
                    LazyOptional.of(SoundRegistry.BLOOD_EXPLOSION::get),
                    ISSDamageTypes.BLOOD_MAGIC
            ));
    public static void register(IEventBus eventBus) { MAHOU_SCHOOLS.register(eventBus); }
}
