package imetima.appforrunningtest;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.FrameLayout;
import android.support.design.widget.TabLayout;

public class AppActivity extends AppCompatActivity {

    FrameLayout frameLayout;
    TabLayout tabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app);

        frameLayout = (FrameLayout)findViewById(R.id.frameLayout);
        tabLayout = (TabLayout)findViewById(R.id.tabLayout);

        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.frameLayout, new PocetnaFragment());
        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        ft.commit();

        TabLayout.Tab pocetnaTab = tabLayout.newTab();
        //TODO: imena tabova da se stave u string.xml file
        pocetnaTab.setText("Pocetna");
        //TODO: pocetnaTab.setIcon(R.drawable.ikonica) mozemo da dodajmo custom ikonice
        tabLayout.addTab(pocetnaTab);

        TabLayout.Tab aktivnostTab = tabLayout.newTab();
        aktivnostTab.setText("Aktivnost");
        tabLayout.addTab(aktivnostTab);


        TabLayout.Tab spisakTab = tabLayout.newTab();
        spisakTab.setText("Spisak");
        tabLayout.addTab(spisakTab);


        TabLayout.Tab nalogTab = tabLayout.newTab();
        nalogTab.setText("Nalog");
        tabLayout.addTab(nalogTab);

        tabLayout.setOnTabSelectedListener(new TabLayout.BaseOnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                Fragment fragment = null;

                switch (tab.getPosition()){
                    case 0:
                        fragment = new PocetnaFragment();
                        break;
                    case 1:
                        fragment = new AktivnostFragment();
                        break;
                    case 2:
                        fragment = new SpisakFragment();
                        break;
                    case 3:
                        fragment = new NalogFragment();
                }
                FragmentManager fm = getSupportFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                ft.replace(R.id.frameLayout, fragment);
                ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
                ft.commit();
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

    }
}
