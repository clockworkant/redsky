package com.clockworkant.redsky.ui;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.clockworkant.redsky.R;

import java.util.ArrayList;
import java.util.List;

class ForecastsViewModelAdapter extends RecyclerView.Adapter<ForecastsViewModelAdapter.ViewHolder> {
    private List<ForecastViewModel> forecasts = new ArrayList<>();

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.row_forecast, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.bind(forecasts.get(position));
    }

    @Override
    public int getItemCount() {
        return forecasts.size();
    }

    public void setForecasts(List<ForecastViewModel> forecasts) {
        this.forecasts = forecasts;
        notifyDataSetChanged();
    }

    public void clear() {
        this.forecasts.clear();
        notifyDataSetChanged();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        private final TextView descTv;
        private final TextView titleTv;
        private final TextView dateTv;

        public ViewHolder(View itemView) {
            super(itemView);
            descTv = (TextView) itemView.findViewById(R.id.row_forecast_desc);
            titleTv = (TextView) itemView.findViewById(R.id.row_forecast_title);
            dateTv = (TextView) itemView.findViewById(R.id.row_forecast_datetime);
        }

        public void bind(ForecastViewModel vm) {
            titleTv.setText(vm.getTitle());
            descTv.setText(vm.getDesc());
            dateTv.setText(vm.getDateTimeString());
        }
    }
}
