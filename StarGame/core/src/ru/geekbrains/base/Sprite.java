package ru.geekbrains.base;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import ru.geekbrains.math.Rect;
import ru.geekbrains.utils.Regions;

public class Sprite extends Rect {
    protected float angle;
    protected float scale = 1.0F;
    protected TextureRegion[] regions;
    protected int frame;
    protected boolean isDestroyed;

    public Sprite() {
    }

    public Sprite(TextureRegion region) {
        if (region == null) {
            throw new NullPointerException("region is null");
        } else {
            this.regions = new TextureRegion[1];
            this.regions[0] = region;
        }
    }

    public Sprite(TextureRegion region, int rows, int cols, int frames) {
        this.regions = Regions.split(region, rows, cols, frames);
    }

    public void draw(SpriteBatch batch) {
        batch.draw(this.regions[this.frame], this.getLeft(), this.getBottom(), this.halfWidth, this.halfHeight, this.getWidth(), this.getHeight(), this.scale, this.scale, this.angle);
    }

    public void setHeightProportion(float height) {
        this.setHeight(height);
        float aspect = (float)this.regions[this.frame].getRegionWidth() / (float)this.regions[this.frame].getRegionHeight();
        this.setWidth(height * aspect);
    }

    public void update(float delta) {
    }

    public void resize(Rect worldBounds) {
    }

    public boolean touchDown(Vector2 touch, int pointer) {
        return false;
    }

    public boolean touchUp(Vector2 touch, int pointer) {
        return false;
    }

    public float getAngle() {
        return this.angle;
    }

    public void setAngle(float angle) {
        this.angle = angle;
    }

    public float getScale() {
        return this.scale;
    }

    public void setScale(float scale) {
        this.scale = scale;
    }

    public void destroy() {
        this.isDestroyed = true;
    }

    public void flushDestroy() {
        this.isDestroyed = false;
    }

    public boolean isDestroyed() {
        return this.isDestroyed;
    }
}
