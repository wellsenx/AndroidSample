package com.wellsen.modernartui;

import android.app.DialogFragment;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private static SeekBar seekBar;
    private static final int MAX_PROGRESS = 360;
    private static TextView textView1, textView2, textView3, textView4, textView5, textView6;
    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.i(TAG, "Entered onCreate");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initializeVariables();
        setColors(0); //Initialize colors

        seekBar.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {
            public void onProgressChanged(SeekBar seekBar, int progressValue, boolean fromUser) {
                updateColor(progressValue);
            }

            public void onStartTrackingTouch(SeekBar seekBar) {
                // Not implemented
            }

            public void onStopTrackingTouch(SeekBar seekBar) {
                // Not implemented
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        switch (item.getItemId()) {
            case R.id.action_info:
                Log.i(TAG,"more information menu pressed, opening dialog");
                DialogFragment mDialog = AlertDialogFragment.newInstance();
                mDialog.show(getFragmentManager(), "Alert");
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void updateColor(int value) {
        float[] hsvColor = {0, 1, 1};
        // generate only hue component in range [0, 360),
        // leaving saturation and brightness maximum possible
        hsvColor[0] = 360f*value/MAX_PROGRESS;
        setColors(Color.HSVToColor(hsvColor)-Color.RED);
    }

    private void initializeVariables() {
        seekBar = (SeekBar) findViewById(R.id.seekBar);
        textView1 = (TextView) findViewById(R.id.textView1);
        textView2 = (TextView) findViewById(R.id.textView2);
        textView3 = (TextView) findViewById(R.id.textView3);
        textView4 = (TextView) findViewById(R.id.textView4);
        textView5 = (TextView) findViewById(R.id.textView5);
        textView6 = (TextView) findViewById(R.id.textView6);
    }

    private void setColors(int color) {
        textView1.setBackgroundColor(Color.RED+color);
        textView2.setBackgroundColor(Color.GREEN+color);
        textView3.setBackgroundColor(Color.BLUE+color);
        // White, Gray, and Black colors are static
        textView4.setBackgroundColor(Color.WHITE);
        textView5.setBackgroundColor(Color.GRAY);
        textView6.setBackgroundColor(Color.BLACK);
    }
}