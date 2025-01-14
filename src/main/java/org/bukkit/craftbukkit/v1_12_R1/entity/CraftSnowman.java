package org.bukkit.craftbukkit.v1_12_R1.entity;

import net.minecraft.entity.monster.EntitySnowman;
import org.bukkit.craftbukkit.v1_12_R1.CraftServer;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Snowman;

public class CraftSnowman extends CraftGolem implements Snowman  {
    public CraftSnowman(CraftServer server, EntitySnowman entity) {
        super(server, entity);
    }

    @Override
    public boolean isDerp() {
        return !getHandle().isPumpkinEquipped();
    }

    @Override
    public void setDerp(boolean derpMode) {
        getHandle().setPumpkinEquipped(!derpMode);
    }

    @Override
    public EntitySnowman getHandle() {
        return (EntitySnowman) entity;
    }

    @Override
    public String toString() {
        return "CraftSnowman";
    }

    @Override
    public EntityType getType() {
        return EntityType.SNOWMAN;
    }
}
