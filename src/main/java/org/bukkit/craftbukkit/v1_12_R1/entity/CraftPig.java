package org.bukkit.craftbukkit.v1_12_R1.entity;

import net.minecraft.entity.passive.EntityPig;
import org.bukkit.craftbukkit.v1_12_R1.CraftServer;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Pig;

public class CraftPig extends CraftAnimals implements Pig {
    public CraftPig(CraftServer server, EntityPig entity) {
        super(server, entity);
    }

    @Override
    public boolean hasSaddle() {
        return getHandle().getSaddled();
    }

    @Override
    public void setSaddle(boolean saddled) {
        getHandle().setSaddled(saddled);
    }

    @Override
    public EntityPig getHandle() {
        return (EntityPig) entity;
    }

    @Override
    public String toString() {
        return "CraftPig";
    }

    @Override
    public EntityType getType() {
        return EntityType.PIG;
    }
}
