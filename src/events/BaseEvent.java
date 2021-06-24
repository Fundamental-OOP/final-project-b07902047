package events;

public abstract class BaseEvent {
    String name;

    public BaseEvent() {
        this.name = "Generic event";
    }

    @Override
    public String toString() {
        return this.name;
    }
}
