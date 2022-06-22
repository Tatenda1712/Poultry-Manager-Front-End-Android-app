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

public class EggsAdapter extends RecyclerView.Adapter<EggsAdapter.ViewHolder> {
    JSONArray items;
    public static ViewGroup parent;
    static Context context;

    public EggsAdapter(JSONArray items, Context context) {
        this.items = items;
        this.context = context;
    }

    @NonNull
    @Override
    public EggsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        this.parent = parent;

        LayoutInflater inflater = LayoutInflater.from(context);

        // Inflate the custom layout
        View driverView = inflater.inflate(R.layout.eggs_item, parent, false);

        // Return a new holder instance
        EggsAdapter.ViewHolder viewHolder = new EggsAdapter.ViewHolder(driverView);
        return viewHolder;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onBindViewHolder(@NonNull EggsAdapter.ViewHolder holder, int position) {

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


        public TextView historydate;
        public TextView batchname;
        public TextView quantity;
        View itemView;

        ViewHolder(@NonNull View itemView) {
            super(itemView);

            this.itemView = itemView;
            historydate = itemView.findViewById(R.id.historydate);
            batchname = itemView.findViewById(R.id.eggs);
            quantity = itemView.findViewById(R.id.quantity);
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
            historydate.setText(dates);
            batchname.setText(product.getString("batch_name"));
            quantity.setText(product.getString("quantity"));
        }
    }
}
