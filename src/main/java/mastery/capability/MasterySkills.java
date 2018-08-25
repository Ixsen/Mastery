package mastery.capability;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import mastery.capability.skillclasses.MasterySpec;

/**
 * @author Subaro
 */
public enum MasterySkills {

    // Alchemy
    ACLHEMY_0_FAST_WORKER(0, "Fast Worker", "Because of the thousand potions you crafted you got a lot faster.",
            "-1 sec to brew a potion", MasterySpec.ALCHEMY, 10),

    ACLHEMY_1_DOUBLE_POTION(1, "Double Potion",
            "You get accustomed to the potion effects and need less of them.",
            "50% chance to not consume the potion after use", MasterySpec.ALCHEMY, 40);

    @Getter
    private int id;
    @Getter
    private String name;
    @Getter
    private String description;
    @Getter
    private String effect;
    @Getter
    private MasterySpec mastery;
    @Getter
    private int requiredLevel;

    private MasterySkills(int id, String name, String description, String effect, MasterySpec mastery,
            int requiredLevel) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.effect = effect;
        this.mastery = mastery;
        this.requiredLevel = requiredLevel;
    }

    public static List<MasterySkills> getSkillsForMastery(MasterySpec spec) {
        List<MasterySkills> list = new ArrayList<>();
        for (MasterySkills masterySkills : MasterySkills.values()) {
            if (masterySkills.mastery == spec) {
                list.add(masterySkills);
            }
        }
        return list;
    }
}
