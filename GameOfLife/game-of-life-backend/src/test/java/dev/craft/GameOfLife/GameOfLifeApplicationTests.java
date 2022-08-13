package dev.craft.GameOfLife;

import dev.craft.GameOfLife.controller.GameAlgorithm;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class GameOfLifeApplicationTests {
	@Autowired
	private GameAlgorithm gameAlgorithm;

	@LocalServerPort
	private int port;

	@Test
	void contextLoads() throws Exception{
	}

	@Test
	void cellsWithLessThanTwoNeighboursDie() throws Exception{
		int[][] initialGrid = {{1,1,0}, {0,0,0}, {0,0,0}};
		int[][] postGameGrid = {{0,0,0}, {0,0,0}, {0,0,0}};
		int[][] response = gameAlgorithm.gameResults(initialGrid);
		Assertions.assertArrayEquals(postGameGrid, response);
	}
	@Test
	void cellWithTwoNeighboursLives() throws Exception{
		int[][] initialGrid = {{1,0,0}, {0,1,0}, {0,0,1}};
		int[][] postGameGrid = {{0,0,0}, {0,1,0}, {0,0,0}};
		int[][] response = gameAlgorithm.gameResults(initialGrid);
		Assertions.assertArrayEquals(postGameGrid, response);
	}
	@Test
	void cellWithMoreThanThreeNeighboursDies() throws Exception{
		int[][] initialGrid = {{1,1,1}, {0,1,0}, {0,1,0}};
		int[][] postGameGrid = {{1,1,1}, {0,0,0}, {0,0,0}};
		int[][] response = gameAlgorithm.gameResults(initialGrid);
		Assertions.assertArrayEquals(postGameGrid, response);
	}
	@Test
	void deadCellWithThreeNeighboursBirths() throws Exception{
		int[][] initialGrid = {{1,1,1}, {0,0,0}, {0,0,0}};
		int[][] postGameGrid = {{0,1,0}, {0,1,0}, {0,0,0}};
		int[][] response = gameAlgorithm.gameResults(initialGrid);
		Assertions.assertArrayEquals(postGameGrid, response);
	}
}
