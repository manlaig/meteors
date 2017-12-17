package com.mygdx.game;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
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

public class MeteorScreen extends InputAdapter implements Screen
{
    private ExtendViewport mainViewport;
    private ScreenViewport hudViewport;
    private ShapeRenderer renderer;
    private MeteorShower meteorShower;
    private Player player;
    private SpriteBatch batch;
    private BitmapFont font;
    private MyGdxGame game;
    private float difficulty;

    public MeteorScreen(MyGdxGame game, float difficulty)
    {
        this.game = game;
        this.difficulty = difficulty;
    }

    @Override
    public void render(float delta)
    {
        mainViewport.apply();
        hudViewport.apply();
        Gdx.gl.glClearColor(203/255.0F, 141/255.0F, 9/255.0F, 1);
        Gdx.gl20.glClear(GL20.GL_COLOR_BUFFER_BIT);
        renderer.setProjectionMatrix(mainViewport.getCamera().combined);
        renderer.begin(ShapeRenderer.ShapeType.Filled);

        meteorShower.update(delta);
        player.updatePlayerPosition(delta);

        if(player.hitByMeteor(meteorShower))
            meteorShower.reset();

        meteorShower.render(renderer);
        player.renderPlayer(renderer);

        renderer.end();

        batch.begin();
        batch.setProjectionMatrix(hudViewport.getCamera().combined);

        if(Gdx.app.getType() == Application.ApplicationType.Android)
        {
            font.draw(batch, "Score: " + meteorShower.getScore(), hudViewport.getWorldWidth() - 200, hudViewport.getWorldHeight() - 30);
            font.draw(batch, "Top Score: " + meteorShower.getTopScore(), hudViewport.getWorldWidth() - 200, hudViewport.getWorldHeight() - 60);
            font.draw(batch, "Deaths: " + player.getDeaths(), 20, hudViewport.getWorldHeight() - 30);
        }
        else
        {
            font.draw(batch, "Score: " + meteorShower.getScore(), hudViewport.getWorldWidth() - 100, hudViewport.getWorldHeight() - 20);
            font.draw(batch, "Top Score: " + meteorShower.getTopScore(), hudViewport.getWorldWidth() - 100, hudViewport.getWorldHeight() - 40);
            font.draw(batch, "Deaths: " + player.getDeaths(), 20, hudViewport.getWorldHeight() - 20);
        }
        batch.end();
    }

    @Override
    public void resize(int width, int height)
    {
        mainViewport.update(width, height, true);
        hudViewport.update(width, height, true);
        player.init();
    }

    @Override
    public void show()
    {
        initializeAllVariables();

        if(Gdx.app.getType() == Application.ApplicationType.Android)    //the text should be bigger on Android
            font.getData().setScale(2);
        else
            font.getData().setScale(1);

        font.getRegion().getTexture().setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
    }

    private void initializeAllVariables()
    {
        renderer = new ShapeRenderer();
        mainViewport = new ExtendViewport(Constants.WORLD_SIZE, Constants.WORLD_SIZE);
        meteorShower = new MeteorShower(mainViewport, difficulty);
        player = new Player(mainViewport);
        batch = new SpriteBatch();
        font = new BitmapFont();
        hudViewport = new ScreenViewport();
        Gdx.input.setInputProcessor(this);
    }

    @Override
    public void dispose()
    {
        renderer.dispose();
        batch.dispose();
        font.dispose();
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button)
    {
        game.setScreen(new SetDifficultyScreen(game));
        return true;
    }

    @Override
    public void hide()
    {
        renderer.dispose();
        batch.dispose();
        font.dispose();
    }

    @Override
    public void pause()
    {
    }
    @Override
    public void resume()
    {
    }
}
