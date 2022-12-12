package uk.ac.tees.aad.sujith.eventplanner;

import android.annotation.SuppressLint;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.fragment.app.Fragment;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.time.LocalDate;

import uk.ac.tees.aad.sujith.eventplanner.chat.ChatFragment;
import uk.ac.tees.aad.sujith.eventplanner.fragments.AccountFragment;
import uk.ac.tees.aad.sujith.eventplanner.fragments.EventsFragment;
import uk.ac.tees.aad.sujith.eventplanner.fragments.HomeFragment;
import uk.ac.tees.aad.sujith.eventplanner.fragments.MapsFragment;
import uk.ac.tees.aad.sujith.eventplanner.todo.ToDoFragment;

public class MainActivity extends AppCompatActivity {

    private static MainActivity instance;
    Handler handler;

    public static MainActivity getInstance() {
        return instance;
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_main);
        instance = this;

        BottomNavigationView bottomNavigationView = findViewById(R.id.menu);
        bottomNavigationView.setOnNavigationItemSelectedListener(item -> {
            Fragment fragment = null;
            switch (item.getItemId()) {
                case R.id.home:
                    fragment = new HomeFragment();
                    break;
                case R.id.chat:
                    fragment = new MapsFragment();
                    break;
                case R.id.events:
                    fragment = new EventsFragment();
                    break;
                case R.id.ToDo:
                    fragment = new ToDoFragment();
                    break;
                case R.id.Account:
                    fragment = new AccountFragment();
                    break;
            }
            getSupportFragmentManager().beginTransaction().replace(R.id.fragments, fragment).commit();
            return true;
        });

        getSupportFragmentManager().beginTransaction().replace(R.id.fragments, new HomeFragment()).commit();

    }
}
