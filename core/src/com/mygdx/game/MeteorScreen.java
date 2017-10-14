package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.viewport.ExtendViewport;

/**
 * Created by manla on 10/12/2017.
 */


public class MeteorScreen implements Screen
{

    ExtendViewport viewport;
    ShapeRenderer renderer;
    MeteorShower meteorShower;
    Player player;

    @Override
    public void hide() {
        renderer.dispose();
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


    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height, true);
        player.init();
        //meteor.init();
    }

    @Override
    public void show() {
        renderer = new ShapeRenderer();
        viewport = new ExtendViewport(Constants.WORLD_SIZE, Constants.WORLD_SIZE);
        meteorShower = new MeteorShower(viewport);
        player = new Player(viewport);
    }

    @Override
    public void dispose() {
        renderer.dispose();
    }
}
