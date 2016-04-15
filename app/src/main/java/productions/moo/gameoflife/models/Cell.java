package productions.moo.gameoflife.models;

import java.util.ArrayList;
import java.util.List;

public class Cell
{
	private boolean _isAlive;
	private boolean _willBeAlive;

	private List<Cell> _neighbors;

	public Cell()
	{
		this(false);
	}

	public Cell(boolean alive)
	{
		_isAlive = alive;
		_neighbors = new ArrayList<>(8);
	}

	public boolean isAlive()
	{
		return _isAlive;
	}

	// TODO: Take this out later, we shouldn't really be setting this. It's only here for the code test.
	public void setAlive(boolean alive)
	{
		_isAlive = alive;
	}

	/**
	 * Since we need to calculate the next step for all cells based on the current state we need to
	 * break the update into two steps. In the first we will calculate the next value for each cell
	 * and then commit that value in the second step so that our cells don't get out of sync depending
	 * on the order in which we update them.
	 */
	public void preUpdate ()
	{
		/*
		 * 1. Any live cell with fewer than two live neighbors dies, as if caused by under population.
		 * 2. Any live cell with more than three live neighbors dies, as if by overcrowding.
		 * 3. Any live cell with two or three live neighbors lives on to the next generation.
		 * 4. Any dead cell with exactly three live neighbors becomes a live cell.
		 */

		int liveNeighbors = 0;
		for (Cell neighbor : _neighbors)
		{
			if (neighbor._isAlive)
			{
				liveNeighbors++;
			}
		}

		// Under populated / Over crowding.
		if (_isAlive)
		{
			if (liveNeighbors < 2 || liveNeighbors > 3)
			{
				_willBeAlive = false;
			}
			else
			{
				_willBeAlive = true;
			}
		}
		else if (liveNeighbors == 3)
		{
			_willBeAlive = true;
		}
	}

	public void update ()
	{
		_isAlive = _willBeAlive;
	}

	public void addNeighbor(final Cell neighbor)
	{
		_neighbors.add(neighbor);
	}

	public void setNeighbors(final List<Cell> neighbors)
	{
		_neighbors = neighbors;
	}
}
