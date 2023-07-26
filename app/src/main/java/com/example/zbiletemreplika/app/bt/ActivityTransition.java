package com.example.zbiletemreplika.app.bt;
import android.os.Bundle;
import android.os.PersistableBundle;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import com.example.zbiletemreplika.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;


public class ActivityTransition extends AppCompatActivity {


    private HomePageFragment homePageFragment;
    private BuyFragment buyFragment;
    private MyTicketsFragment myTicketsFragment;
    private MenuFragment menuFragment;
    private BottomNavigationView bottomNavView;



    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transition);

        homePageFragment = new HomePageFragment();
        buyFragment = new BuyFragment();
        myTicketsFragment = new MyTicketsFragment();
        menuFragment = new MenuFragment();
        bottomNavView = findViewById(R.id.bottomNavView);


        getSupportFragmentManager().beginTransaction().replace(R.id.container, homePageFragment).commit();

        bottomNavView.setOnItemSelectedListener(item -> {
            switch (item.getItemId()) {

                case R.id.home:
                    getSupportFragmentManager().beginTransaction().replace(R.id.container, homePageFragment).commit();

                case R.id.buy:
                    getSupportFragmentManager().beginTransaction().replace(R.id.container, buyFragment).commit();
                    return true;

                case R.id.myTickets:
                    getSupportFragmentManager().beginTransaction().replace(R.id.container, myTicketsFragment).commit();
                    return true;

                case R.id.menu:
                    getSupportFragmentManager().beginTransaction().replace(R.id.container, menuFragment).commit();
                    return true;

            }
            return false;
        });

    }

}
