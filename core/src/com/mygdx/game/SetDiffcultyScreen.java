package com.mygdx.game;

import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

/**
 * Created by manlai on 10/13/2017.
 */

public class SetDiffcultyScreen extends InputAdapter implements Screen {

    ShapeRenderer renderer;
    SpriteBatch batch;
    BitmapFont font;
    MyGdxGame game;

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        game.setScreen(new MeteorScreen());
        return true;
    }

    @Override
    public void show() {
        renderer = new ShapeRenderer();
        game = new MyGdxGame();
        batch = new SpriteBatch();
        font = new BitmapFont();
    }

    @Override
    public void render(float delta) {



    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void dispose() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void pause() {

    }
}
