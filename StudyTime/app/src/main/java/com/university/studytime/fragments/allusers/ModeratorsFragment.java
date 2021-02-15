package com.university.studytime.fragments.allusers;

import android.os.Bundle;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.university.studytime.R;
import com.university.studytime.adapter.UsersAdapter;
import com.university.studytime.models.UsersModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ModeratorsFragment extends Fragment {
    private static final String KEY = null;
    // Нужно для передачи параметра при создании фрагмента
    public static ModeratorsFragment newInstance(List<UsersModel> list) {
        Bundle args = new Bundle();
        ModeratorsFragment fragment = new ModeratorsFragment();
        args.putParcelableArrayList(KEY, (ArrayList<? extends Parcelable>) list);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);
        RecyclerView recyclerView = rootView.findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        List<UsersModel> usersList = Objects.requireNonNull(getArguments()).getParcelableArrayList(KEY);
        UsersAdapter adapter = new UsersAdapter(usersList, getContext());
        recyclerView.setAdapter(adapter);

        return rootView;
    }
}
