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
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import icatalog.pkp.faisal.icatalog.Activity.ItemDetailActivity;
import icatalog.pkp.faisal.icatalog.Activity.ListItemActivity;
import icatalog.pkp.faisal.icatalog.Model.ItemModel;
import icatalog.pkp.faisal.icatalog.R;


/**
 * Created by faisal.ariyanto on 11/2/2016.
 */
public class ItemAdapter extends
        RecyclerView.Adapter<ItemAdapter.DataObjectHolder>

{
    private static String mode;
    public ArrayList<ItemModel> mDataSet;
    private Context mCtx;

    public static class DataObjectHolder extends
            RecyclerView.ViewHolder
            implements View.OnClickListener {

        public ArrayList<ItemModel> mItemSet;

        ImageView photoUrl;
        TextView name;
        TextView sellerName;
        TextView feedback;
        TextView options;
        TextView price;
        Button btnFavBuy;
        Context mCtx;

        public DataObjectHolder(
                final View view,
                final ViewGroup viewGroup){
            super(view);
            photoUrl = (ImageView) view.findViewById(R.id.iv_commodity_photo);
            name = (TextView) view.findViewById(R.id.tv_commodity_name);
            sellerName = (TextView) view.findViewById(R.id.tv_commodity_seller_name);
            feedback = (TextView) view.findViewById(R.id.tv_commodity_feedback);
            price = (TextView) view.findViewById(R.id.tv_commodity_price);
            btnFavBuy = (Button) view.findViewById(R.id.btn_favorite_buy);
            options = (TextView) view.findViewById(R.id.fav_cv_Options);

            switch(mode) {
                case "Promo":
                    btnFavBuy.setVisibility(View.GONE);
                    options.setVisibility(View.GONE);
                    break;
                case "Favorit":
                    view.setLayoutParams(new CardView.LayoutParams(CardView.LayoutParams.MATCH_PARENT, CardView.LayoutParams.WRAP_CONTENT));
                    btnFavBuy.setOnClickListener(this);
                    options.setOnClickListener(this);
                    break;
                case "Vertical":
                    view.setLayoutParams(new CardView.LayoutParams(CardView.LayoutParams.MATCH_PARENT, CardView.LayoutParams.WRAP_CONTENT));
                    btnFavBuy.setVisibility(View.GONE);
                    options.setVisibility(View.GONE);
                    break;
            }
            view.setOnClickListener(this);
        }

        public void setItem(ArrayList<ItemModel> mDataSet, Context ctx) {
            mItemSet = mDataSet;
            mCtx = ctx;
        }

        @Override
        public void onClick(View v) {
            int id = v.getId();
            switch (id){
                case R.id.btn_favorite_buy:
                    Snackbar.make(itemView, "Buy " + mItemSet.get(getAdapterPosition()).Name, Snackbar.LENGTH_LONG)
                            .show();
                    break;
                case R.id.card_view:
//                    Snackbar.make(itemView, "Click " + mItemSet.get(getAdapterPosition()).Name, Snackbar.LENGTH_LONG)
//                            .show();

                    Intent i = new Intent(mCtx, ItemDetailActivity.class);
                    i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

                    Bundle b = new Bundle();
                    b.putString("title", mItemSet.get(getAdapterPosition()).Name);
                    b.putString("photoUrl", mItemSet.get(getAdapterPosition()).PhotoUrl);
                    b.putString("price", String.valueOf(mItemSet.get(getAdapterPosition()).Price));
                    i.putExtras(b);
                    mCtx.startActivity(i);
                    break;
                case R.id.fav_cv_Options:
                    //onPopUpMenu();
                    break;
            }
        }

//        private void onPopUpMenu(){
//            PopupMenu popup = new PopupMenu(mCtx, options);
//            popup.inflate(R.menu.favorite_cv_menu);
//            //adding click listener
//            popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
//                @Override
//                public boolean onMenuItemClick(MenuItem item) {
//                    switch (item.getItemId()) {
//                        case R.id.fav_beli:
//                            //handle menu1 click
//                            Toast.makeText(mCtx, "Beli", Toast.LENGTH_SHORT).show();
//                            break;
//                        case R.id.fav_share:
//
//                            Toast.makeText(mCtx, "Share", Toast.LENGTH_SHORT).show();
//                            break;
//                        case R.id.fav_profil:
//                            Toast.makeText(mCtx, "Profile", Toast.LENGTH_SHORT).show();
//                            break;
//
//                        case R.id.fav_tidak_favoritkan:
//                            Toast.makeText(mCtx, "Tidak Fav", Toast.LENGTH_SHORT).show();
//                            break;
//                        //Snackbar.make()
//                    }
//                    return false;
//                }
//            });
//            popup.show();
//        }
    }

    public ItemAdapter(ArrayList<ItemModel> myDataSet, String viewMode, Context ctx){
        mDataSet = myDataSet;
        mode = viewMode;
        mCtx = ctx;
    }

    @Override
    public DataObjectHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_item, parent, false);

        DataObjectHolder dataObjectHolder = new DataObjectHolder(view, parent);
        return dataObjectHolder;
    }

    @Override
    public void onBindViewHolder(DataObjectHolder holder, int position) {
        ItemModel commodity = mDataSet.get(position);
        holder.setItem(mDataSet, mCtx);
        int total = 0;
        int percent;
        int feedbackLength = commodity.Feedback.length;
        for(int i = 0; i< feedbackLength; i++ ){
            total += commodity.Feedback[i];
        }
        percent = total / feedbackLength * 2 * 10;

        holder.name.setText(commodity.Name);
        holder.price.setText("Rp"+commodity.Price);
        holder.feedback.setText(percent+"% ("+feedbackLength+" feedback)");
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
