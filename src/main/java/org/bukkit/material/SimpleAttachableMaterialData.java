package org.bukkit.material;

import org.bukkit.Material;
import org.bukkit.block.BlockFace;

/**
 * Simple utility class for attachable MaterialData subclasses
 */
public abstract class SimpleAttachableMaterialData extends MaterialData implements Attachable {

    /**
     * @param type the raw type id
     * @deprecated Magic value
     */
    
    public SimpleAttachableMaterialData(int type) {
        super(type);
    }

    public SimpleAttachableMaterialData(int type, BlockFace direction) {
        this(type);
        setFacingDirection(direction);
    }

    public SimpleAttachableMaterialData(Material type, BlockFace direction) {
        this(type);
        setFacingDirection(direction);
    }

    public SimpleAttachableMaterialData(Material type) {
        super(type);
    }

    /**
     * @param type the raw type id
     * @param data the raw data value
     * @deprecated Magic value
     */
    
    public SimpleAttachableMaterialData(int type, byte data) {
        super(type, data);
    }

    /**
     * @param type the type
     * @param data the raw data value
     * @deprecated Magic value
     */
    
    public SimpleAttachableMaterialData(Material type, byte data) {
        super(type, data);
    }

    @Override
    public BlockFace getFacing() {
        BlockFace attachedFace = getAttachedFace();
        return attachedFace == null ? null : attachedFace.getOppositeFace();
    }

    @Override
    public String toString() {
        return super.toString() + " facing " + getFacing();
    }

    @Override
    public SimpleAttachableMaterialData clone() {
        return (SimpleAttachableMaterialData) super.clone();
    }
}
