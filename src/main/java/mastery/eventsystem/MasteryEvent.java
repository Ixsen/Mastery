/**
 * 
 */
package mastery.eventsystem;

/**
 * Main event for every Event used by the Mastery Event
 * 
 * @author Subaro
 */
public class MasteryEvent {

    protected MasteryEventType _type;
    protected Object source;
    protected Object target;

    public MasteryEvent(MasteryEventType _type, Object source, Object target) {
        this._type = _type;
        this.source = source;
        this.target = target;
    }

    public MasteryEventType getType() {
        return _type;
    }

    public Object getSource() {
        return source;
    }

    public Object getTarget() {
        return target;
    }
}
