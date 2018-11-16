package ru.geekbrains.screen;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import ru.geekbrains.base.ActionListener;
import ru.geekbrains.base.Base2DScreen;
import ru.geekbrains.math.Rect;
import ru.geekbrains.sprite.Background;
import ru.geekbrains.sprite.ButtonExit;
import ru.geekbrains.sprite.ButtonPlay;
import ru.geekbrains.sprite.Star;

public class MenuScreen extends Base2DScreen implements ActionListener {
    private static final int STAR_COUNT = 256;
    private Game game;
    private Texture bgTexture;
    private Background background;
    private TextureAtlas textureAtlas;
    private Star[] stars;
    private ButtonExit buttonExit;
    private ButtonPlay buttonPlay;
    private Music startMusic;

    public MenuScreen(Game game) {
        this.game = game;
    }

    public void show() {
        super.show();
        this.bgTexture = new Texture("bg.png");
        this.background = new Background(new TextureRegion(this.bgTexture));
        this.textureAtlas = new TextureAtlas("menuAtlas.tpack");
        this.stars = new Star[256];

        for(int i = 0; i < this.stars.length; ++i) {
            this.stars[i] = new Star(this.textureAtlas);
        }

        this.buttonExit = new ButtonExit(this.textureAtlas, this);
        this.buttonPlay = new ButtonPlay(this.textureAtlas, this);
        startMusic = Gdx.audio.newMusic(Gdx.files.internal("sounds/start.mp3"));
        startMusic.setLooping(true);
        startMusic.setVolume(0.1f);
        startMusic.play();
    }

    public void render(float delta) {
        super.render(delta);
        this.update(delta);
        this.draw();
    }

    public void update(float delta) {
        for(int i = 0; i < this.stars.length; ++i) {
            this.stars[i].update(delta);
        }

    }

    public void draw() {
        Gdx.gl.glClearColor(0.128F, 0.53F, 0.9F, 1.0F);
        Gdx.gl.glClear(16384);
        this.batch.begin();
        this.background.draw(this.batch);

        for(int i = 0; i < this.stars.length; ++i) {
            this.stars[i].draw(this.batch);
        }

        this.buttonExit.draw(this.batch);
        this.buttonPlay.draw(this.batch);
        this.batch.end();
    }

    public void resize(Rect worldBounds) {
        this.background.resize(worldBounds);

        for(int i = 0; i < this.stars.length; ++i) {
            this.stars[i].resize(worldBounds);
        }

        this.buttonExit.resize(worldBounds);
        this.buttonPlay.resize(worldBounds);
    }

    public void dispose() {
        this.bgTexture.dispose();
        this.textureAtlas.dispose();
        this.startMusic.dispose();
        super.dispose();
    }

    public boolean touchDown(Vector2 touch, int pointer) {
        this.buttonExit.touchDown(touch, pointer);
        this.buttonPlay.touchDown(touch, pointer);
        return false;
    }

    public boolean touchUp(Vector2 touch, int pointer) {
        this.buttonExit.touchUp(touch, pointer);
        this.buttonPlay.touchUp(touch, pointer);
        return super.touchUp(touch, pointer);
    }

    public void actionPerformed(Object src) {
        if (src == this.buttonExit) {
            Gdx.app.exit();
        } else if (src == this.buttonPlay) {
            this.game.setScreen(new GameScreen());
        }

    }
}
