package com.example.tbd.Ticket;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.tbd.R;

import java.util.ArrayList;

public class TicketAdapter extends RecyclerView.Adapter<TicketAdapter.TicketHolder>  {
    private Context context;
    private ArrayList<Ticket> tickets = new ArrayList<>();

    public TicketAdapter(Context context, ArrayList<Ticket> tickets) {
        this.context = context;
        this.tickets = tickets;
    }

    @Override
    public TicketHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.ticket_booked,parent,false);
        return new TicketHolder(view);
    }

    @Override
    public void onBindViewHolder(TicketAdapter.TicketHolder holder, int position) {
        Ticket ticket = tickets.get(position);
        if (ticket == null){
            return;
        }
        holder.nameRoute.setText(ticket.getNameRoute());
        holder.price.setText(ticket.getPriceTicket()+" VNĐ");
        holder.departureDate.setText(ticket.getDepartureDate());
        holder.departureTime.setText(ticket.getDepartureTime());
        holder.numSeat.setText(ticket.getNumSeat());

    }

    @Override
    public int getItemCount() {
        return tickets.size();
    }

    public class TicketHolder extends RecyclerView.ViewHolder
    {
        TextView nameRoute, departureDate, departureTime, numSeat, price;

        public TicketHolder( View itemView) {
            super(itemView);

            nameRoute = itemView.findViewById(R.id.name_Route);
            departureDate = itemView.findViewById(R.id.departure_Date);
            departureTime = itemView.findViewById(R.id.departure_Time);
            numSeat = itemView.findViewById(R.id.num_Seat);
            price = itemView.findViewById(R.id.price);

        }
    }
}
