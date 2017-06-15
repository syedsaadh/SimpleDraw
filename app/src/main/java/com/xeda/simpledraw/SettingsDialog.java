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
    private OnSettingsSelectedListener mCallback;
    private GridLayout colorsGrid;
    private LinearLayout brushSizesLayout;
    private ImageView eraser;
    public static int[] colors = {
            R.color.md_red_700,
            R.color.md_blue_700,
            R.color.md_teal_700,
            R.color.md_yellow_500,
            R.color.md_pink_700,
            R.color.md_light_blue_700,
            R.color.md_green_700,
            R.color.md_amber_500,
            R.color.md_purple_700,
            R.color.md_cyan_700,
            R.color.md_light_green_700,
            R.color.md_orange_500,
            R.color.md_indigo_500,
            R.color.md_blue_grey_500,
            R.color.md_lime_700,
            R.color.md_deep_orange_500,
    };

    public static int[] brushSizes = {
            20,
            30,
            40,
            50
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
        brushSizesLayout = (LinearLayout)v.findViewById(R.id.brush_size_layout);
        eraser = (ImageView)v.findViewById(R.id.eraser);
        eraser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCallback.onEraserSelected();
            }
        });
        colorAssign(v);
        brushSizeAssign(v);

        return v;
    }
    void brushSizeAssign(final View view){
        for (int i=0; i< brushSizes.length; i++) {
            ImageView v = new ImageView(view.getContext());
            Resources r = getResources();
            int margin = (int)TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 8, r.getDisplayMetrics());
            int px = (int)TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, brushSizes[i], r.getDisplayMetrics());
            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(px, px);
            v.setImageResource(R.drawable.circle);
            lp.setMargins(margin, 0, margin, 0);
            v.setLayoutParams(lp);
            v.setTag(i);
            v.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(view.getContext(), "Brush Selected" , Toast.LENGTH_SHORT).show();
                    mCallback.onBrushSelected(Integer.valueOf(v.getTag().toString()));
                }
            });
            brushSizesLayout.addView(v);
        }
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
                    Toast.makeText(view.getContext(), "Color Selected" , Toast.LENGTH_SHORT).show();
                    mCallback.onColorSelected(Integer.valueOf(v.getTag().toString()));
                }
            });
            colorsGrid.addView(v);
        }
    }
    public interface OnSettingsSelectedListener {
        public void onColorSelected(int position);
        public void onBrushSelected(int position);
        public void onEraserSelected();
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        // This makes sure that the container activity has implemented
        // the callback interface. If not, it throws an exception
        try {
            mCallback = (OnSettingsSelectedListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnSettingsSelectedListener");
        }
    }
}
