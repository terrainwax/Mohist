package org.bukkit.craftbukkit.v1_12_R1.entity;

import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.item.EntityMinecart;
import net.minecraft.init.Blocks;
import org.bukkit.craftbukkit.v1_12_R1.CraftServer;
import org.bukkit.craftbukkit.v1_12_R1.util.CraftMagicNumbers;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Minecart;
import org.bukkit.material.MaterialData;
import org.bukkit.util.Vector;

public class CraftMinecart extends CraftVehicle implements Minecart {
    public CraftMinecart(CraftServer server, EntityMinecart entity) {
        super(server, entity);
    }

    @Override
    public void setDamage(double damage) {
        getHandle().setDamage((float) damage);
    }

    @Override
    public double getDamage() {
        return getHandle().getDamage();
    }

    @Override
    public double getMaxSpeed() {
        return getHandle().maxSpeed;
    }

    @Override
    public void setMaxSpeed(double speed) {
        if (speed >= 0D) {
            getHandle().maxSpeed = speed;
        }
    }

    @Override
    public boolean isSlowWhenEmpty() {
        return getHandle().slowWhenEmpty;
    }

    @Override
    public void setSlowWhenEmpty(boolean slow) {
        getHandle().slowWhenEmpty = slow;
    }

    @Override
    public Vector getFlyingVelocityMod() {
        return getHandle().getFlyingVelocityMod();
    }

    @Override
    public void setFlyingVelocityMod(Vector flying) {
        getHandle().setFlyingVelocityMod(flying);
    }

    @Override
    public Vector getDerailedVelocityMod() {
        return getHandle().getDerailedVelocityMod();
    }

    @Override
    public void setDerailedVelocityMod(Vector derailed) {
        getHandle().setDerailedVelocityMod(derailed);
    }

    @Override
    public EntityMinecart getHandle() {
        return (EntityMinecart) entity;
    }

    @Override
    public void setDisplayBlock(MaterialData material) {
        if(material != null) {
            IBlockState block = CraftMagicNumbers.getBlock(material.getItemTypeId()).getStateFromMeta(material.getData());
            this.getHandle().setDisplayTile(block);
        } else {
            // Set block to air (default) and set the flag to not have a display block.
            this.getHandle().setDisplayTile(Blocks.AIR.getDefaultState());
            this.getHandle().setHasDisplayTile(false);
        }
    }

    @Override
    public MaterialData getDisplayBlock() {
        IBlockState blockData = getHandle().getDisplayTile();
        return CraftMagicNumbers.getMaterial(blockData.getBlock()).getNewData((byte) blockData.getBlock().getMetaFromState(blockData));
    }

    @Override
    public void setDisplayBlockOffset(int offset) {
        getHandle().setDisplayTileOffset(offset);
    }

    @Override
    public int getDisplayBlockOffset() {
        return getHandle().getDisplayTileOffset();
    }

    @Override
    public EntityType getType() {
        return EntityType.MINECART;
    }
}
