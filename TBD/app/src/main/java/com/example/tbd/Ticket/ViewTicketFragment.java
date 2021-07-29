package com.example.tbd.Ticket;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tbd.R;
import com.example.tbd.Ticket.Ticket;
import com.example.tbd.Ticket.TicketAdapter;
import com.example.tbd.Ticket.TicketDetails;

import java.util.ArrayList;

public class ViewTicketFragment extends Fragment {

    RecyclerView recyclerTicketBooked;
    CardView cvTicketBooked;
    TextView txtNameRoute;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.kiem_tra_ve, container, false);

//        recyclerTicketBooked = view.findViewById(R.id.rcv_ticket_booked);

        cvTicketBooked = view.findViewById(R.id.cv_ticket_list);

        createTicket();

        txtNameRoute = view.findViewById(R.id.name_Route);

        cvTicketBooked.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), TicketDetails.class));
            }
        });
        return view;
    }

    public void createTicket(){

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerTicketBooked.setLayoutManager(layoutManager);

        TicketAdapter adapter = new TicketAdapter(getActivity(),getTicket());
        recyclerTicketBooked.setAdapter(adapter);
        adapter.notifyDataSetChanged();

    }
    public ArrayList<Ticket> getTicket(){
        ArrayList<Ticket> list = new ArrayList<>();
        list.add(new Ticket("222","Bến Tre -> TP Hồ Chí Minh",100000,"Thường","A1","21/06/2021","22/06/2021","8:00"));
        list.add(new Ticket("222","Bến Tre -> TP Hồ Chí Minh",100000,"Thường","A1","21/06/2021","22/06/2021","8:00"));
        list.add(new Ticket("222","Bến Tre -> TP Hồ Chí Minh",100000,"Thường","A1","21/06/2021","22/06/2021","8:00"));
        list.add(new Ticket("222","Bến Tre -> TP Hồ Chí Minh",100000,"Thường","A1","21/06/2021","22/06/2021","8:00"));
        list.add(new Ticket("222","Bến Tre -> TP Hồ Chí Minh",100000,"Thường","A1","21/06/2021","22/06/2021","8:00"));
        list.add(new Ticket("222","Bến Tre -> TP Hồ Chí Minh",100000,"Thường","A1","21/06/2021","22/06/2021","8:00"));


        return list;
    }
}
