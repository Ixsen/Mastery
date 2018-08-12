package mastery.eventsystem;

/**
 * Main event for every Event used by the Mastery Event
 * 
 * @author Subaro
 */
public class MasteryEvent {

    private MasteryEventType _type;
    private Object source;
    private Object target;

    public MasteryEvent(MasteryEventType _type, Object source, Object target) {
        this._type = _type;
        this.source = source;
        this.target = target;
    }

    public MasteryEventType getType() {
        return this._type;
    }

    public Object getSource() {
        return this.source;
    }

    public Object getTarget() {
        return this.target;
    }
}
