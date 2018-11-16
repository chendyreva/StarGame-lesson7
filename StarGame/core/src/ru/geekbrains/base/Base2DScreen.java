package ru.geekbrains.base;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Matrix3;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Vector2;
import ru.geekbrains.math.MatrixUtils;
import ru.geekbrains.math.Rect;

public class Base2DScreen implements Screen, InputProcessor {
    protected SpriteBatch batch;
    private Rect screenBounds;
    protected Rect worldBounds;
    private Rect glBounds;
    protected Matrix4 worldToGl;
    protected Matrix3 screenToWorld;
    private Vector2 touch;

    public Base2DScreen() {
    }

    public void show() {
        System.out.println("show");
        this.batch = new SpriteBatch();
        Gdx.input.setInputProcessor(this);
        this.screenBounds = new Rect();
        this.worldBounds = new Rect();
        this.glBounds = new Rect(0.0F, 0.0F, 1.0F, 1.0F);
        this.worldToGl = new Matrix4();
        this.screenToWorld = new Matrix3();
        this.touch = new Vector2();
    }

    public void render(float delta) {
    }

    public void resize(int width, int height) {
        System.out.println("resize w = " + width + " h = " + height);
        this.screenBounds.setSize((float)width, (float)height);
        this.screenBounds.setLeft(0.0F);
        this.screenBounds.setBottom(0.0F);
        float aspect = (float)width / (float)height;
        this.worldBounds.setHeight(1.0F);
        this.worldBounds.setWidth(1.0F * aspect);
        MatrixUtils.calcTransitionMatrix(this.worldToGl, this.worldBounds, this.glBounds);
        this.batch.setProjectionMatrix(this.worldToGl);
        MatrixUtils.calcTransitionMatrix(this.screenToWorld, this.screenBounds, this.worldBounds);
        this.resize(this.worldBounds);
    }

    public void resize(Rect worldBounds) {
    }

    public void pause() {
        System.out.println("pause");
    }

    public void resume() {
        System.out.println("resume");
    }

    public void hide() {
        System.out.println("hide");
        this.dispose();
    }

    public void dispose() {
        System.out.println("dispose");
        this.batch.dispose();
    }

    public boolean keyDown(int keycode) {
        System.out.println("keyDown keycode = " + keycode);
        return false;
    }

    public boolean keyUp(int keycode) {
        System.out.println("keyUp keycode = " + keycode);
        return false;
    }

    public boolean keyTyped(char character) {
        System.out.println("keyTyped character = " + character);
        return false;
    }

    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        this.touch.set((float)screenX, this.screenBounds.getHeight() - (float)screenY).mul(this.screenToWorld);
        this.touchDown(this.touch, pointer);
        return false;
    }

    public boolean touchDown(Vector2 touch, int pointer) {
        System.out.println("touchDown touch.x = " + touch.x + " touch.y = " + touch.y);
        return false;
    }

    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        this.touch.set((float)screenX, this.screenBounds.getHeight() - (float)screenY).mul(this.screenToWorld);
        this.touchUp(this.touch, pointer);
        return false;
    }

    public boolean touchUp(Vector2 touch, int pointer) {
        System.out.println("touchUp touch.x = " + touch.x + " touch.y = " + touch.y);
        return false;
    }

    public boolean touchDragged(int screenX, int screenY, int pointer) {
        System.out.println("touchDragged screenX = " + screenX + " screenY = " + screenY);
        return false;
    }

    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    public boolean scrolled(int amount) {
        System.out.println("scrolled");
        return false;
    }
}
