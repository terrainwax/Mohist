package org.bukkit.entity;

/**
 * Represents a {@link Zombie} which was once a {@link Villager}.
 */
public interface ZombieVillager extends Zombie {

    /**
     * Sets the villager profession of this zombie.
     */
    @Override
    void setVillagerProfession(Villager.Profession profession);

    /**
     * Returns the villager profession of this zombie.
     *
     * @return the profession or null
     */
    @Override
    Villager.Profession getVillagerProfession();
}
