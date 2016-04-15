package productions.moo.gameoflife.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import productions.moo.gameoflife.R;
import productions.moo.gameoflife.models.Grid;

public class MainActivity extends AppCompatActivity implements View.OnClickListener
{
	private LifeBoard _board;
	private Button _button;

	@Override
	protected void onCreate (Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		_board = (LifeBoard)findViewById(R.id.board);

		_button = (Button)findViewById(R.id.button);
		_button.setOnClickListener(this);
	}

	@Override
	public void onClick (View v)
	{
		_board.update();
	}
}
