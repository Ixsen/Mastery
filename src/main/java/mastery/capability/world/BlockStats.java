package mastery.capability.world;

public class BlockStats {
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

    public static final class FACTORY {
        public static BlockStats createOpened() {
            return new BlockStats(true, false);
        }

        public static BlockStats createPlaced() {
            return new BlockStats(true, true);
        }

        public static BlockStats createFalse() {
            return new BlockStats(false, false);
        }
    }
}
