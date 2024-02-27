package com.example.glidelibrary;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentContainer;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity implements ImageAdapter.OnItemClickListener {

    RecyclerView recyclerView;
    View fragmentHost;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        fragmentHost = findViewById(R.id.fragmentContainer);

        List<Integer> images = Arrays.asList(
                R.drawable.image1,
                R.drawable.image2,
                R.drawable.image3,
                R.drawable.image4,
                R.drawable.image5,
                R.drawable.image6
        );

        int spanCount = 2; // количество столбцов в сетке
        recyclerView.setLayoutManager(new GridLayoutManager(this, spanCount));
        ImageAdapter adapter = new ImageAdapter(this, images, this);
        recyclerView.setAdapter(adapter);
    }

    FragmentTransaction transaction;
    @Override
    public void onItemClick(int imageResId) {
        // Открываем новый фрагмент с выбранным изображением
        ImageFragment fragment = ImageFragment.newInstance(imageResId);
        transaction = getSupportFragmentManager().beginTransaction();

        fragmentHost.setVisibility(View.VISIBLE);
        recyclerView.setVisibility(View.INVISIBLE);
        transaction.replace(R.id.fragmentContainer, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            fragmentHost.setVisibility(View.INVISIBLE);
            recyclerView.setVisibility(View.VISIBLE);
            return true;
        }

        return super.onKeyDown(keyCode, event);
    }
}