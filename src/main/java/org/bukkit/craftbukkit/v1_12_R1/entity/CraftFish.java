package org.bukkit.craftbukkit.v1_12_R1.entity;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityFishHook;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import org.apache.commons.lang3.Validate;
import org.bukkit.craftbukkit.v1_12_R1.CraftServer;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Fish;
import org.bukkit.projectiles.ProjectileSource;

public class CraftFish extends AbstractProjectile implements Fish {
    private double biteChance = -1;

    public CraftFish(CraftServer server, EntityFishHook entity) {
        super(server, entity);
    }

    @Override
    public ProjectileSource getShooter() {
        if (getHandle().angler != null) {
            return getHandle().angler.getBukkitEntity();
        }

        return null;
    }

    @Override
    public void setShooter(ProjectileSource shooter) {
        if (shooter instanceof CraftHumanEntity) {
            getHandle().angler = (EntityPlayer) ((CraftHumanEntity) shooter).entity;
        }
    }

    @Override
    public EntityFishHook getHandle() {
        return (EntityFishHook) entity;
    }

    @Override
    public String toString() {
        return "CraftFish";
    }

    @Override
    public EntityType getType() {
        return EntityType.FISHING_HOOK;
    }

    @Override
    public double getBiteChance() {
        EntityFishHook hook = getHandle();

        if (this.biteChance == -1) {
            if (hook.world.isRainingAt(new BlockPos(MathHelper.floor(hook.posX), MathHelper.floor(hook.posY) + 1, MathHelper.floor(hook.posZ)))) {
                return 1/300.0;
            }
            return 1/500.0;
        }
        return this.biteChance;
    }

    @Override
    public void setBiteChance(double chance) {
        Validate.isTrue(chance >= 0 && chance <= 1, "The bite chance must be between 0 and 1.");
        this.biteChance = chance;
    }
}
