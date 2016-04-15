package productions.moo.gameoflife.activities;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import productions.moo.gameoflife.models.Cell;
import productions.moo.gameoflife.models.Grid;

public class LifeBoard extends View
{
	private Paint _paint;
	private Grid _grid;

	public LifeBoard (Context context)
	{
		this(context, null);
	}

	public LifeBoard (Context context, AttributeSet attrs)
	{
		this(context, attrs, 0);
	}

	public LifeBoard (Context context, AttributeSet attrs, int defStyleAttr)
	{
		super(context, attrs, defStyleAttr);

		_paint = new Paint();
		_paint.setColor(Color.GREEN);

		setGrid(new Grid());
	}

	public void setGrid (final Grid grid)
	{
		_grid = grid;
		invalidate();
	}

	public void update ()
	{
		if (_grid != null)
		{
			_grid.update();
			invalidate();
		}
	}

	@Override
	protected void onDraw (Canvas canvas)
	{
		super.onDraw(canvas);

		// Clear Canvas
		canvas.drawColor(Color.BLACK);

		if (_grid == null)
		{
			return;
		}

		int cellWidth = getWidth() / _grid.getWidth();
		int cellHeight = getHeight() / _grid.getHeight();
		int left = 0, top = 0;

		for (int y = 0; y < _grid.getHeight(); y++)
		{
			for (int x = 0; x < _grid.getWidth(); x++)
			{
				Cell cell = _grid.getCell(x, y);
				if (cell.isAlive())
				{
					canvas.drawRect(left, top, left + cellWidth, top + cellHeight, _paint);
				}

				left += cellWidth;
			}

			left = 0;
			top += cellHeight;
		}
	}
}
