package org.bukkit.craftbukkit.v1_12_R1.entity;

import net.minecraft.entity.monster.AbstractSkeleton;
import org.bukkit.craftbukkit.v1_12_R1.CraftServer;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Skeleton;

public class CraftSkeleton extends CraftMonster implements Skeleton {

    public CraftSkeleton(CraftServer server, AbstractSkeleton entity) {
        super(server, entity);
    }

    @Override
    public AbstractSkeleton getHandle() {
        return (AbstractSkeleton) entity;
    }

    @Override
    public String toString() {
        return "CraftSkeleton";
    }

    @Override
    public EntityType getType() {
        return EntityType.SKELETON;
    }

    @Override
    public SkeletonType getSkeletonType() {
       return SkeletonType.NORMAL;
    }

    @Override
    public void setSkeletonType(SkeletonType type) {
        throw new UnsupportedOperationException("Not supported.");
    }
}
