package icatalog.pkp.faisal.icatalog.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.daimajia.slider.library.Animations.DescriptionAnimation;
import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.TextSliderView;
import com.daimajia.slider.library.Tricks.ViewPagerEx;
import com.mikepenz.materialdrawer.AccountHeader;
import com.mikepenz.materialdrawer.AccountHeaderBuilder;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.ProfileDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IProfile;

import java.util.HashMap;

import icatalog.pkp.faisal.icatalog.R;

import static java.security.AccessController.getContext;

public class MainActivity extends AppCompatActivity {
    private Drawer navigationDrawer;
    private SliderLayout mHomeSlider;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        makeDrawer(savedInstanceState);


        CardView cartBtn = (CardView) findViewById(R.id.cart_button);
        cartBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), CartActivity.class));
            }
        });
        makeSlider();

        CardView msgBtn = (CardView) findViewById(R.id.message);
        msgBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Message Clicked", Snackbar.LENGTH_LONG).show();
            }
        });

        CardView profileBtn = (CardView) findViewById(R.id.profil);
        profileBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Profile Clicked", Snackbar.LENGTH_LONG).show();
            }
        });

        CardView transactionBtn = (CardView) findViewById(R.id.transaction);
        transactionBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Transaction Clicked", Snackbar.LENGTH_LONG).show();
            }
        });






    }

    private void makeSlider() {
        mHomeSlider = (SliderLayout) findViewById(R.id.home_slider);

        HashMap<String, String> url_maps = new HashMap<String, String>();
        url_maps.put("Top Produk", "https://images-na.ssl-images-amazon.com/images/G/01/aplus/detail-page/B00155T2AS-1.jpg");
        url_maps.put("Promosi", "https://usercontent2.hubstatic.com/5661983_f520.jpg");
        url_maps.put("Promosi", "http://www.lg.com/in/images/home-entertainment/md05200860/gallery/DH3140-Large-940x620.jpg");
        url_maps.put("Populer", "http://seputarhargaterkini.com/wp-content/uploads/2015/09/harga-tv-led-samsung-terbaru-september-2015-1024x699.jpg");

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


    private void makeDrawer(Bundle savedInstanceState) {
        //Toolbar
        final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("I-Catalog");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(false);

//        // Create the AccountHeader
        AccountHeader headerResult = new AccountHeaderBuilder()
                .withActivity(this)
                .withHeaderBackground(R.drawable.material_background)
                .addProfiles(
                        new ProfileDrawerItem()
                                .withName("I-Catalog Demo")
                                .withEmail("Sales name")
                                .withIcon(getResources().getDrawable(R.drawable.avatar))
                )
                .withOnAccountHeaderListener(new AccountHeader.OnAccountHeaderListener() {
                    @Override
                    public boolean onProfileChanged(View view, IProfile profile, boolean currentProfile) {
                        return false;
                    }
                })
                .build();

        //if you want to update the items at a later time it is recommended to keep it in a variable
        navigationDrawer = new DrawerBuilder()
                .withActivity(this)
                .withToolbar(toolbar)
                .withActionBarDrawerToggleAnimated(true)
                .withTranslucentStatusBar(false)
                .withFullscreen(true)
                .withSavedInstance(savedInstanceState)
                .withAccountHeader(headerResult)
                .withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
                    @Override
                    public boolean onItemClick(View view, int position, IDrawerItem drawerItem) {
                        String title = "";
                        if (drawerItem == null) return false;
                        long id = drawerItem.getIdentifier();
                        getSupportActionBar().setElevation(6);
                        switch (Long.toString(id)) {
                            case "0":
                                title = "Air Conditioner";
                                break;
                            case "1":
                                title = "Alat-alat Listrik";
                                break;
                            case "2":
                                title = "Alat Rumah Tangga";
                                break;
                            case "3":
                                title = "Audio Sistem";

                                break;
                            case "4":
                                title = "Category Standard";
                                break;
                            case "5":
                                title = "HP";
                                break;
                            case "6":
                                title = "Kulkas";
                                break;
                            case "7":
                                title = "Lain-lain";
                                break;
                            case "8":
                                title = "Mesin Cuci";
                                break;
                            case "9":
                                title = "Televisi";
                                break;
                        }
                        navigationDrawer.closeDrawer();

                        Intent i = new Intent(getApplicationContext(), ListItemActivity.class);
                        Bundle b = new Bundle();
                        b.putString("title", title);
                        i.putExtras(b);
                        startActivity(i);
                        return true;
                    }
                })

                .build();

        seedDrawer();


    }

    private void seedDrawer() {
        PrimaryDrawerItem a = new PrimaryDrawerItem()
                .withIdentifier(0)
                .withName("Air Conditioner")
                .withIcon(R.drawable.miniicon_ac);
        PrimaryDrawerItem b = new PrimaryDrawerItem()
                .withIdentifier(1)
                .withName("Alat-alat Listrik")
                .withIcon(R.drawable.miniicon_alat_listrik);
        PrimaryDrawerItem c = new PrimaryDrawerItem()
                .withIdentifier(2)
                .withName("Alat Rumah Tangga")
                .withIcon(R.drawable.miniicon_alat_rumah);
        PrimaryDrawerItem d = new PrimaryDrawerItem()
                .withIdentifier(3)
                .withName("Audio Sistem")
                .withIcon(R.drawable.miniicon_audio_system);
        PrimaryDrawerItem e = new PrimaryDrawerItem()
                .withIdentifier(4)
                .withName("Category Standard")
                .withIcon(R.drawable.miniicon_category_standard);
        PrimaryDrawerItem f = new PrimaryDrawerItem()
                .withIdentifier(5)
                .withName("HP")
                .withIcon(R.drawable.miniicon_hp);
        PrimaryDrawerItem g = new PrimaryDrawerItem()
                .withIdentifier(6)
                .withName("Kulkas")
                .withIcon(R.drawable.miniicon_kulkas);
        PrimaryDrawerItem h = new PrimaryDrawerItem()
                .withIdentifier(7)
                .withName("Lain-lain")
                .withIcon(R.drawable.miniicon_lain2);
        PrimaryDrawerItem i = new PrimaryDrawerItem()
                .withIdentifier(8)
                .withName("Mesin Cuci")
                .withIcon(R.drawable.miniicon_mesin_cuci);
        PrimaryDrawerItem j = new PrimaryDrawerItem()
                .withIdentifier(9)
                .withName("Televisi")
                .withIcon(R.drawable.miniicon_television);

        navigationDrawer.addItems(a, b, c, d, e, f, g, i, j, h);

    }

}
