package com.university.studytime.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.university.studytime.R;
import com.university.studytime.adapter.TimetableAdapter;
import com.university.studytime.api.Api;
import com.university.studytime.api.ApiClient;
import com.university.studytime.models.TimetableModel;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MondayFragment extends Fragment {
    private List<TimetableModel> timetableList;
    private Iterator<TimetableModel> timetableIterator;
    private RecyclerView recyclerView;
    private String codeGroup;

    public MondayFragment(String codeGroup) {
        this.codeGroup = codeGroup;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);
        recyclerView = rootView.findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        timetableList = new ArrayList<>();
        timetable();

        return rootView;
    }

    private void timetable() {
        Api api = ApiClient.getClient().create(Api.class);
        final Call<List<TimetableModel>> timetable = api.timetable(codeGroup);

        timetable.enqueue(new Callback<List<TimetableModel>>() {
            @Override
            public void onResponse(@NonNull Call<List<TimetableModel>> call, @NonNull Response<List<TimetableModel>> response) {
                if (response.isSuccessful() && response.body() != null) {

                    timetableList = response.body();
                    timetableIterator = timetableList.iterator();

                    while (timetableIterator.hasNext()) {
                        TimetableModel next = timetableIterator.next();
                        if (!next.getDayName().equals("Понедельник")) {
                            timetableIterator.remove();
                        }
                    }

                    TimetableAdapter adapter = new TimetableAdapter(timetableList, getContext());
                    recyclerView.setAdapter(adapter);


                } else {
                    Toast.makeText(getActivity(), "Error", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(@NonNull Call<List<TimetableModel>> call, @NonNull Throwable t) {

            }
        });
    }
}
