package mastery.experience;

/**
 * Created by Granis on 16/03/2018.
 */
public class Mastery implements IMastery {

    private int miningMastery;

    public Mastery() {
        this.miningMastery = 0;
    }

    public int getMiningMastery() {
        return this.miningMastery;
    }

    public void setMiningMastery(int miningMastery) {
        this.miningMastery = miningMastery;
    }

}
