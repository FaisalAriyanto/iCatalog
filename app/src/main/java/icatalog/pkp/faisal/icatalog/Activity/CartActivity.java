package icatalog.pkp.faisal.icatalog.Activity;

import android.content.Intent;
import android.os.Handler;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import icatalog.pkp.faisal.icatalog.Adapter.CartAdapter;
import icatalog.pkp.faisal.icatalog.Adapter.CustomerAdapter;
import icatalog.pkp.faisal.icatalog.Model.CategoryModel;
import icatalog.pkp.faisal.icatalog.Model.ItemModel;
import icatalog.pkp.faisal.icatalog.R;

public class CartActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Cart");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(false);


        RecyclerView mRecyclerView1;
        final RecyclerView.Adapter mAdapter1;
        RecyclerView.LayoutManager mLayoutManager1;
        final ArrayList<ItemModel> promosi1Base = new ArrayList<>();


        CategoryModel category = new CategoryModel(
                UUID.randomUUID().toString(),
                "Komputer"
        );

        int[] feedback = {5, 5, 5, 5};
        for (int i = 0; i < 50; i++) {
            ItemModel commodity = new ItemModel(
                    UUID.randomUUID().toString(),
                    category.Id,
                    "Air Conditioner " + i,
                    feedback,
                    900000,
                    "http://id-live-02.slatic.net/p/15/samsung-ac-1-2-pk-ar05hcflaw-putih-khusus-jabodetabek-2805-5580821-bfbd0556cb5d4ddf29481c475459a97b-webp-product.jpg");
            promosi1Base.add(commodity);
        }

        mRecyclerView1 = (RecyclerView) findViewById(R.id.rv_promosi1);
        mRecyclerView1.setHasFixedSize(true);
        mLayoutManager1 = new LinearLayoutManager(getApplicationContext());
        mRecyclerView1.setLayoutManager(mLayoutManager1);
        mAdapter1 = new CartAdapter(promosi1Base, getApplicationContext());
        mRecyclerView1.setAdapter(mAdapter1);

        ItemTouchHelper.SimpleCallback simpleItemTouchCallback = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(final RecyclerView.ViewHolder viewHolder, int swipeDir) {

                Toast.makeText(getApplicationContext(), "Delete item" + viewHolder.getPosition(), Toast.LENGTH_LONG).show();

                final Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        promosi1Base.remove(viewHolder.getPosition());
                        mAdapter1.notifyDataSetChanged();
                    }
                }, 500);
                //Remove swiped item from list and notify the RecyclerView
            }
        };

        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(simpleItemTouchCallback);

        itemTouchHelper.attachToRecyclerView(mRecyclerView1);

//        List<String> categories = new ArrayList<String>();
//        categories.add("Automobile");
//        categories.add("Business Services");
//        categories.add("Computers");
//        categories.add("Education");
//        categories.add("Personal");
//        categories.add("Travel");
//
//        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, categories);
//        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//
//
//        Spinner custSpinner = (Spinner) findViewById(R.id.spinner_rv_no);
//        custSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
//
//            }
//
//            @Override
//            public void onNothingSelected(AdapterView<?> adapterView) {
//
//            }
//        });
//
//        custSpinner.setAdapter(dataAdapter);
//
//


    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    public void addCustBtn(View view) {
        startActivity(new Intent(this, AddCustomerActivity.class));
    }

    public void pilihCust(View view) {
        startActivityForResult(new Intent(this, CustomerActivity.class), 1);
    }

    public void proses(View view) {
        Snackbar.make(view, "Pesanan di proses", Snackbar.LENGTH_LONG).show();

        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                //Do something after 100ms

                finish();
            }
        }, 1000);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 1) {


        }
    }
}
