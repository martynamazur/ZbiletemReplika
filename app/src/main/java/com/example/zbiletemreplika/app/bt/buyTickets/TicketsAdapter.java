package com.example.zbiletemreplika.app.bt.buyTickets;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.zbiletemreplika.R;
import java.util.ArrayList;


public class TicketsAdapter extends RecyclerView.Adapter<TicketsAdapter.ViewHolder> {

    private ArrayList<TicketsModel> itemList;

    public TicketsAdapter(ArrayList<TicketsModel> ticketList) {
        itemList = ticketList;
    }

    @NonNull
    @Override
    public TicketsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.buy_ticket_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TicketsAdapter.ViewHolder holder, int position) {
       TicketsModel item = itemList.get(position);
       holder.headline.setText(item.getHeadline());
       holder.price.setText(String.valueOf(item.getPrice()));
       holder.subHeadline.setText(item.getTicketTypeInformation());
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }
    // Metoda do ustawienia nowej listy danych i odświeżenia adaptera
    public void setTicketList(ArrayList<TicketsModel> newTicketList) {
        itemList = newTicketList;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView price;
        public TextView headline;
        public TextView subHeadline;


        public ViewHolder(View itemView){
            super(itemView);
            price = itemView.findViewById(R.id.price);
            headline = itemView.findViewById(R.id.headline);
            subHeadline = itemView.findViewById(R.id.subHeadline);

        }
    }
}