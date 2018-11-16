package ru.geekbrains.sprite;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import ru.geekbrains.base.Sprite;

public class Explosion extends Sprite {
    private Sound sound;
    private float anumateInterval = 0.017F;
    private float animateTimer;

    public Explosion(TextureRegion region, int rows, int cols, int frames, Sound sound) {
        super(region, rows, cols, frames);
        this.sound = sound;
    }

    public void set(float height, Vector2 pos) {
        this.pos.set(pos);
        this.setHeightProportion(height);
        this.sound.play();
    }

    public void update(float delta) {
        this.animateTimer += delta;
        if (this.animateTimer >= this.anumateInterval) {
            this.animateTimer = 0.0F;
            if (++this.frame == this.regions.length) {
                this.destroy();
            }
        }

    }

    public void destroy() {
        this.frame = 0;
        super.destroy();
    }
}
