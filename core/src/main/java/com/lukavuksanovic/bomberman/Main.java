package com.lukavuksanovic.bomberman;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.OrthographicCamera;


import java.util.ArrayList;
import java.util.List;

public class Main extends Game {
    private static final int TILE_SIZE = 64;

    private SpriteBatch batch;
    private Texture indestructibleWallTexture;
    private Texture destructibleWallTexture;
    private Texture pathTexture;
    private OrthographicCamera camera;

    private List<String> mapLines;
    private int[][] map;

    @Override
    public void create() {
        batch = new SpriteBatch();
        camera = new OrthographicCamera();
        camera.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        indestructibleWallTexture = new Texture(Gdx.files.internal("indestructible.png"));
        destructibleWallTexture = new Texture(Gdx.files.internal("destructible.png"));
        pathTexture = new Texture(Gdx.files.internal("path.png"));

        loadMap("maps/map1.txt");
        int mapWidth = map[0].length * TILE_SIZE;
        int mapHeight = map.length * TILE_SIZE;
        camera.setToOrtho(false, mapWidth, mapHeight);
    }


    private void loadMap(String filePath) {
        FileHandle file = Gdx.files.internal(filePath);
        String[] mapLines = file.readString().split("\n");
        int height = mapLines.length;
        int width = mapLines[0].trim().length();
        map = new int[height][width];
        for (int y = 0; y < height; y++) {
            String line = mapLines[y].trim();
            for (int x = 0; x < width; x++) {
                map[y][x] = Character.getNumericValue(line.charAt(x));
            }
        }
    }





    @Override
    public void render() {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        for (int y = 0; y < map.length; y++) {
            for (int x = 0; x < map[y].length; x++) {
                int tile = map[y][x];
                float posX = x * TILE_SIZE;
                float posY = (map.length - 1 - y) * TILE_SIZE;

                if (tile == 1) batch.draw(indestructibleWallTexture, posX, posY, TILE_SIZE, TILE_SIZE);
                else if (tile == 2) batch.draw(destructibleWallTexture, posX, posY, TILE_SIZE, TILE_SIZE);
                else batch.draw(pathTexture, posX, posY, TILE_SIZE, TILE_SIZE);
            }
        }
        batch.end();
    }




    @Override
    public void dispose() {
        batch.dispose();
        indestructibleWallTexture.dispose();
        destructibleWallTexture.dispose();
        pathTexture.dispose();
    }
}
