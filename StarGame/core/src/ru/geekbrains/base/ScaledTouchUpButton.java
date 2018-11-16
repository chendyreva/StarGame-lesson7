package ru.geekbrains.base;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

public class ScaledTouchUpButton extends Sprite {
    private static final float PRESS_SCALE = 0.9F;
    private int pointer;
    private boolean isPressed;
    private ActionListener actionListener;

    public ScaledTouchUpButton(TextureRegion region, ActionListener actionListener) {
        super(region);
        this.actionListener = actionListener;
        this.setHeightProportion(0.15F);
    }

    public boolean touchDown(Vector2 touch, int pointer) {
        if (!this.isPressed && this.isMe(touch)) {
            this.pointer = pointer;
            this.scale = 0.9F;
            this.isPressed = true;
            return false;
        } else {
            return false;
        }
    }

    public boolean touchUp(Vector2 touch, int pointer) {
        if (this.pointer == pointer && this.isPressed) {
            if (this.isMe(touch)) {
                this.actionListener.actionPerformed(this);
            }

            this.isPressed = false;
            this.scale = 1.0F;
            return false;
        } else {
            return false;
        }
    }
}
