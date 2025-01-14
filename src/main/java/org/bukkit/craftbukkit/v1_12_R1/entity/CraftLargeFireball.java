package org.bukkit.craftbukkit.v1_12_R1.entity;

import net.minecraft.entity.projectile.EntityLargeFireball;
import org.bukkit.craftbukkit.v1_12_R1.CraftServer;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LargeFireball;

public class CraftLargeFireball extends CraftFireball implements LargeFireball {
    public CraftLargeFireball(CraftServer server, EntityLargeFireball entity) {
        super(server, entity);
    }

    @Override
    public void setYield(float yield) {
        super.setYield(yield);
        getHandle().explosionPower = (int) yield;
    }

    @Override
    public EntityLargeFireball getHandle() {
        return (EntityLargeFireball) entity;
    }

    @Override
    public String toString() {
        return "CraftLargeFireball";
    }

    @Override
    public EntityType getType() {
        return EntityType.FIREBALL;
    }
}
