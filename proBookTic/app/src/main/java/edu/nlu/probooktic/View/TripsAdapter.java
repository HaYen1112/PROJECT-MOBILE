package edu.nlu.probooktic.View;

import android.content.Context;
import android.graphics.Color;
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

    TextView tVgioKH, tVgioKT, tVngayKH, tVngayKT, tVdiemKH, tVdiemKT,tVidTrip, tVBienSO;
    private Context context;
    private int layout;
    private List<Trip> dstrip;
    public static int mSelectedItem=-1;


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


        return dstrip.get(position);
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
        tVidTrip = (TextView) convertView.findViewById(R.id.idTripp);
        tVBienSO = (TextView) convertView.findViewById(R.id.idBienSoo);

                //gán gtri
        Trip trip = dstrip.get(position);

        tVgioKH.setText(trip.getTgKH());
        tVgioKT.setText(trip.getTgKT());
        tVngayKH.setText(Trip.dateToString(trip.getDate()));
        tVngayKT.setText(Trip.dateToString(trip.getDate()));
        tVdiemKH.setText(trip.getDiemKH());
        tVdiemKT.setText(trip.getDiemKT());
        tVidTrip.setText(trip.getIdtrip());
        tVBienSO.setText(trip.getBienSo());
//        tVgia.setText(trip.getPrice()+"");
        if (position==mSelectedItem){
            convertView.setBackgroundColor(Color.parseColor("#7CC4D3FF"));
        }
        return convertView;
    }
}
