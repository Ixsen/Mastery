package de.ixsen.minecraft.mastery.eventhandlers;

public enum ExperienceDictionary {

    ALCHEMY_THROW(3), ALCHEMY_DRINK(3), ALCHEMY_BREW(5),
    //
    ATHLETICS_JUMP(1), ATHLETICS_WATER(32), ATHLETICS_LAVA(40), ATHLETICS_RIDING(1), ATHLETICS_FLYING(
            2), ATHLETICS_WALKING(4), ATHLETICS_DISTANCE(60),
    //
    COMBAT_ENTITY_DAMAGED(1), COMBAT_PLAYER_DAMAGED(1),
    //
    HUSBANDRY_ENTITY_TAMED(10), HUSBANDRY_ENTITY_HARVESTED(3), HUSBANDRY_ENTITY_FED(1), HUSBANDRY_ANIMAL_CHILD_SPAWN(1),
    //
    SURVIVAL_DEATH(5), SURVIVAL_LOW_DAMAGE(1), SURVIVAL_MIDDLE_DAMAGE(3), SURVIVAL_HIGH_DAMAGE(5), SURVIVAL_EATING(5),
    //
    MINING_ORE(2), MINING_BLOCK(1);

    private int value;

    ExperienceDictionary(int value) {
        this.value = value;
    }

    public int getValue() {
        return this.value;
    }
}
