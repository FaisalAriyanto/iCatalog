package icatalog.pkp.faisal.icatalog.Activity;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import java.util.ArrayList;
import java.util.UUID;

import icatalog.pkp.faisal.icatalog.Adapter.CartAdapter;
import icatalog.pkp.faisal.icatalog.Adapter.CustomerAdapter;
import icatalog.pkp.faisal.icatalog.Model.ItemModel;
import icatalog.pkp.faisal.icatalog.R;

public class CustomerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Pilih Customer");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(false);

        RecyclerView mRecyclerView1;
        RecyclerView.Adapter mAdapter1;
        RecyclerView.LayoutManager mLayoutManager1;
        ArrayList<ItemModel> promosi1Base = new ArrayList<>();



        int[] feedback = {5, 5, 5, 5};
        for (int i = 0; i < 50; i++) {
            ItemModel commodity = new ItemModel(
                    UUID.randomUUID().toString(),
                    "",
                    "Customer Name " + i,
                    feedback,
                    900000,
                    "http://www.snappertech.com/images/prashant-surana.jpg");
            promosi1Base.add(commodity);
        }

        mRecyclerView1 = (RecyclerView) findViewById(R.id.rv_promosi1);
        mRecyclerView1.setHasFixedSize(true);
        mLayoutManager1 = new LinearLayoutManager(getApplicationContext());
        mRecyclerView1.setLayoutManager(mLayoutManager1);
        mAdapter1 = new CustomerAdapter(promosi1Base, getApplicationContext());
        mRecyclerView1.setAdapter(mAdapter1);


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        } else {
            startActivity(new Intent(this, AddCustomerActivity.class));
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();

        inflater.inflate(R.menu.add_customer, menu);

        // return true so that the menu pop up is opened
        return true;
    }

}
