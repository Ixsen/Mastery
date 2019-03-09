package mastery.capability.world;

public class BlockStats {

    public static final BlockStatsFactory FACTORY = new BlockStatsFactory();

    private boolean wasOpened;
    private boolean placedByPlayer;

    public BlockStats(boolean wasOpened, boolean placedByPlayer) {
        this.wasOpened = wasOpened;
        this.placedByPlayer = placedByPlayer;
    }

    public boolean wasOpened() {
        return this.wasOpened;
    }

    public void setWasOpened(boolean opened) {
        this.wasOpened = opened;
    }

    public boolean isPlacedByPlayer() {
        return this.placedByPlayer;
    }

    public void setPlacedByPlayer(boolean placedByPlayer) {
        this.placedByPlayer = placedByPlayer;
    }

    @Override
    public String toString() {
        return String.format("Opened: %b - Placed by player: %b", this.wasOpened, this.placedByPlayer);
    }

    public static class BlockStatsFactory {
        public BlockStats createPlayerOpened() {
            return new BlockStats(true, false);
        }

        public BlockStats createPlayerPlaced() {
            return new BlockStats(true, true);
        }

        public BlockStats createNoPreviousInteraction() {
            return new BlockStats(false, false);
        }
    }
}
