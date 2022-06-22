package com.example.poultry.Adapter;

import android.content.Context;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import com.example.poultry.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class FlockAdapter extends RecyclerView.Adapter<FlockAdapter.ViewHolder>{
    JSONArray items;
    public static ViewGroup parent;
    static Context context;

    public FlockAdapter(JSONArray items, Context context) {
        this.items = items;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        this.parent = parent;

        LayoutInflater inflater = LayoutInflater.from(context);

        // Inflate the custom layout
        View driverView = inflater.inflate(R.layout.flock_item, parent, false);

        // Return a new holder instance
        ViewHolder viewHolder = new ViewHolder(driverView);
        return viewHolder;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        try {
            JSONObject history = items.getJSONObject(position);
            holder.onBindView(history, position);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void notifyChange() {
        this.notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return items.length();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {


        public TextView newdate;
        public TextView amount;
        public TextView batchname;
        public TextView purpose;
        View itemView;

        ViewHolder(@NonNull View itemView) {
            super(itemView);

            this.itemView = itemView;
            newdate = itemView.findViewById(R.id.newdate);
            amount = itemView.findViewById(R.id.amount);
            purpose = itemView.findViewById(R.id.purpose);
            batchname = itemView.findViewById(R.id.batchname);
        }

        @RequiresApi(api = Build.VERSION_CODES.O)
        public void onBindView(JSONObject product, int pos) throws JSONException {
            String dates=product.getString("created_at");
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
            Date date = null;
            try {
                date = simpleDateFormat.parse(dates);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
            dates = simpleDateFormat.format(date);
            newdate.setText(dates);
            amount.setText(product.getString("quantity"));
            purpose.setText(product.getString("purpose"));
            batchname.setText(product.getString("batch_name"));
        }
    }
}
