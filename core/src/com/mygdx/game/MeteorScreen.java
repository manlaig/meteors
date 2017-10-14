package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

/**
 * Created by manlai on 10/12/2017.
 */


public class MeteorScreen implements Screen
{

    ExtendViewport viewport;
    ScreenViewport hudViewport;
    ShapeRenderer renderer;
    MeteorShower meteorShower;
    Player player;
    SpriteBatch batch;
    BitmapFont font;

    @Override
    public void hide() {
        renderer.dispose();
        batch.dispose();
        font.dispose();
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void render(float delta) {
        viewport.apply();

        //Gdx.gl.glClearColor(203, 141, 9, 1);
        Gdx.gl.glClearColor(203/255.0F, 141/255.0F, 9/255.0F, 1);
        Gdx.gl20.glClear(GL20.GL_COLOR_BUFFER_BIT);

        renderer.setProjectionMatrix(viewport.getCamera().combined);
        meteorShower.update(delta);
        player.update(delta);
        renderer.begin(ShapeRenderer.ShapeType.Filled);

        if(player.hitByMeteor(meteorShower))
        {
            meteorShower.reset();
        }

        meteorShower.render(renderer);
        player.render(renderer);
        renderer.end();

        hudViewport.apply();

        batch.begin();
        batch.setProjectionMatrix(hudViewport.getCamera().combined);
        font.draw(batch, "Score: " + meteorShower.score, hudViewport.getWorldWidth() - 100, hudViewport.getWorldHeight() - 20);
        font.draw(batch, "Top Score: " + meteorShower.topScore, hudViewport.getWorldWidth() - 100, hudViewport.getWorldHeight() - 40);
        font.draw(batch, "Deaths: " + player.deaths, 20, hudViewport.getWorldHeight() - 20);
        batch.end();

    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height, true);
        hudViewport.update(width, height, true);
        player.init();
        //meteor.init();
    }

    @Override
    public void show() {
        renderer = new ShapeRenderer();
        viewport = new ExtendViewport(Constants.WORLD_SIZE, Constants.WORLD_SIZE);
        meteorShower = new MeteorShower(viewport);
        player = new Player(viewport);
        batch = new SpriteBatch();
        font = new BitmapFont();
        hudViewport = new ScreenViewport();

        font.getData().setScale(1);
        font.getRegion().getTexture().setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
    }

    @Override
    public void dispose() {
        renderer.dispose();
        batch.dispose();
        font.dispose();
    }
}
