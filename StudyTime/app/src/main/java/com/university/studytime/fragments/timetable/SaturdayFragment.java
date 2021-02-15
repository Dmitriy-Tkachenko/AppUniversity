package com.university.studytime.fragments.timetable;

import android.os.Bundle;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.university.studytime.R;
import com.university.studytime.adapter.TimetableAdapter;
import com.university.studytime.models.TimetableModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class SaturdayFragment extends Fragment {
    private static final String KEY = null;
    // Нужно для передачи параметра при создании фрагмента
    public static SaturdayFragment newInstance(List<TimetableModel> list) {
        Bundle args = new Bundle();
        SaturdayFragment fragment = new SaturdayFragment();
        args.putParcelableArrayList(KEY, (ArrayList<? extends Parcelable>) list);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);
        RecyclerView recyclerView = rootView.findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        List<TimetableModel> timetableList = Objects.requireNonNull(getArguments()).getParcelableArrayList(KEY);
        TimetableAdapter adapter = new TimetableAdapter(timetableList, getContext());
        recyclerView.setAdapter(adapter);

        return rootView;
    }
}
