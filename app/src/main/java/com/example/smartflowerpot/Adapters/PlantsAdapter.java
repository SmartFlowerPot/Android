package com.example.smartflowerpot.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.smartflowerpot.Model.Plant;
import com.example.smartflowerpot.R;

import java.util.ArrayList;
import java.util.List;

public class PlantsAdapter extends RecyclerView.Adapter<PlantsAdapter.ViewHolder> {

    private List<Plant> mPlants;
    final private OnListItemClickListener mOnListItemClickListener;

    public PlantsAdapter(List<Plant> mPlants, OnListItemClickListener listener){
        this.mPlants = mPlants;
        mOnListItemClickListener = listener;
    }

    public void setmPlants(List<Plant> mPlants) {
        this.mPlants = mPlants;
    }

    public int getItemCount() {
        return mPlants.size();
    }

    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.recycledview_plants, parent, false);
        return new ViewHolder(view);
    }

    public void onBindViewHolder(ViewHolder viewHolder, int position) {
        viewHolder.name.setText(mPlants.get(position).getNickname());
        viewHolder.deviceIdentifier.setText(mPlants.get(position).getPlantID());
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView name;
        TextView deviceIdentifier;

        ViewHolder(View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.nickname);
            deviceIdentifier = itemView.findViewById(R.id.deviceIdentifier);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            mOnListItemClickListener.onListItemClick(deviceIdentifier.getText().toString());
        }
    }

    public interface OnListItemClickListener {
        void onListItemClick(String deviceIdentifier);
    }
}

