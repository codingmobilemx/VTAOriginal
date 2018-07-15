package com.vta.codingmobile.vtamovil;

import android.content.Context;
import android.os.Bundle;
import android.preference.PreferenceActivity;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.vta.codingmobile.vtamovil.Adapters.NewProductsAdapter;
import com.vta.codingmobile.vtamovil.Adapters.ProductsAdapter;
import com.vta.codingmobile.vtamovil.DTO.Responses.ProductsResponseDTO;
import com.vta.codingmobile.vtamovil.Fragments.NewProductsFragment;
import com.vta.codingmobile.vtamovil.Fragments.ProgressBarFragment;
import com.vta.codingmobile.vtamovil.Helpers.Constants;
import com.vta.codingmobile.vtamovil.Helpers.Enumerators.TypeImage;
import com.vta.codingmobile.vtamovil.Model.Clases.Product;
import com.vta.codingmobile.vtamovil.Services.ApiServices;
import com.vta.codingmobile.vtamovil.Services.DataService;
import com.vta.codingmobile.vtamovil.Services.Mock;

import org.json.JSONArray;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import cz.msebera.android.httpclient.Header;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    private static final String TAG = MainActivity.class.getSimpleName();

    public static Context context;
    public static MainActivity mainActivity;

    List<Product> products = new ArrayList<>();
    ProductsAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        context = this;
        mainActivity = this;

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


        List<android.support.v4.app.Fragment> fragments = new ArrayList<>();
        fragments.add(new NewProductsFragment());
        fragments.add(new NewProductsFragment());
        fragments.add(new NewProductsFragment());

       /* ViewPager viewPagerNewProducts = findViewById(R.id.viewPagerNewProducts);
        NewProductsAdapter productsAdapter = new NewProductsAdapter(this.getSupportFragmentManager(), fragments);
        viewPagerNewProducts.setAdapter(productsAdapter);
*/
        RecyclerView recyclerView = findViewById(R.id.recycleViewProducts);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(gridLayoutManager);


        adapter = new ProductsAdapter(products);
        recyclerView.setAdapter(adapter);
        getProducts();


    }

    public void getProducts() {
        try {
            ProgressBarFragment.getInstance().show();
            String filter = String.format(Constants.GETALL_PRODUCTS, 0, TypeImage.SMALL.getName());
            ApiServices.get(Constants.URI_BASE + filter, new JsonHttpResponseHandler() {
                @Override
                public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                    super.onSuccess(statusCode, headers, response);

                    ProductsResponseDTO productsResponseDTO = new Gson().fromJson(response.toString(), ProductsResponseDTO.class);
                    if (productsResponseDTO.getSuccess()) {
                        products.addAll(productsResponseDTO.data);
                        adapter.notifyDataSetChanged();
                    }
                    ProgressBarFragment.getInstance().dismiss();
                }

                @Override
                public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                    super.onFailure(statusCode, headers, throwable, errorResponse);
                    ProgressBarFragment.getInstance().dismiss();
                }

            });

        } catch (Exception ex) {
            Log.e(TAG, "Error", ex);
            ProgressBarFragment.getInstance().dismiss();
        }
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        switch (id) {
            case R.id.nav_camera:
                // Handle the camera action
                break;
            case R.id.nav_gallery:

                break;
            case R.id.nav_slideshow:

                break;
            case R.id.nav_manage:

                break;
            case R.id.nav_share:

                break;
            case R.id.nav_send:

                break;
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
