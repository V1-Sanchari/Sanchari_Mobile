package com.tourism.sanchari;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.google.android.material.chip.Chip;
import com.google.android.material.slider.RangeSlider;
import com.google.android.material.slider.Slider;

public class LocationUpdate extends BottomSheetDialogFragment{

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_location_update, container, false); // ✅ Inflate first

        Chip costom=view.findViewById(R.id.costom);
        Slider seekBar=view.findViewById(R.id.slider);
        TextView value1=view.findViewById(R.id.value);
        seekBar.setVisibility(view.GONE);
        value1.setVisibility(view.GONE);

        costom.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b==true){
                    seekBar.setVisibility(view.VISIBLE);
                    value1.setVisibility(view.VISIBLE);

                    seekBar.addOnChangeListener(new Slider.OnChangeListener() {
                        @Override
                        public void onValueChange(@NonNull Slider slider, float value, boolean fromUser) {
                            value1.setText("Radius in KM : "+value);
                        }
                    });
                }else{
                    seekBar.setVisibility(view.GONE);
                    value1.setVisibility(view.GONE);
                }

            }
        });


        return view;
    }
}
