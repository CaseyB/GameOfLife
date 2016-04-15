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
		_cells.get(1).setAlive(false);
		_cells.get(2).setAlive(false);
		_cells.get(3).setAlive(false);
		_cells.get(4).setAlive(false);
		_cells.get(5).setAlive(false);
		_cells.get(6).setAlive(true);
		_cells.get(7).setAlive(false);

		// OOO...O.
		_cells.get( 8).setAlive(true);
		_cells.get( 9).setAlive(true);
		_cells.get(10).setAlive(true);
		_cells.get(11).setAlive(false);
		_cells.get(12).setAlive(false);
		_cells.get(13).setAlive(false);
		_cells.get(14).setAlive(true);
		_cells.get(15).setAlive(false);

		// ......O.
		_cells.get(16).setAlive(false);
		_cells.get(17).setAlive(false);
		_cells.get(18).setAlive(false);
		_cells.get(19).setAlive(false);
		_cells.get(20).setAlive(false);
		_cells.get(21).setAlive(false);
		_cells.get(22).setAlive(true);
		_cells.get(23).setAlive(false);

		// ........
		_cells.get(24).setAlive(false);
		_cells.get(25).setAlive(false);
		_cells.get(26).setAlive(false);
		_cells.get(27).setAlive(false);
		_cells.get(28).setAlive(false);
		_cells.get(29).setAlive(false);
		_cells.get(30).setAlive(false);
		_cells.get(31).setAlive(false);

		// ...OO...
		_cells.get(32).setAlive(false);
		_cells.get(33).setAlive(false);
		_cells.get(34).setAlive(false);
		_cells.get(35).setAlive(true);
		_cells.get(36).setAlive(true);
		_cells.get(37).setAlive(false);
		_cells.get(38).setAlive(false);
		_cells.get(39).setAlive(false);

		// ...OO...
		_cells.get(40).setAlive(false);
		_cells.get(41).setAlive(false);
		_cells.get(42).setAlive(false);
		_cells.get(43).setAlive(true);
		_cells.get(44).setAlive(true);
		_cells.get(45).setAlive(false);
		_cells.get(46).setAlive(false);
		_cells.get(47).setAlive(false);
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
		for (int y = 0; y < _height; y++)
		{
			for (int x = 0; x < _width; x++)
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
		if (x < 0 || y < 0 || x >= _width || y >= _height)
		{
			return;
		}

		Cell neighbor = getCell(x, y);
		if (neighbor != null)
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
