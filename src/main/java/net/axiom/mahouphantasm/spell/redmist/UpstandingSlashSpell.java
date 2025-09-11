package net.axiom.mahouphantasm.spell.redmist;

import io.redspace.ironsspellbooks.api.spells.*;
import io.redspace.ironsspellbooks.api.config.DefaultConfig;
import io.redspace.ironsspellbooks.api.registry.SchoolRegistry;
import io.redspace.ironsspellbooks.api.magic.MagicData;
import io.redspace.ironsspellbooks.api.util.AnimationHolder;
import io.redspace.ironsspellbooks.api.util.Utils;
import io.redspace.ironsspellbooks.capabilities.magic.MagicManager;
import io.redspace.ironsspellbooks.registries.MobEffectRegistry;
import io.redspace.ironsspellbooks.registries.SoundRegistry;
import io.redspace.ironsspellbooks.util.ParticleHelper;
import net.axiom.mahouphantasm.MahouPhantasm;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.AABB;
import net.minecraft.sounds.SoundEvents;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Optional;

@AutoSpellConfig
public class UpstandingSlashSpell extends AbstractSpell {
    private static final ResourceLocation SPELL_ID = new ResourceLocation(MahouPhantasm.MOD_ID, "upstandingslash");


    @Override
    public List<MutableComponent> getUniqueInfo(int spellLevel, LivingEntity caster) {
        float damage = getSpellPower(spellLevel, caster);
        return List.of(
                Component.translatable("ui.irons_spellbooks.damage", Utils.stringTruncation(damage, 2))
        );
    }

    private final DefaultConfig defaultConfig = new DefaultConfig()
            .setMinRarity(SpellRarity.RARE)
            .setSchoolResource(SchoolRegistry.BLOOD_RESOURCE)
            .setMaxLevel(3)
            .setCooldownSeconds(20)
            .build();

    public UpstandingSlashSpell() {
        this.manaCostPerLevel = 15;
        this.baseSpellPower = 12;
        this.spellPowerPerLevel = 4;
        this.castTime = 20;
        this.baseManaCost = 50;
    }

    @Override
    public SpellRarity getRarity(int spellLevel) {
        return switch (spellLevel) {
            case 1 -> SpellRarity.RARE;
            case 2 -> SpellRarity.EPIC;
            case 3 -> SpellRarity.LEGENDARY;
            default -> SpellRarity.COMMON;
        };
    }

    @Override
    public boolean canBeCraftedBy(Player player) {
        return false;
    }

    @Override
    public boolean allowLooting() {
        return false;
    }

    @Override
    public DefaultConfig getDefaultConfig() {
        return defaultConfig;
    }

    @Override
    public CastType getCastType() {
        return CastType.LONG;
    }

//    @Override
//    public int getEffectiveCastTime(int spellLevel, @Nullable LivingEntity entity) {
//        int base = getCastTime(spellLevel);
//
//        if (entity != null && entity.hasEffect(MahouEffects.MANIFESTED.get())) {
//            // each level is reducing 20%
//            int amplifier = entity.getEffect(MahouEffects.MANIFESTED.get()).getAmplifier();
//            double multiplier = 1.0 - (0.2 * (amplifier + 1));
//            return (int) Math.max(1, base * multiplier); // Not 0
//        }
//
//        return base;
//    }

    @Override
    public ResourceLocation getSpellResource() { return SPELL_ID; }

//    change it to MahouEffects.MANIFESTED later
    @Override
    public boolean canBeInterrupted(@Nullable Player player) {
        return player == null || !player.hasEffect(MobEffectRegistry.FORTIFY.get());
        }

    @Override
    public Optional<SoundEvent> getCastStartSound() { return Optional.of(SoundRegistry.SCORCH_PREPARE.get()); }

    @Override
    public Optional<SoundEvent> getCastFinishSound() { return Optional.of(SoundRegistry.BLOOD_EXPLOSION.get()); }

    @Override
    public AnimationHolder getCastStartAnimation() { return SpellAnimations.OVERHEAD_MELEE_SWING_ANIMATION; }

    @Override
    public AnimationHolder getCastFinishAnimation() { return AnimationHolder.pass(); }

    @Override
    public void onCast(Level level, int spellLevel, LivingEntity caster, CastSource source, MagicData data) {
        final double LENGTH = 3.0;
        final double WIDTH = 1.0;
        final double HEIGHT = 2.0;
        float damage = getSpellPower(spellLevel, caster);

        Vec3 eye = caster.getEyePosition();
        Vec3 forward = caster.getLookAngle().normalize();
        Vec3 center = eye.add(forward.scale(LENGTH / 2));

        if (!level.isClientSide) {
            // HITBOX
            Vec3 right = forward.cross(new Vec3(0, 1, 0)).normalize();
            Vec3 up = right.cross(forward).normalize();

            Vec3[] corners = new Vec3[8];
            int i = 0;
            for (int dx : new int[]{-1, 1}) {
                for (int dy : new int[]{-1, 1}) {
                    for (int dz : new int[]{-1, 1}) {
                        corners[i++] = center
                                .add(forward.scale(dx * LENGTH / 2))
                                .add(right.scale(dz * WIDTH / 2))
                                .add(up.scale(dy * HEIGHT / 2));
                    }
                }
            }

            // Hitbox visualisation
            for (Vec3 c : corners) {
                MagicManager.spawnParticles(level, ParticleHelper.EMBERS,
                        c.x, c.y, c.z,
                        5, 0.01, 0.01, 0.01,
                        0.0, false
                );
            }

            AABB box = new AABB(
                    center.x - WIDTH, center.y - WIDTH, center.z - WIDTH,
                    center.x + WIDTH, center.y + WIDTH, center.z + WIDTH
            );

            List<LivingEntity> targets = level.getEntitiesOfClass(
                    LivingEntity.class,
                    box,
                    e -> e != caster && e.isAlive() && e.isPickable()
            );

            if (!targets.isEmpty()) {
                LivingEntity target = targets.get(0);
                target.hurt(level.damageSources().mobAttack(caster), damage);

                MagicManager.spawnParticles(level, ParticleHelper.BLOOD,
                        target.getX(),
                        target.getY() + target.getBbHeight() * 0.4,
                        target.getZ(), 30,
                        target.getBbWidth() * 0.5,
                        target.getBbHeight() * 0.5,
                        target.getBbWidth() * 0.5,
                        0.03, false
                );
            }
        }
    }
}









//    @Override
//    public void onCast(Level level, int spellLevel, LivingEntity caster, CastSource source, MagicData data) {
//        final double RANGE = 2;
//        final double HITBOX_RADIUS = 1;
//        float damage = getSpellPower(spellLevel, caster);
//
//        Vec3 eye = caster.getEyePosition();
//        Vec3 look = caster.getLookAngle().normalize();
//        Vec3 center = eye.add(look.scale(RANGE));
//
//        if (!level.isClientSide) {
//            AABB box = new AABB(
//                    center.x - HITBOX_RADIUS, center.y - HITBOX_RADIUS, center.z - HITBOX_RADIUS,
//                    center.x + HITBOX_RADIUS, center.y + HITBOX_RADIUS, center.z + HITBOX_RADIUS
//            );
//
//            // Hitbox
//            for (double x : new double[]{box.minX, box.maxX}) {
//                for (double y : new double[]{box.minY, box.maxY}) {
//                    for (double z : new double[]{box.minZ, box.maxZ}) {
//                        MagicManager.spawnParticles(level, ParticleHelper.EMBERS,
//                                x, y, z,
//                                5,
//                                0.01, 0.01, 0.01,
//                                0.0, false
//                        );
//                    }
//                }
//            }
////            // Filled Hitbox
////            MagicManager.spawnParticles(level, ParticleHelper.FIRE,
////                    box.getCenter().x, box.getCenter().y, box.getCenter().z,
////                    250,
////                    box.getXsize() / 2,
////                    box.getYsize() / 2,
////                    box.getZsize() / 2,
////                    0.0001, false
////            );
//
//            List<LivingEntity> targets = level.getEntitiesOfClass(
//                    LivingEntity.class,
//                    box,
//                    e -> e != caster && e.isAlive() && e.isPickable()
//            );
//
//            level.playSound(null, caster.blockPosition(), SoundEvents.PLAYER_ATTACK_SWEEP, caster.getSoundSource(), 1.0f, 1.0f);
//
//            if (!targets.isEmpty()) {
//                LivingEntity target = targets.get(0);
//                target.hurt(level.damageSources().mobAttack(caster), damage);
//
//                MagicManager.spawnParticles(level, ParticleHelper.BLOOD,
//                        target.getX(),
//                        target.getY() + target.getBbHeight() * 0.4,
//                        target.getZ(), 30,
//                        target.getBbWidth() * 0.5,
//                        target.getBbHeight() * 0.5,
//                        target.getBbWidth() * 0.5,
//                        0.03, false
//                );
//            }
//        }
//    }
//}
