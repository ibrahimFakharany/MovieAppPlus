package exampls.com.movieappplus.View;

import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;

import exampls.com.movieappplus.Controller.MainActivityController;
import exampls.com.movieappplus.Controller.MyRecyclerViewAdapter;
import exampls.com.movieappplus.Controller.OnAdapterFinishIntializing;
import exampls.com.movieappplus.R;

public class MainActivity extends AppCompatActivity implements OnAdapterFinishIntializing {
    RecyclerView recyclerView;
    MainActivityController mainActivityController;

    private static final String TAG = "mainactivity";
    private static final int NUMBER_OF_COLUMNS = 2;

    private int category = 0;

    private final String CATEGORY = "category";

    final int CATEGORY_POPULAR = 1;
    final int CATEGORY_TOP_RATED = 2;
    final int CATEGORY_FAVOURIT = 3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = (RecyclerView) findViewById(R.id.rv_main_movies);
        GridLayoutManager gridLayouManage = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(gridLayouManage);
        if ((category = PreferenceManager.getDefaultSharedPreferences(this).getInt(CATEGORY, 1)) != 0) {
            getCategory(category);
        }


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {

            case R.id.top_rated:

                category = CATEGORY_TOP_RATED;
                getCategory(category);

                break;

            case R.id.popular:

                category = CATEGORY_POPULAR;
                getCategory(category);

                break;
            case R.id.favourit:

                category = CATEGORY_FAVOURIT;
                getCategory(category);

                break;

        }
        return super.onOptionsItemSelected(item);
    }

    private void getCategory(int category) {


        final String POPULAR_STRING = "popular";
        final String TOP_RATED_STRING = "top_rated";


        mainActivityController = new MainActivityController(MainActivity.this);
        mainActivityController.setOnAdapterFinishIntializing(this);

        switch (category) {
            case CATEGORY_POPULAR:
                mainActivityController.getOnlineMovies(POPULAR_STRING);
                break;
            case CATEGORY_TOP_RATED:
                mainActivityController.getOnlineMovies(TOP_RATED_STRING);
                break;
            case CATEGORY_FAVOURIT:
                recyclerView.setAdapter(mainActivityController.getFavouritAdapter());
                break;
        }

    }

    @Override
    protected void onPause() {
        super.onPause();
        PreferenceManager.getDefaultSharedPreferences(this).edit().putInt(CATEGORY, category).apply();
    }

    @Override
    public void passAdapter(MyRecyclerViewAdapter adapter) {

        recyclerView.setAdapter(adapter);

    }


}
