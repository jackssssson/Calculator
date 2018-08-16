package com.example.calculator.activities;


import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.example.calculator.R;
import com.mikepenz.materialdrawer.AccountHeader;
import com.mikepenz.materialdrawer.AccountHeaderBuilder;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.ProfileDrawerItem;
import com.mikepenz.materialdrawer.model.SecondaryDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IProfile;
import com.squareup.picasso.Picasso;


public class MainCalculator extends Activity {

    private static final String ADD_SIGN = "+";
    private static final String SUBTRACT_SIGN = "-";
    private static final String DIVIDE_SIGN = "/";
    private static final String PRODUCT_SIGN = "*";
    private static final String SIGN = "sign";

    private static final int FIRST_INTENT = 1;
    private static final int SECOND_INTENT = 2;
    private static final int THIRD_INTENT = 3;
    private static final int FOURTH_INTENT = 4;

    private Toolbar mToolbar;
    private AccountHeader mHeaderResult;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mToolbar = findViewById(R.id.toolbarId);

        loadImage();
        setUpAccountHeader();
        setUpDrawer();
        loadMusic();
    }

    public void loadMusic() {
        final MediaPlayer mediaPlayer = MediaPlayer.create(this, R.raw.fiki_and_galena);

        Button button = findViewById(R.id.btn_fiki);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mediaPlayer.start();
            }
        });
    }

    private void loadImage() {
        ImageView imageView = findViewById(R.id.imageViewId);
        String image = "https://firebasestorage.googleapis.com/v0/b/blog-fcf89.appspot.com/o/background.jpg?alt=media&token=66daa943-99be-4fcf-a3cd-01277ddb93c4";
        Picasso.get().load(image).into(imageView);
    }

    private void setUpAccountHeader() {


        mHeaderResult = new AccountHeaderBuilder()
                .withActivity(this)
                .withHeaderBackground(R.drawable.header)
                .addProfiles(
                        new ProfileDrawerItem().withName("Zhivko Zhelyazkov")
                                .withEmail("jivko_hinev90@abv.bg").withIcon(getResources()
                                .getDrawable(R.drawable.profile))
                )
                .withOnAccountHeaderListener(new AccountHeader.OnAccountHeaderListener() {
                    @Override
                    public boolean onProfileChanged(View view, IProfile profile, boolean currentProfile) {
                        return false;
                    }
                })
                .build();

    }

    private void setUpDrawer() {
        PrimaryDrawerItem item1 = new PrimaryDrawerItem().withIdentifier(1)
                .withName("Sum");
        SecondaryDrawerItem item2 = new SecondaryDrawerItem().withIdentifier(2)
                .withName("Subtract");
        SecondaryDrawerItem item3 = new SecondaryDrawerItem().withIdentifier(2)
                .withName("Product");
        SecondaryDrawerItem item4 = new SecondaryDrawerItem().withIdentifier(2)
                .withName("Divide");


        Drawer result = new DrawerBuilder()
                .withActivity(this)
                .withAccountHeader(mHeaderResult)
                .withToolbar(mToolbar)
                .addDrawerItems(
                        item1,
                        item2,
                        item3,
                        item4
                )
                .withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
                    @Override
                    public boolean onItemClick(View view, int position, IDrawerItem drawerItem) {
                        switch (position) {
                            case FIRST_INTENT:
                                Intent intentAdd = new Intent(MainCalculator.this, OperationNumbers.class);
                                intentAdd.putExtra(SIGN, ADD_SIGN);
                                startActivity(intentAdd);
                                break;
                            case SECOND_INTENT:
                                Intent intentSubtract = new Intent(MainCalculator.this, OperationNumbers.class);
                                intentSubtract.putExtra(SIGN, SUBTRACT_SIGN);
                                startActivity(intentSubtract);
                                break;
                            case THIRD_INTENT:
                                Intent intentProduct = new Intent(MainCalculator.this, OperationNumbers.class);
                                intentProduct.putExtra(SIGN, PRODUCT_SIGN);
                                startActivity(intentProduct);
                                break;
                            case FOURTH_INTENT:
                                Intent intentDivide = new Intent(MainCalculator.this, OperationNumbers.class);
                                intentDivide.putExtra(SIGN, DIVIDE_SIGN);
                                startActivity(intentDivide);
                                break;
                            default:
                                return false;
                        }

                        return true;
                    }
                })
                .build();
        System.out.println(result);
    }
}
