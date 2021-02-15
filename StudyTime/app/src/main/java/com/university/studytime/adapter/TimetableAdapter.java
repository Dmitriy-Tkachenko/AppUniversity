package com.university.studytime.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.university.studytime.R;
import com.university.studytime.activity.ModeratorActivity;
import com.university.studytime.api.Api;
import com.university.studytime.api.ApiClient;
import com.university.studytime.models.TimetableModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
//Данный класс отвечает за предоставление данных
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
        View view = LayoutInflater.from(context).inflate(R.layout.fragment_timetable, parent, false);
        return new RecyclerViewAdapter(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final RecyclerViewAdapter holder, int position) {
        TimetableModel timetableModel = timetableModels.get(position);
        holder.tv_startTime.setText(timetableModel.getStartTime());
        holder.tv_endTime.setText(timetableModel.getEndTime());
        holder.tv_discipline.setText(timetableModel.getDiscipline());
        holder.tv_teacher.setText(timetableModel.getFullName());
        holder.tv_lessonType.setText(timetableModel.getLessonType());
        holder.tv_audience.setText(timetableModel.getAudience());
        if (context instanceof ModeratorActivity) {
            holder.btnDeleteCard.setVisibility(View.VISIBLE);
            //Ообработка нажатия на кнопку удаления занятий
            holder.btnDeleteCard.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Api api = ApiClient.getClient().create(Api.class);
                    Call<TimetableModel> existGroupCode = api.delete(timetableModels.get(holder.getAdapterPosition()).getId());

                    existGroupCode.enqueue(new Callback<TimetableModel>() {
                        @Override
                        public void onResponse(@NonNull Call<TimetableModel> call, @NonNull Response<TimetableModel> response) {
                            if (response.isSuccessful() && response.body() != null) {
                                if (response.body().getIsSuccess() == 1) {
                                    timetableModels.remove(holder.getAdapterPosition());
                                    notifyItemRemoved(holder.getAdapterPosition());
                                    notifyItemRangeChanged(holder.getAdapterPosition(), timetableModels.size());
                                    Toast.makeText(context, "Занятие удалено!", Toast.LENGTH_SHORT).show();
                                }
                            } else {
                                Toast.makeText(context, "Ошибка!", Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onFailure(@NonNull Call<TimetableModel> call, @NonNull Throwable t) {
                        }
                    });

                }
            });
        } else  holder.btnDeleteCard.setVisibility(View.GONE);
    }

    @Override
    public int getItemCount() {
        return timetableModels.size();
    }

    static class RecyclerViewAdapter extends RecyclerView.ViewHolder  {
        CardView cardView;
        TextView tv_startTime, tv_endTime, tv_discipline, tv_teacher, tv_lessonType, tv_audience;
        ImageButton btnDeleteCard;

        RecyclerViewAdapter(@NonNull View itemView) {
            super(itemView);
            cardView = itemView.findViewById(R.id.card_view);
            tv_startTime = itemView.findViewById(R.id.start_time);
            tv_endTime = itemView.findViewById(R.id.end_time);
            tv_discipline = itemView.findViewById(R.id.discipline);
            tv_teacher = itemView.findViewById(R.id.teacher);
            tv_lessonType = itemView.findViewById(R.id.lesson_type);
            tv_audience = itemView.findViewById(R.id.audience);
            btnDeleteCard = itemView.findViewById(R.id.btnDeleteCard);
        }
    }
}
