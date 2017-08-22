package icatalog.pkp.faisal.icatalog.Adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import icatalog.pkp.faisal.icatalog.Activity.ItemDetailActivity;
import icatalog.pkp.faisal.icatalog.Model.ItemModel;
import icatalog.pkp.faisal.icatalog.R;


/**
 * Created by faisal.ariyanto on 11/2/2016.
 */
public class CartAdapter extends
        RecyclerView.Adapter<CartAdapter.DataObjectHolder>

{
    private static String mode;
    public ArrayList<ItemModel> mDataSet;
    private Context mCtx;

    public static class DataObjectHolder extends
            RecyclerView.ViewHolder {

        public ArrayList<ItemModel> mItemSet;

        ImageView photoUrl;
        TextView name;
        Context mCtx;

        public DataObjectHolder(
                final View view,
                final ViewGroup viewGroup) {
            super(view);
            photoUrl = (ImageView) view.findViewById(R.id.iv_commodity_photo);
            name = (TextView) view.findViewById(R.id.tv_commodity_name);

            view.setLayoutParams(new CardView.LayoutParams(CardView.LayoutParams.MATCH_PARENT, CardView.LayoutParams.WRAP_CONTENT));

        }

        public void setItem(ArrayList<ItemModel> mDataSet, Context ctx) {
            mItemSet = mDataSet;
            mCtx = ctx;
        }


    }

    public CartAdapter(ArrayList<ItemModel> myDataSet, Context ctx) {
        mDataSet = myDataSet;
        mCtx = ctx;
    }

    @Override
    public DataObjectHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_cart, parent, false);

        DataObjectHolder dataObjectHolder = new DataObjectHolder(view, parent);
        return dataObjectHolder;
    }

    @Override
    public void onBindViewHolder(DataObjectHolder holder, int position) {
        ItemModel commodity = mDataSet.get(position);
        holder.setItem(mDataSet, mCtx);

        holder.name.setText(commodity.Name);
//        holder.sellerName.setText(selerBase.get(0).Name);
        Picasso
                .with(holder.photoUrl.getContext())
                .load(commodity.PhotoUrl)
                .into(holder.photoUrl);
    }

    @Override
    public int getItemCount() {
        return mDataSet.size();
    }
}
