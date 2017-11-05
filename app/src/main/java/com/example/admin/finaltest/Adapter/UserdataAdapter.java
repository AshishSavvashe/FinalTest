package com.example.admin.finaltest.Adapter;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.admin.finaltest.MainActivity;
import com.example.admin.finaltest.Model.Datum;
import com.example.admin.finaltest.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by Admin on 04-11-2017.
 */

public class UserdataAdapter extends RecyclerView.Adapter<UserdataAdapter.RecyclerViewHolders>  {

    private ArrayList<Datum> dataViewsArrayList;

    private List<Datum> filteredList;
    private Context context;


    public UserdataAdapter(Context context, ArrayList<Datum> dataViewsArrayList) {
        this.dataViewsArrayList = dataViewsArrayList;
        this.context = context;
    }

    public UserdataAdapter(List<Datum> filteredList, Activity activity) {
        this.filteredList=filteredList;
    }


    @Override
    public UserdataAdapter.RecyclerViewHolders onCreateViewHolder(ViewGroup parent, int viewType) {
        View layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.itemview_userr, null);
        RecyclerViewHolders rcv = new RecyclerViewHolders(layoutView);
        return rcv;
    }

    @Override
    public void onBindViewHolder(UserdataAdapter.RecyclerViewHolders holder, int position) {

        holder.tvid.setText((""+dataViewsArrayList.get(position).getId()));
        holder.tvname.setText((""+dataViewsArrayList.get(position).getFirst_name()  +" "+ dataViewsArrayList.get(position).getLast_name()));


        Picasso.with(context).load(dataViewsArrayList.get(position).getAvatar())
                .placeholder(R.drawable.user_placeholder)
                .into((holder).ivUserImage);


    }

    @Override
    public int getItemCount() {
        return dataViewsArrayList == null ? 0 : dataViewsArrayList.size();
    }


    public class RecyclerViewHolders extends RecyclerView.ViewHolder {

        TextView tvid,tvname;
        public LinearLayout llcardmain;
        public CircleImageView ivUserImage;





        public RecyclerViewHolders(View itemView) {
            super(itemView);
            ivUserImage = (CircleImageView) itemView.findViewById(R.id.ivUserImage);
            tvid = (TextView) itemView.findViewById(R.id.tvid);
            tvname = (TextView) itemView.findViewById(R.id.tvname);




        }
    }
}
