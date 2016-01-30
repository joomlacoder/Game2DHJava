package xyz.ignatyev.game.level;

import xyz.ignatyev.game.Game;
import xyz.ignatyev.graphics.TextureAtlas;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Andrej on 30.01.2016.
 */
public class Level {

    public static final int TILE_SCALE = 8;
    public static final int TILE_IN_GAME_SCALE = 2;
    public static final int SCALED_TILE_SIZE = TILE_SCALE * TILE_IN_GAME_SCALE;
    public static final int TILE_IN_WIDTH = Game.WEIDTH/SCALED_TILE_SIZE;
    public static final int TILE_IN_HEIGHT = Game.HIGHT/SCALED_TILE_SIZE;

    private int[][] tileMap;
    private Map<TileType, Tile> tiles;

    public Level(TextureAtlas atlas){
        tileMap = new int[TILE_IN_WIDTH][TILE_IN_HEIGHT];
        tiles  = new HashMap<TileType, Tile>();
        tiles.put(TileType.BRICK, new Tile(atlas.cut(32 * TILE_SCALE, 0 * TILE_SCALE, TILE_SCALE, TILE_SCALE), TILE_IN_GAME_SCALE, TileType.BRICK));
        tiles.put(TileType.METAL, new Tile(atlas.cut(32 * TILE_SCALE, 2 * TILE_SCALE, TILE_SCALE, TILE_SCALE), TILE_IN_GAME_SCALE, TileType.METAL));
        tiles.put(TileType.WATER, new Tile(atlas.cut(32 * TILE_SCALE, 4 * TILE_SCALE, TILE_SCALE, TILE_SCALE), TILE_IN_GAME_SCALE, TileType.WATER));
        tiles.put(TileType.GRASS, new Tile(atlas.cut(34 * TILE_SCALE, 2 * TILE_SCALE, TILE_SCALE, TILE_SCALE), TILE_IN_GAME_SCALE, TileType.GRASS));
        tiles.put(TileType.ICE, new Tile(atlas.cut(36 * TILE_SCALE, 4 * TILE_SCALE, TILE_SCALE, TILE_SCALE), TILE_IN_GAME_SCALE, TileType.ICE));
        tiles.put(TileType.EMPTY, new Tile(atlas.cut(36 * TILE_SCALE, 6 * TILE_SCALE, TILE_SCALE, TILE_SCALE), TILE_IN_GAME_SCALE, TileType.EMPTY));

        tileMap[10][10] = TileType.BRICK.numeric();
    }

    public void update(){}

    public void render(Graphics2D g){
        for (int i = 0; i < tileMap.length; i++)
            for (int j = 0; j < tileMap[i].length; j++)
                tiles.get(TileType.fromNumeric(tileMap[i][j])).render(g, j * SCALED_TILE_SIZE, i * SCALED_TILE_SIZE);
    }

}
