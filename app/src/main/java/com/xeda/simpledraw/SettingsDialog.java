package com.xeda.simpledraw;

import android.app.Activity;
import android.app.DialogFragment;
import android.content.res.Resources;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

/**
 * Created by syedaamir on 15-06-2017.
 */

public class SettingsDialog extends DialogFragment {
    /**
     * Create a new instance of SettingsDialog, providing "num"
     * as an argument.
     */
    private OnColorSelectedListener onColorSelectedListener;
    private GridLayout colorsGrid;
    

    public static int[] colors = {
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
            R.color.md_indigo_500,
            R.color.md_blue_grey_500,
            R.color.md_lime_700,
            R.color.md_deep_orange_500,
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
        setStyle(DialogFragment.STYLE_NO_TITLE, R.style.MyDialog);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_settings, container, false);
        colorsGrid = (GridLayout)v.findViewById(R.id.colors_grid);
        colorAssign(v);
        return v;
    }
    void brushSizeAssign(){

    }
    void colorAssign(final View view){
        for (int i=0; i< colors.length; i++) {
            Log.d("colorAssign", "  " + colors.length);
            ImageView v = new ImageView(view.getContext());
            Resources r = getResources();
            int px = (int)TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 10, r.getDisplayMetrics());
            GridLayout.LayoutParams lp = new GridLayout.LayoutParams(new ViewGroup.LayoutParams(5*px, 5*px));
            v.setImageResource(R.drawable.circle);
            lp.setMargins(px, px, px, px);
            v.setLayoutParams(lp);
            v.setTag(i);
            v.setColorFilter(getResources().getColor(colors[i]));
            v.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(view.getContext(), v.getTag().toString() , Toast.LENGTH_SHORT).show();
                    onColorSelectedListener.onColorSelected(Integer.valueOf(v.getTag().toString()));
                }
            });
            colorsGrid.addView(v);
        }
    }
    public interface OnColorSelectedListener {
        public void onColorSelected(int position);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        // This makes sure that the container activity has implemented
        // the callback interface. If not, it throws an exception
        try {
            onColorSelectedListener = (OnColorSelectedListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnColorSelectedListener");
        }
    }
}
