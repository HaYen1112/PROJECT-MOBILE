package edu.nlu.probooktic.View;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.List;

import edu.nlu.probooktic.Model.Trip;
import edu.nlu.probooktic.R;

public class TripsAdapter extends BaseAdapter {

    TextView tVgioKH, tVgioKT, tVngayKH, tVngayKT, tVdiemKH, tVdiemKT,tVgia;
    private Context context;
    private int layout;
    private List<Trip> dstrip;

    public TripsAdapter(Context context, int layout, List<Trip> dstrip) {
        this.context = context;
        this.layout = layout;
        this.dstrip = dstrip;
    }

    @Override
    public int getCount() {
        return dstrip.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView = inflater.inflate(layout,null);


                //Anh xa
        tVgioKH = (TextView) convertView.findViewById(R.id.tgKH);
        tVgioKT = (TextView) convertView.findViewById(R.id.tgKT);
        tVngayKH = (TextView) convertView.findViewById(R.id.ngayKH);
        tVngayKT = (TextView) convertView.findViewById(R.id.ngayKT);
        tVdiemKH = (TextView) convertView.findViewById(R.id.diemKH);
        tVdiemKT = (TextView) convertView.findViewById(R.id.diemKT);
        tVgia = (TextView) convertView.findViewById(R.id.price);
                //gán gtri
        Trip trip = dstrip.get(position);

        tVgioKH.setText(trip.getTgKH());
        tVgioKT.setText(trip.getTgKT());
        tVngayKH.setText(trip.getDate().getDay()+"/"+trip.getDate().getMonth()+"/"+trip.getDate().getYear());
        tVngayKT.setText(trip.getDate().getDay()+"/"+trip.getDate().getMonth()+"/"+trip.getDate().getYear());
        tVdiemKH.setText(trip.getDiemKH());
        tVdiemKT.setText(trip.getDiemKT());
//        tVgia.setText(trip.getPrice()+"");

        return convertView;
    }
}
