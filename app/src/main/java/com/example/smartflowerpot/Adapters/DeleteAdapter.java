package com.example.smartflowerpot.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.smartflowerpot.Model.Plant;
import com.example.smartflowerpot.R;
import com.example.smartflowerpot.RemoteDataSource.API.PlantAPI;
import com.example.smartflowerpot.ViewModel.PlantViewModel;

import java.util.ArrayList;
import java.util.List;

public class DeleteAdapter  {
/*
extends RecyclerView.Adapter<DeleteAdapter.ViewHolder>
    private DeleteListItemClickListener DeleteclickListener;
    private ArrayList<Plant> mPlants;

    public DeleteAdapter( DeleteListItemClickListener deleteclickListener, ArrayList<Plant> mPlants) {
        DeleteclickListener = deleteclickListener;
        this.mPlants = mPlants;
    }

    public DeleteListItemClickListener getDeleteclickListener() {
        return DeleteclickListener;
    }

    public void setDeleteclickListener(DeleteListItemClickListener deleteclickListener) {
        DeleteclickListener = deleteclickListener;
    }

    public List<Plant> getmPlants() {
        return mPlants;
    }

    public void setmPlants(List<Plant> mPlants) {
        this.mPlants = mPlants;
    }


    public ViewHolder onCreateViewHolder( ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.recycledview_plants, parent, false);
        return new ViewHolder(view);
    }


    public void onBindViewHolder(ViewHolder holder, int position) {
        PlantViewModel plantViewModel = null;

        holder.deleteimage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                plantViewModel.deletePlant("0004A30B00251001");

            }
        });
    }


    public int getItemCount() {
        return mPlants.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private ImageView deleteimage;

        public ViewHolder( View itemView) {
            super(itemView);
            deleteimage = itemView.findViewById(R.id.imageView2);
            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {
            DeleteclickListener.deleteListItemClick("");
        }
    }

    public interface DeleteListItemClickListener {
        void deleteListItemClick(String Eui);

    }*/
}
