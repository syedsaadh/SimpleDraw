package com.xeda.simpledraw;

import android.app.DialogFragment;
import android.content.res.Resources;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

/**
 * Created by syedaamir on 15-06-2017.
 */

public class SettingsDialog extends DialogFragment {
    /**
     * Create a new instance of SettingsDialog, providing "num"
     * as an argument.
     */
    private GridLayout colorsGrid;
    private static int[] colors = {
            R.color.md_red_700,
            R.color.md_blue_700,
            R.color.md_teal_700,
            R.color.md_yellow_700,
            R.color.md_pink_700,
            R.color.md_light_blue_700,
            R.color.md_green_700,
            R.color.md_amber_700,
            R.color.md_purple_700,
            R.color.md_cyan_700,
            R.color.md_light_green_700,
            R.color.md_orange_500,
            R.color.md_indigo_700,
            R.color.md_blue_grey_500,
            R.color.md_lime_700,
            R.color.md_deep_orange_600
    };
    static SettingsDialog newInstance() {
        SettingsDialog f = new SettingsDialog();

        // Supply num input as an argument.
//        Bundle args = new Bundle();
//        args.putInt("num", num);
//        f.setArguments(args);

        return f;
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(DialogFragment.STYLE_NO_TITLE, android.R.style.Theme_Holo_Light_Dialog);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_settings, container, false);
        colorsGrid = (GridLayout)v.findViewById(R.id.colors_grid);
        colorAssign();
        return v;
    }
    void colorAssign(){
        Integer counts = colorsGrid.getChildCount();
        for (int i=0; i< counts; i++) {
            ImageView v = (ImageView)colorsGrid.getChildAt(i);
            Resources r = getResources();
            int px = (int)TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 8, r.getDisplayMetrics());
            GridLayout.LayoutParams lp = new GridLayout.LayoutParams(v.getLayoutParams());
            lp.setMargins(px, px, px, px);
            v.setLayoutParams(lp);
            v.setColorFilter(getResources().getColor(colors[i]));
        }
    }
}
