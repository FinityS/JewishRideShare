package com.finitydev.jewishrideshare;


import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.firebase.ui.auth.AuthUI;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.SecondaryDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;

public class DrawerRootActivity extends AppCompatActivity {


    public static final String POST_RIDE = "postridefragment";
    public static final String SEARCH_RIDE = "searchridefragment";



    Fragment postRideFragment;
    FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drawer_root);

        PrimaryDrawerItem item1 = new PrimaryDrawerItem().withIdentifier(1).withName("Post a ride");
        SecondaryDrawerItem item2 = new SecondaryDrawerItem().withIdentifier(2).withName("Search for a ride");
        SecondaryDrawerItem item3 = new SecondaryDrawerItem().withIdentifier(3).withName("Log out");


        new DrawerBuilder()
                .withActivity(this)
                .withTranslucentStatusBar(false)
                .withActionBarDrawerToggle(false)
                .addDrawerItems(
                        item1,
                        // new DividerDrawerItem(),
                        item2,
                        item3
                )
                .withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
                    @Override
                    public boolean onItemClick(View view, int position, IDrawerItem drawerItem) {
                        onNavDrawerItemSelected((int)drawerItem.getIdentifier());
                        return true;
                    }
                })
                .build();

        fragmentManager = getSupportFragmentManager();



    }



    private void onNavDrawerItemSelected(int identifier) {
        Intent intent;
        switch (identifier){
            //Sign In
            case 1:
                if(postRideFragment == null) {
                    postRideFragment = new PostRideFragment();
                    fragmentManager.beginTransaction()
                            .add(R.id.fragment_container,postRideFragment,POST_RIDE)
                            .commit();

                }
                break;
            //Sign Out
            case 2:
                intent = new Intent(this,MainActivity.class);
                startActivity(intent);
                break;
            case 3:
                AuthUI.getInstance()
                        .signOut(this)
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            public void onComplete(@NonNull Task<Void> task) {
                                // user is now signed out
                                Intent intent = new Intent(DrawerRootActivity.this,MainActivity.class);
                                startActivity(intent);
                                finish();
                            }
                        });

        }


    }












}
