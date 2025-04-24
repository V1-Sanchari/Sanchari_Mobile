package com.tourism.sanchari;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

public class MyBottomSheet extends BottomSheetDialogFragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.bottom_sheet_layout, container, false);

        Button viewTrips = view.findViewById(R.id.viewTrips);
        Button editProfile = view.findViewById(R.id.editProfile);

        viewTrips.setOnClickListener(v ->
                Toast.makeText(getContext(), "Trips Clicked!", Toast.LENGTH_SHORT).show());

        editProfile.setOnClickListener(v ->
                Toast.makeText(getContext(), "Edit Profile Clicked!", Toast.LENGTH_SHORT).show());

        return view;
    }
}
