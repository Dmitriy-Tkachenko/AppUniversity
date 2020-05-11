package com.university.studytime.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.university.studytime.R;
import com.university.studytime.models.TimetableModel;

import java.util.List;

public class TimetableAdapter extends RecyclerView.Adapter<TimetableAdapter.RecyclerViewAdapter> {

    private List<TimetableModel> timetableModels;
    private Context context;


    public TimetableAdapter(List<TimetableModel> timetableModels, Context context) {
        this.timetableModels = timetableModels;
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerViewAdapter onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.fragment_monday, parent, false);
        return new RecyclerViewAdapter(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewAdapter holder, int position) {
        TimetableModel timetableModel = timetableModels.get(position);
        holder.tv_startTime.setText(timetableModel.getStartTime());
        holder.tv_endTime.setText(timetableModel.getEndTime());
        holder.tv_discipline.setText(timetableModel.getDiscipline());
        holder.tv_teacher.setText(timetableModel.getFullName());
        holder.tv_lessonType.setText(timetableModel.getLessonType());
        holder.tv_audience.setText(timetableModel.getAudience());

    }

    @Override
    public int getItemCount() {
        return timetableModels.size();
    }

    static class RecyclerViewAdapter extends RecyclerView.ViewHolder  {

        TextView tv_startTime, tv_endTime, tv_discipline, tv_teacher, tv_lessonType, tv_audience;

        RecyclerViewAdapter(@NonNull View itemView) {
            super(itemView);

            tv_startTime = itemView.findViewById(R.id.start_time);
            tv_endTime = itemView.findViewById(R.id.end_time);
            tv_discipline = itemView.findViewById(R.id.discipline);
            tv_teacher = itemView.findViewById(R.id.teacher);
            tv_lessonType = itemView.findViewById(R.id.lesson_type);
            tv_audience = itemView.findViewById(R.id.audience);
        }
    }
}
