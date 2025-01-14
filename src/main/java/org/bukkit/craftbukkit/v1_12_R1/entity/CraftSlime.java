package org.bukkit.craftbukkit.v1_12_R1.entity;

import net.minecraft.entity.monster.EntitySlime;
import org.bukkit.craftbukkit.v1_12_R1.CraftServer;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Slime;

public class CraftSlime extends CraftLivingEntity implements Slime {

    public CraftSlime(CraftServer server, EntitySlime entity) {
        super(server, entity);
    }

    @Override
    public int getSize() {
        return getHandle().getSlimeSize();
    }

    @Override
    public void setSize(int size) {
        getHandle().setSlimeSize(size, true);
    }

    @Override
    public void setTarget(LivingEntity target) {
        if (target == null) {
            getHandle().setGoalTarget(null, null, false);
        } else if (target instanceof CraftLivingEntity) {
            getHandle().setGoalTarget(((CraftLivingEntity) target).getHandle(), null, false);
        }
    }

    @Override
    public LivingEntity getTarget() {
        return getHandle().getAttackTarget() == null ? null : (LivingEntity)getHandle().getAttackTarget().getBukkitEntity();
    }

    @Override
    public EntitySlime getHandle() {
        return (EntitySlime) entity;
    }

    @Override
    public String toString() {
        return "CraftSlime";
    }

    @Override
    public EntityType getType() {
        return EntityType.SLIME;
    }
}
