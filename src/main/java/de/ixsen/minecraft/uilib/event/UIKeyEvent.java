package de.ixsen.minecraft.uilib.event;

import de.ixsen.minecraft.uilib.elements.core.GuiElement;

/**
 * Event is delivered for the typeable ui elements.
 *
 * @author Subaro
 */

public class UIKeyEvent extends UIEvent {
    private char typedChar;
    private int keyCode;

    public UIKeyEvent(GuiElement actuatorElement, char typedChar, int keyCode) {
        super(actuatorElement);
        this.typedChar = typedChar;
        this.keyCode = keyCode;
    }

    public char getTypedChar() {
        return this.typedChar;
    }

    public int getKeyCode() {
        return this.keyCode;
    }
}
