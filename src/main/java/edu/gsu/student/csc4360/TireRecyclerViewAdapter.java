package edu.gsu.student.csc4360;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class TireRecyclerViewAdapter extends RecyclerView.Adapter<TireRecyclerViewAdapter.ViewHolder>{

    private ArrayList<Tire> tires;
    private Context         context;

    public TireRecyclerViewAdapter(Context context, ArrayList<Tire> tires ) {
        this.tires   = tires;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_list_tire, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        holder.brand.setText( tires.get(position).getBrand() );
        holder.model.setText( tires.get(position).getModel() );
        holder.part_number.setText( tires.get(position).getPart_number() );

        String tireSize = tires.get(position).getWidth() +
                tires.get(position).getConstruction() +
                tires.get(position).getWheel_diameter();

        holder.size.setText( tireSize );

        // Opens the modify page for the particular item that's passed
        holder.parentLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, EditActivity.class);
                intent.putExtra("part_number", tires.get(position).getPart_number());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return tires.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView       brand;
        TextView       model;
        TextView       size;
        TextView       part_number;
        RelativeLayout parentLayout;

        public ViewHolder(View itemView) {
            super(itemView);
            brand        = itemView.findViewById(R.id.tire_brand);
            model        = itemView.findViewById(R.id.tire_model);
            size         = itemView.findViewById(R.id.tire_size);
            part_number  = itemView.findViewById(R.id.tire_part_number);
            parentLayout = itemView.findViewById(R.id.parent_layout);
        }
    }
}
