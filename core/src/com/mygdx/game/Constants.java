package com.mygdx.game;

import com.badlogic.gdx.math.Vector2;

/**
 * Created by manlai on 10/12/2017.
 */

public class Constants
{
    public static final float WORLD_SIZE = 10.0F;
    public static final float METEOR_ACCELERATION = -9.8f;
    public static final float PLAYER_VELOCITY = 10.0F;
    public static final float METEOR_RADIUS = 0.15f;

    public static final float DIFFICULTY_EASY = 5.0f;
    public static final float DIFFUCULTY_MEDIUM = 10.0f;
    public static final float DIFFICULTY_HARD = 20.0f;

    public static final float RADIUS_CIRCLE_DIFFICULTY = 80.0f;
    public static final float DIFFICULTY_WORLD_SIZE = 580.0f;
    public static final float DIFFICULTY_BUBBLE_RADIUS = DIFFICULTY_WORLD_SIZE / 9;

    public static final Vector2 EASY_CENTER = new Vector2(DIFFICULTY_WORLD_SIZE / 4, DIFFICULTY_WORLD_SIZE / 2);
    public static final Vector2 MEDIUM_CENTER = new Vector2(DIFFICULTY_WORLD_SIZE / 2, DIFFICULTY_WORLD_SIZE / 2);
    public static final Vector2 HARD_CENTER = new Vector2(DIFFICULTY_WORLD_SIZE * 3 / 4, DIFFICULTY_WORLD_SIZE / 2);
}
