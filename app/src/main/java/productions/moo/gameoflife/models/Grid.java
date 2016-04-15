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
	public Grid ()
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

	public Grid (int width, int height)
	{
		_width = width;
		_height = height;

		_cells = new ArrayList<>(_width * _height);

		// Assign starting values
		Random rand = new Random(System.currentTimeMillis());
		for (int i = 0; i < _width * _height; i++)
		{
			_cells.add(new Cell(rand.nextBoolean()));
		}

		// Assign neighbors
		for (int x = 0; x < _width; x++)
		{
			for (int y = 0; y < _height; y++)
			{
				Cell target = getCell(x, y);

				// Neighbors are going to be all the cells around this one

				// X--
				// -O-
				// ---
				int neighborX = x - 1;
				int neighborY = y - 1;
				assignNeighbor(target, neighborX, neighborY);

				// -X-
				// -O-
				// ---
				neighborX = x;
				neighborY = y - 1;
				assignNeighbor(target, neighborX, neighborY);

				// --X
				// -O-
				// ---
				neighborX = x + 1;
				neighborY = y - 1;
				assignNeighbor(target, neighborX, neighborY);

				// ---
				// XO-
				// ---
				neighborX = x - 1;
				neighborY = y;
				assignNeighbor(target, neighborX, neighborY);

				// ---
				// -OX
				// ---
				neighborX = x + 1;
				neighborY = y;
				assignNeighbor(target, neighborX, neighborY);

				// ---
				// -O-
				// X--
				neighborX = x - 1;
				neighborY = y + 1;
				assignNeighbor(target, neighborX, neighborY);

				// ---
				// -O-
				// -X-
				neighborX = x;
				neighborY = y + 1;
				assignNeighbor(target, neighborX, neighborY);

				// ---
				// -O-
				// --X
				neighborX = x + 1;
				neighborY = y + 1;
				assignNeighbor(target, neighborX, neighborY);
			}
		}
	}

	private void assignNeighbor (Cell target, int x, int y)
	{
		if (x < 0 || y < 0)
		{
			return;
		}

		Cell neighbor = getCell(x, y);
		if(neighbor != null)
		{
			target.addNeighbor(neighbor);
		}
	}

	public Cell getCell (int x, int y)
	{
		return _cells.get(y * _width + x);
	}

	public int getWidth ()
	{
		return _width;
	}

	public int getHeight ()
	{
		return _height;
	}

	public List<Cell> getCells ()
	{
		return _cells;
	}

	public void update ()
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
