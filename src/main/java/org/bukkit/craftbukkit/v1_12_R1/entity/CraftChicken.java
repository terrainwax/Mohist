package org.bukkit.craftbukkit.v1_12_R1.entity;

import net.minecraft.entity.passive.EntityChicken;
import org.bukkit.craftbukkit.v1_12_R1.CraftServer;
import org.bukkit.entity.Chicken;
import org.bukkit.entity.EntityType;

public class CraftChicken extends CraftAnimals implements Chicken {

    public CraftChicken(CraftServer server, EntityChicken entity) {
        super(server, entity);
    }

    @Override
    public EntityChicken getHandle() {
        return (EntityChicken) entity;
    }

    @Override
    public String toString() {
        return "CraftChicken";
    }

    @Override
    public EntityType getType() {
        return EntityType.CHICKEN;
    }
}
