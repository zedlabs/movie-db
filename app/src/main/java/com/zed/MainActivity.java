package com.zed;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.View;

import com.mikepenz.materialdrawer.AccountHeader;
import com.mikepenz.materialdrawer.AccountHeaderBuilder;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.model.DividerDrawerItem;
import com.mikepenz.materialdrawer.model.SecondaryDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SecondaryDrawerItem item1 = new SecondaryDrawerItem().withIdentifier(1).withName("popular");
        SecondaryDrawerItem item2 = new SecondaryDrawerItem().withIdentifier(2).withName("favorites");
        SecondaryDrawerItem item3 = new SecondaryDrawerItem().withIdentifier(3).withName("profile");

        AccountHeader headerResult = new AccountHeaderBuilder().withActivity(this)
                .withHeaderBackground(R.drawable.ic_launcher_background).build();

        Toolbar toolbar = findViewById(R.id.toolbar);

        Drawer result = new DrawerBuilder()
                .withActivity(this)
                .withToolbar(toolbar)
                .addDrawerItems(item1, new DividerDrawerItem(),item2, new DividerDrawerItem(), item3,new DividerDrawerItem())
                .withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
                    @Override
                    public boolean onItemClick(View view, int position, IDrawerItem drawerItem) {
                        switch ((int) drawerItem.getIdentifier()){
                            case 1:{}
                            case 2:{}
                            case 3:{}
                        }
                        return false;
                    }
        }).withAccountHeader(headerResult)
                .withSelectedItem(-1)
                .build();
    }
}
