package org.bukkit.craftbukkit.v1_12_R1.entity;

import net.minecraft.entity.passive.EntityBat;
import org.bukkit.craftbukkit.v1_12_R1.CraftServer;
import org.bukkit.entity.Bat;
import org.bukkit.entity.EntityType;

public class CraftBat extends CraftAmbient implements Bat {
    public CraftBat(CraftServer server, EntityBat entity) {
        super(server, entity);
    }

    @Override
    public EntityBat getHandle() {
        return (EntityBat) entity;
    }

    @Override
    public String toString() {
        return "CraftBat";
    }

    @Override
    public EntityType getType() {
        return EntityType.BAT;
    }

    @Override
    public boolean isAwake() {
        return !getHandle().getIsBatHanging();
    }

    @Override
    public void setAwake(boolean state) {
        getHandle().setIsBatHanging(!state);
    }
}
