package icatalog.pkp.faisal.icatalog.Activity;

import android.content.DialogInterface;
import android.media.Image;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.daimajia.slider.library.Animations.DescriptionAnimation;
import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.TextSliderView;
import com.daimajia.slider.library.Tricks.ViewPagerEx;
import com.midtrans.sdk.corekit.callback.TransactionFinishedCallback;
import com.midtrans.sdk.corekit.core.MidtransSDK;
import com.midtrans.sdk.corekit.core.TransactionRequest;
import com.midtrans.sdk.corekit.models.BankType;
import com.midtrans.sdk.corekit.models.ItemDetails;
import com.midtrans.sdk.corekit.models.snap.CreditCard;
import com.midtrans.sdk.corekit.models.snap.TransactionResult;
import com.midtrans.sdk.scancard.ScanCard;
import com.midtrans.sdk.uikit.SdkUIFlowBuilder;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

import icatalog.pkp.faisal.icatalog.Adapter.CartAdapter;
import icatalog.pkp.faisal.icatalog.Adapter.ItemAdapter;
import icatalog.pkp.faisal.icatalog.Model.CategoryModel;
import icatalog.pkp.faisal.icatalog.Model.ItemModel;
import icatalog.pkp.faisal.icatalog.R;

public class ItemDetailActivity extends AppCompatActivity implements TransactionFinishedCallback {
    private String title;
    private String photoUrl;
    private String price;
    private SliderLayout mHomeSlider;
    private static String BASE_URL = "VT-server-CTHASYpq-X4DNxQF1sgnXCdi";
    private static String CLIENT_KEY = "VT-client-URgym5mu72xnablE";
    AlertDialog alert = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_detail);

        SdkUIFlowBuilder.init(this, CLIENT_KEY, BASE_URL, new TransactionFinishedCallback() {
            @Override
            public void onTransactionFinished(TransactionResult result) {
                // Handle finished transaction here.
            }
        }).setExternalScanner(new ScanCard())
                .buildSDK();


        Bundle b = getIntent().getExtras();
        if (b != null) {
            title = b.getString("title");
            photoUrl = b.getString("photoUrl");
            price = b.getString("price");
        }
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        TextView tv = (TextView) findViewById(R.id.price);
        tv.setText("Rp. " + price);
        toolbar.setTitle(title);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(false);

        makeSlider(photoUrl);
        initMidtransSDK();
    }

    private void makeSlider(String photoUrl) {
        mHomeSlider = (SliderLayout) findViewById(R.id.home_slider);

        HashMap<String, String> url_maps = new HashMap<String, String>();
        url_maps.put("Photo1", photoUrl);
        url_maps.put("Photo2", photoUrl);
        url_maps.put("Photo3", photoUrl);
        url_maps.put("Photo4", photoUrl);

//        HashMap<String, Integer> file_maps = new HashMap<String, Integer>();
//        file_maps.put("Hannibal", R.drawable.hannibal);
//        file_maps.put("Big Bang Theory", R.drawable.bigbang);
//        file_maps.put("House of Cards", R.drawable.house);
//        file_maps.put("Game of Thrones", R.drawable.game_of_thrones);

        for (String name : url_maps.keySet()) {
            TextSliderView textSliderView = new TextSliderView(getApplicationContext());
            // initialize a SliderLayout
            textSliderView
                    .description(name)
                    .image(url_maps.get(name))
                    .setScaleType(BaseSliderView.ScaleType.Fit)
                    .setOnSliderClickListener(new BaseSliderView.OnSliderClickListener() {
                        @Override
                        public void onSliderClick(BaseSliderView slider) {
                            //Snackbar.make(, slider.getBundle().get("extra") + "", Snackbar.LENGTH_SHORT).show();
                        }
                    });

            //add your extra information
            textSliderView.bundle(new Bundle());
            textSliderView.getBundle()
                    .putString("extra", name);

            mHomeSlider.addSlider(textSliderView);
        }
        mHomeSlider.setPresetTransformer(SliderLayout.Transformer.Default);
        mHomeSlider.setPresetIndicator(SliderLayout.PresetIndicators.Center_Bottom);
        mHomeSlider.setCustomAnimation(new DescriptionAnimation());
        mHomeSlider.setDuration(4000);
        mHomeSlider.addOnPageChangeListener(new ViewPagerEx.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    public void btnPreOrder(View view) {
        Log.d("", "dor");
        showAlert();
    }

    private void showAlert() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater inflater = this.getLayoutInflater();
        View view = inflater.inflate(R.layout.alert_hasil_penugasan, null);

        ImageView photo = (ImageView) view.findViewById(R.id.iv_commodity_photo);
        TextView name = (TextView) view.findViewById(R.id.tv_commodity_name);
        TextView mPrice = (TextView) view.findViewById(R.id.price);


        name.setText(title);
        mPrice.setText(price);

        Picasso.with(getApplicationContext())
                .load(photoUrl)
                .into(photo);

        builder.setView(view);
//        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
//
//            @Override
//            public void onClick(DialogInterface dialog, int which) {
//                dialog.cancel();
//            }
//        });

//        builder.setPositiveButton("Done", new DialogInterface.OnClickListener() {
//
//            @Override
//            public void onClick(DialogInterface dialog, int which) {
//
//
//            }
//        });

        alert = builder.create();

        alert.show();
    }

    public void addToCartBtn(View view) {
        MidtransSDK.getInstance().startPaymentUiFlow(this);

//        TransactionRequest transactionRequest = new TransactionRequest(String.valueOf(Math.random()), Double.parseDouble(price));
//
//        String ITEM_ID_1 = String.valueOf(Math.random());
//        int ITEM_PRICE_1 = Integer.valueOf(price);
//        int ITEM_QUANTITY_1 = 1;
//        String ITEM_NAME_1 = title;
//
//        ItemDetails itemDetails1 = new ItemDetails(ITEM_ID_1, ITEM_PRICE_1, ITEM_QUANTITY_1, ITEM_NAME_1);
//
//        ArrayList<ItemDetails> itemDetailsList = new ArrayList<>();
//        itemDetailsList.add(itemDetails1);
//
//// Set item details into the transaction request.
//        transactionRequest.setItemDetails(itemDetailsList);
//
//        MidtransSDK.getInstance().setTransactionRequest(transactionRequest);
//
//        CreditCard creditCardOptions = new CreditCard();
//// Set to true if you want to save card to Snap
//        creditCardOptions.setSaveCard(false);
//// Set to true to save card token as `one click` token
//        creditCardOptions.setSecure(false);
//// Set acquiring bank (Optional)
//        creditCardOptions.setBank(BankType.BCA);
//// Set MIGS channel (ONLY for BCA and Maybank Acquiring bank)
//        creditCardOptions.setChannel(CreditCard.MIGS);
//// Set Credit Card Options
//        transactionRequest.setCreditCard(creditCardOptions);
//// Set card payment info
//        transactionRequest.setCardPaymentInfo(getApplicationContext().getString(R.string.card_click_type_none), false);
//// Set transaction request into SDK instance
//        MidtransSDK.getInstance().setTransactionRequest(transactionRequest);
//
//        MidtransSDK.getInstance().startPaymentUiFlow(this);

//        alert.dismiss();

    }

    private void initMidtransSDK() {
        MidtransSDK.getInstance().setTransactionFinishedCallback(this);
    }

    public void closeAlert(View view) {
        alert.dismiss();
    }

    @Override
    public void onTransactionFinished(TransactionResult transactionResult) {

    }
}
