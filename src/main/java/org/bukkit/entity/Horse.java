package org.bukkit.entity;

import org.bukkit.inventory.HorseInventory;

/**
 * Represents a Horse.
 */
public interface Horse extends AbstractHorse {

    /**
     * @deprecated different variants are differing classes
     */
    @Deprecated
    enum Variant {
        /**
         * A normal horse
         */
        HORSE,
        /**
         * A donkey
         */
        DONKEY,
        /**
         * A mule
         */
        MULE,
        /**
         * An undead horse
         */
        UNDEAD_HORSE,
        /**
         * A skeleton horse
         */
        SKELETON_HORSE,
        /**
         * Not really a horse :)
         */
        LLAMA
    }

    /**
     * Represents the base color that the horse has.
     */
    enum Color {
        /**
         * Snow white
         */
        WHITE,
        /**
         * Very light brown
         */
        CREAMY,
        /**
         * Chestnut
         */
        CHESTNUT,
        /**
         * Light brown
         */
        BROWN,
        /**
         * Pitch black
         */
        BLACK,
        /**
         * Gray
         */
        GRAY,
        /**
         * Dark brown
         */
        DARK_BROWN,
        ;
    }

    /**
     * Represents the style, or markings, that the horse has.
     */
    enum Style {
        /**
         * No markings
         */
        NONE,
        /**
         * White socks or stripes
         */
        WHITE,
        /**
         * Milky splotches
         */
        WHITEFIELD,
        /**
         * Round white dots
         */
        WHITE_DOTS,
        /**
         * Small black dots
         */
        BLACK_DOTS,
        ;
    }

    /**
     * Gets the horse's color.
     * <p>
     * Colors only apply to horses, not to donkeys, mules, skeleton horses
     * or undead horses.
     *
     * @return a {@link Color} representing the horse's group
     */
    Color getColor();

    /**
     * Sets the horse's color.
     * <p>
     * Attempting to set a color for any donkey, mule, skeleton horse or
     * undead horse will not result in a change.
     *
     * @param color a {@link Color} for this horse
     */
    void setColor(Color color);

    /**
     * Gets the horse's style.
     * Styles determine what kind of markings or patterns a horse has.
     * <p>
     * Styles only apply to horses, not to donkeys, mules, skeleton horses
     * or undead horses.
     *
     * @return a {@link Style} representing the horse's style
     */
    Style getStyle();

    /**
     * Sets the style of this horse.
     * Styles determine what kind of markings or patterns a horse has.
     * <p>
     * Attempting to set a style for any donkey, mule, skeleton horse or
     * undead horse will not result in a change.
     *
     * @param style a {@link Style} for this horse
     */
    void setStyle(Style style);

    /**
     * @return carrying chest status
     * @deprecated see {@link ChestedHorse}
     */
    @Deprecated
    boolean isCarryingChest();

    /**
     * @param chest
     * @deprecated see {@link ChestedHorse}
     */
    @Deprecated
    void setCarryingChest(boolean chest);

    @Override
    HorseInventory getInventory();
}
