package icatalog.pkp.faisal.icatalog.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.UUID;

import icatalog.pkp.faisal.icatalog.Adapter.ItemAdapter;
import icatalog.pkp.faisal.icatalog.Model.CategoryModel;
import icatalog.pkp.faisal.icatalog.Model.ItemModel;
import icatalog.pkp.faisal.icatalog.R;

import static java.security.AccessController.getContext;

public class ListItemActivity extends AppCompatActivity {
    private String title;

    private RecyclerView mRecyclerView1;
    private RecyclerView.Adapter mAdapter1;
    private RecyclerView.LayoutManager mLayoutManager1;
    private ArrayList<ItemModel> promosi1Base;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_item);

        Bundle b = getIntent().getExtras();
        if (b != null) {
            title = b.getString("title");
        }

        promosi1Base = new ArrayList<>();
        seedData1(title);
        makeCardView();

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle(title);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(false);
    }

    private void makeCardView() {
        mRecyclerView1 = (RecyclerView) findViewById(R.id.rv_promosi1);
        mRecyclerView1.setHasFixedSize(true);
        mLayoutManager1 = new GridLayoutManager(this, 2);
        mRecyclerView1.setLayoutManager(mLayoutManager1);
        mAdapter1 = new ItemAdapter(promosi1Base, "Vertical", getApplicationContext());
        mRecyclerView1.setAdapter(mAdapter1);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    private void seedData1(String title) {
        CategoryModel category = new CategoryModel(
                UUID.randomUUID().toString(),
                "Komputer"
        );
        int[] feedback = {5, 5, 5, 5};

        String url1 = "";
        switch (title) {
            case "Air Conditioner":
                promosi1Base.add(new ItemModel(
                        UUID.randomUUID().toString(),
                        category.Id,
                        "Samsung AC 1/2 PK AR05HCFLAW - Putih",
                        feedback,
                        2475000,
                        "http://id-live-02.slatic.net/p/15/samsung-ac-1-2-pk-ar05hcflaw-putih-khusus-jabodetabek-2805-5580821-bfbd0556cb5d4ddf29481c475459a97b-webp-product.jpg"));

                promosi1Base.add(new ItemModel(
                        UUID.randomUUID().toString(),
                        category.Id,
                        "Samsung AR09KRFSVURN AC Low Watt ",
                        feedback,
                        3293000,
                        "http://id-live-02.slatic.net/p/15/samsung-ar09krfsvurn-ac-low-watt-1pk-650watt-putih-produk-baru-garansi-10-tahun-kompressor-3405-43739001-7ba22b4e1ff0f669b5062f0dbcbf4e80-webp-product.jpg"));


                promosi1Base.add(new ItemModel(
                        UUID.randomUUID().toString(),
                        category.Id,
                        "Daikin AC FTNE15JEV 1/2 PK Standart ",
                        feedback,
                        2993000,
                        "http://id-live-02.slatic.net/p/15/daikin-ac-ftne15jev-1-2-pk-standart-r410-putih-6646-7257089-4c0341ec5ac4e4f48dea4075500ab861-webp-product.jpg"));


                promosi1Base.add(new ItemModel(
                        UUID.randomUUID().toString(),
                        category.Id,
                        "LG-F05NXA- Putih",
                        feedback,
                        3100000,
                        "http://id-live-02.slatic.net/p/15/lg-f05nxa-putih-6673-12752931-76dcded91fccd25ae8c0bef9bb4ab89d-webp-product.jpg"));

                promosi1Base.add(new ItemModel(
                        UUID.randomUUID().toString(),
                        category.Id,
                        "Samsung AC 1/2 PK AR05HCFLAW - Putih",
                        feedback,
                        2475000,
                        "http://id-live-02.slatic.net/p/15/samsung-ac-1-2-pk-ar05hcflaw-putih-khusus-jabodetabek-2805-5580821-bfbd0556cb5d4ddf29481c475459a97b-webp-product.jpg"));

                promosi1Base.add(new ItemModel(
                        UUID.randomUUID().toString(),
                        category.Id,
                        "Samsung AR09KRFSVURN AC Low Watt ",
                        feedback,
                        3293000,
                        "http://id-live-02.slatic.net/p/15/samsung-ar09krfsvurn-ac-low-watt-1pk-650watt-putih-produk-baru-garansi-10-tahun-kompressor-3405-43739001-7ba22b4e1ff0f669b5062f0dbcbf4e80-webp-product.jpg"));


                promosi1Base.add(new ItemModel(
                        UUID.randomUUID().toString(),
                        category.Id,
                        "Daikin AC FTNE15JEV 1/2 PK Standart ",
                        feedback,
                        2993000,
                        "http://id-live-02.slatic.net/p/15/daikin-ac-ftne15jev-1-2-pk-standart-r410-putih-6646-7257089-4c0341ec5ac4e4f48dea4075500ab861-webp-product.jpg"));


                promosi1Base.add(new ItemModel(
                        UUID.randomUUID().toString(),
                        category.Id,
                        "LG-F05NXA- Putih",
                        feedback,
                        3100000,
                        "http://id-live-02.slatic.net/p/15/lg-f05nxa-putih-6673-12752931-76dcded91fccd25ae8c0bef9bb4ab89d-webp-product.jpg"));
                return;
            case "Alat-alat Listrik":
                url1 = "http://www.portalmartin.com/wp-content/uploads/2015/07/Solder-Listrik-AC-60W-AC-220-240V-3.jpg";
                break;
            case "Alat Rumah Tangga":
                url1 = "http://id-live-01.slatic.net/p/3/ben-furniture-lemari-pakaian-all-white-3-pintu-kaca-dan-laci-jabodetabek-only-4933-21119901-2ff30d8b545e7badd23d5ee6d25aad88.jpg";
                break;
            case "Audio Sistem":
                url1 = "http://www.lg.com/in/images/home-entertainment/md05200860/gallery/DH3140-Large-940x620.jpg";
                break;
            case "Category Standard":
                url1 = "http://id-live-03.slatic.net/p/7/lbag-sepatu-sepeda-motor-all-bike-ap-boots-hujan-allbike-100-original-439-6587-5474287-bbf240b11fef0da8edc90251a41f9116.jpg";
                break;
            case "HP":
                url1 = "http://www.lg.com/us/images/cell-phones/md05776887/md05776887-350x350.jpg";
                break;
            case "Kulkas":
                url1 = "http://sinarmaju.co.id/image/data/kulkas/samsung/RT-20FAR.jpg";
                break;
            case "Lain-lain":
                url1 = "http://4.bp.blogspot.com/_Exv06G8EL1w/TEqnj5cAnII/AAAAAAAAAM4/mGcfREZrVqU/s1600/P1010075.JPG";
                break;
            case "Mesin Cuci":
                url1 = "http://4.bp.blogspot.com/-ADx81ZUPIfg/VdXx8kOV7fI/AAAAAAAAAN8/bmZJ0a1nGAo/s1600/Harga-mesin-cuci-sanken-front-loading-terbaru.jpg";
                break;

            case "Televisi":
                url1 = "http://3.bp.blogspot.com/-RqVwYlFILxU/VU3j2W62pLI/AAAAAAAAAbY/dZAJ156dZDw/s1600/tv%2Bdigital.jpg";
                break;
        }

        for (int i = 0; i < 20; i++) {
            ItemModel commodity = new ItemModel(
                    UUID.randomUUID().toString(),
                    category.Id,
                    title + " " + i,
                    feedback,
                    i * 1000 + 1200000  ,
                    url1);
            promosi1Base.add(commodity);
        }

        //tv_promosi1.setText("Promosi peralatan komputer");

        //getExternalStorageWritePermission();
        //Util.writeToFile("Category", "ddd");
        //Util.writeToFile("Commodity", promosiBase1.toString());
        //Util.writeToFile("Seller", sellerBase.toString());
        //Util.writeToFile("Address", addressBase.toString());
    }

}
