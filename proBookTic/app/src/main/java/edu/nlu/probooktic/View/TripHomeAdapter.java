package edu.nlu.probooktic.View;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import edu.nlu.probooktic.Model.Trip;
import edu.nlu.probooktic.Model.TripItem;
import edu.nlu.probooktic.R;

public class TripHomeAdapter extends BaseAdapter {
    private Context context;
    private int layout;
    private List<TripItem> listTrip = new ArrayList<>();

    public TripHomeAdapter(Context context, int layout, List<TripItem> listTrip) {
        this.context = context;
        this.layout = layout;
        this.listTrip = listTrip;
    }

    @Override
    public int getCount() {
        return listTrip.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    private class ViewHolders{
        TextView chuyenDi, gioKH, giaVe, bienSoXe;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolders holder;
        if (convertView == null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(layout, null);
            holder = new ViewHolders();
            holder.chuyenDi = (TextView) convertView.findViewById(R.id.chuyenDi);
            holder.gioKH = (TextView) convertView.findViewById(R.id.gioKH);
            holder.giaVe = (TextView) convertView.findViewById(R.id.giaVe);
            holder.bienSoXe = (TextView) convertView.findViewById(R.id.bienSo);
            convertView.setTag(holder);
        }else {
            holder = (ViewHolders) convertView.getTag();
        }
        holder.chuyenDi.setText("Chuyến đi: "+ listTrip.get(position).getChuyenDi());
        holder.gioKH.setText("Giờ khởi hành: "+ listTrip.get(position).getGioKH());
        holder.giaVe.setText("Giá vé: "+ listTrip.get(position).getGiaVe());
        holder.bienSoXe.setText("Biển số xe: "+ listTrip.get(position).getBienSoXe());

        return convertView;
    }
}
