package productions.moo.gameoflife.models;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Grid
{
	private int _width, _height;
	private List<Cell> _cells;

	// NOTE: For the case of this code test we're going to define a default constructor that matches
	// the requirements
	public Grid()
	{
		this(8, 6);

		// Go through and reset the cells to match the test state
		// ......O.
		_cells.get(0).setAlive(false);
		_cells.get(0).setAlive(false);
		_cells.get(0).setAlive(false);
		_cells.get(0).setAlive(false);
		_cells.get(0).setAlive(false);
		_cells.get(0).setAlive(false);
		_cells.get(0).setAlive(true);
		_cells.get(0).setAlive(false);

		// OOO...O.
		_cells.get(0).setAlive(true);
		_cells.get(0).setAlive(true);
		_cells.get(0).setAlive(true);
		_cells.get(0).setAlive(false);
		_cells.get(0).setAlive(false);
		_cells.get(0).setAlive(false);
		_cells.get(0).setAlive(true);
		_cells.get(0).setAlive(false);

		// ......O.
		_cells.get(0).setAlive(false);
		_cells.get(0).setAlive(false);
		_cells.get(0).setAlive(false);
		_cells.get(0).setAlive(false);
		_cells.get(0).setAlive(false);
		_cells.get(0).setAlive(false);
		_cells.get(0).setAlive(true);
		_cells.get(0).setAlive(false);

		// ........
		_cells.get(0).setAlive(false);
		_cells.get(0).setAlive(false);
		_cells.get(0).setAlive(false);
		_cells.get(0).setAlive(false);
		_cells.get(0).setAlive(false);
		_cells.get(0).setAlive(false);
		_cells.get(0).setAlive(false);
		_cells.get(0).setAlive(false);

		// ...OO...
		_cells.get(0).setAlive(false);
		_cells.get(0).setAlive(false);
		_cells.get(0).setAlive(false);
		_cells.get(0).setAlive(true);
		_cells.get(0).setAlive(true);
		_cells.get(0).setAlive(false);
		_cells.get(0).setAlive(false);
		_cells.get(0).setAlive(false);

		// ...OO...
		_cells.get(0).setAlive(false);
		_cells.get(0).setAlive(false);
		_cells.get(0).setAlive(false);
		_cells.get(0).setAlive(true);
		_cells.get(0).setAlive(true);
		_cells.get(0).setAlive(false);
		_cells.get(0).setAlive(false);
		_cells.get(0).setAlive(false);
	}

	public Grid(int width, int height)
	{
		_width = width;
		_height = height;

		_cells = new ArrayList<>(_width * _height);

		Random rand = new Random(System.currentTimeMillis());
		for (int i = 0; i < _width * _height; i++)
		{
			_cells.add(new Cell(rand.nextBoolean()));
		}
	}

	public void update()
	{
		for (Cell cell : _cells)
		{
			cell.preUpdate();
		}

		for (Cell cell : _cells)
		{
			cell.update();
		}
	}
}
