package ru.geekbrains.base;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import ru.geekbrains.math.Rect;
import ru.geekbrains.pool.BulletPool;
import ru.geekbrains.pool.ExplosionPool;
import ru.geekbrains.sprite.Bullet;
import ru.geekbrains.sprite.Explosion;

public abstract class Ship extends Sprite {
    protected Vector2 v = new Vector2();
    protected BulletPool bulletPool;
    protected ExplosionPool explosionPool;
    protected Rect worldBounds;
    protected Vector2 bulletV = new Vector2();
    protected float bulletHeight;
    protected int bulletDamage;
    protected float reloadInterval;
    protected float reloadTimer;
    protected float damageAnimateInterval = 0.1F;
    protected float damageAnimateTimer;
    protected int hp;
    protected TextureRegion bulletRegion;
    private Sound shootSound;

    public Ship(TextureRegion region, int rows, int cols, int frames, Sound shootSound) {
        super(region, rows, cols, frames);
        this.shootSound = shootSound;
    }

    public Ship(Sound shootSound) {
        this.shootSound = shootSound;
    }

    public void resize(Rect worldBounds) {
        this.worldBounds = worldBounds;
    }

    protected void shoot() {
        Bullet bullet = (Bullet)this.bulletPool.obtain();
        bullet.set(this, this.bulletRegion, this.pos, this.bulletV, this.bulletHeight, this.worldBounds, this.bulletDamage);
        this.shootSound.play();
    }

    public void update(float delta) {
        super.update(delta);
        this.damageAnimateTimer += delta;
        if (this.damageAnimateTimer >= this.damageAnimateInterval) {
            this.frame = 0;
        }

    }

    public void boom() {
        Explosion explosion = (Explosion)this.explosionPool.obtain();
        explosion.set(this.getHeight(), this.pos);
    }

    public void damage(int damage) {
        this.frame = 1;
        this.damageAnimateTimer = 0.0F;
        this.hp -= damage;
        if (this.hp <= 0) {
            this.destroy();
        }

    }
}
