package dev.craft.GameOfLife.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/")
public class GameAlgorithm {
    @CrossOrigin
    @PostMapping("game")
    public int[][] gameResults(@RequestBody int[][] grid){
        int size = grid.length;
        int[][] copyArr = new int[size][size];
        for(int i = 0; i < size; i++){
            for(int j = 0; j < size; j++){
                copyArr[i][j] = grid[i][j];
            }
        }
        for(int i = 0; i < size; i++){
            for(int j = 0; j < size; j++){
                int liveAround = liveAround(copyArr, i, j);
                if(liveAround < 2) grid[i][j] = 0;
                if(grid[i][j] == 0 && liveAround == 3) grid[i][j] = 1;
                if(liveAround == 2 || liveAround == 3) continue;
                if(liveAround > 3) grid[i][j] = 0;
            }
        }
        return grid;
    }
    private int liveAround(int[][] grid, int row, int col){
        int live = 0;
        for(int i= -1; i <= 1; i++){
            for(int j = -1; j <=1; j++){
                if(i == 0 && j == 0) continue;
                try{
                    live += grid[row+i][col+j];
                }
                catch (ArrayIndexOutOfBoundsException e){
                    continue;
                }
            }
        }
        return live;
    }
}
