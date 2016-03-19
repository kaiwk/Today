package io.github.kermit95.today.main.view;

import android.os.Bundle;
import android.support.annotation.DrawableRes;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.ListView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import butterknife.Bind;
import butterknife.ButterKnife;
import io.github.kermit95.today.R;
import io.github.kermit95.today.fluxbase.Dispatcher;
import io.github.kermit95.today.main.MainActionCreator;
import io.github.kermit95.today.main.MainStore;
import io.github.kermit95.today.todo.view.TodoFragment;
import io.github.kermit95.today.weather.view.WeatherFragment;

public class MainActivity extends AppCompatActivity implements WeatherFragment.Callback {


    @Bind(R.id.toolbar_main)
    Toolbar mToolbar;
    @Bind(R.id.drawerlayout_main)
    DrawerLayout mDrawerlayout;
    @Bind(R.id.list_menu)
    ListView mListView;
    @Bind(R.id.img_left_menu)
    ImageView mImgLeftMenu;

    private Fragment mFragment;
    private ActionBarDrawerToggle mDrawerToggle;


    private Dispatcher mDispatcher;
    private MainActionCreator mActionCreator;
    private MainStore mMainStore;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        setUpView();
        setUpFragment();
        initDependencies();
    }

    private void initDependencies() {
        mDispatcher = Dispatcher.get(EventBus.getDefault());
        mActionCreator = MainActionCreator.getInstance(mDispatcher);
        mMainStore = MainStore.getInstance(mDispatcher);
    }

    private void setUpFragment() {
        Fragment fragment = TodoFragment.newInstance();
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.main_container, fragment)
                .commit();
    }

    protected void setUpView() {

        ButterKnife.bind(this);

        //toolbar and drawer
        setSupportActionBar(mToolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        mDrawerToggle = new ActionBarDrawerToggle(this,
                mDrawerlayout,
                mToolbar,
                R.string.drawer_open, R.string.drawer_close);
        mDrawerlayout.addDrawerListener(mDrawerToggle);

        //left list menu
        mListView.setAdapter(new LeftMenuAdapter(this));
        mListView.setOnItemClickListener((parent, view, position, id) -> {
            switch (position) {
                case 0:
                    mFragment = TodoFragment.newInstance();
                    break;
                case 1:
                    mFragment = WeatherFragment.newInstance();
                    break;
                case 2:
                    break;
            }
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.main_container, mFragment)
                    .commit();
            mDrawerlayout.closeDrawers();
        });
    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        mDrawerToggle.syncState();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.item_menu_setting:
                break;
            case R.id.item_menu_sync:
                break;
            default:
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mDispatcher.register(this);
        mDispatcher.register(mMainStore);
    }

    @Subscribe
    public void onMainStoreChange(MainStore.MainStoreChangeEvent event){}

    @Override
    protected void onPause() {
        super.onPause();
        mDispatcher.unregister(this);
        mDispatcher.unregister(mMainStore);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mDrawerlayout.removeDrawerListener(mDrawerToggle);
        ButterKnife.unbind(this);
    }

    @Override
    public void getWeatherIcon(@DrawableRes int picId) {
        mImgLeftMenu.setImageResource(picId);
    }
}
