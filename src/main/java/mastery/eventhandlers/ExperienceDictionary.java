package mastery.eventhandlers;

public enum ExperienceDictionary {
    ALCHEMY_THROW(3), ALCHEMY_DRINK(3), ALCHEMY_BREW(5),
    //
    ATHLETICS_JUMP(1), ATHLETICS_WATER(32), ATHLETICS_LAVA(40), ATHLETICS_RIDING(1), ATHLETICS_FLYING(
            2), ATHLETICS_WALKING(4), ATHLETICS_DISTANCE(60),
    //
    COMBAT_ENTITY_DAMAGED(1), COMBAT_PLAYER_DAMAGED(1),
    //
    HUSBANDRY_ENTITY_TAMED(10), HUSBANDRY_ENTITY_HARVESTED(3), HUSBANDRY_ENTITY_FED(1), HUSBANDRY_ANIMAL_CHILD_SPAWN(1);

    public int getValue() {
        return this.value;
    }

    private int value = 1;

    ExperienceDictionary(int value) {
        this.value = value;
    }
}
