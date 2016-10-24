package edu.temple.colorPicker;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends Activity implements NavFragment.OnFragmentInteractionListener {

    boolean twoPanes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        twoPanes = (findViewById(R.id.fragment_details) != null);

        Fragment navFragment = new NavFragment();

        Bundle bundle = new Bundle();
        bundle.putStringArray(NavFragment.dataKey, getResources().getStringArray(R.array.colors));
        navFragment.setArguments(bundle);

        loadFragment(R.id.fragment_nav, navFragment, false);

        if (twoPanes){
            loadFragment(R.id.fragment_details, new DetailsFragment(), false);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Fragment fragment;
        if (twoPanes)
            fragment = getFragmentManager().findFragmentById(R.id.fragment_details);
        else
            fragment = getFragmentManager().findFragmentById(R.id.fragment_nav);

        if (item.getItemId() == R.id.action_toggle_image && fragment instanceof DetailsFragment){
            ((DetailsFragment) fragment)
                    .setImageVisibility(!((DetailsFragment) fragment).isImageVisibile());
        }

        return super.onOptionsItemSelected(item);
    }

    private void loadFragment(int paneId, Fragment fragment, boolean placeOnBackstack){
        FragmentManager fm = getFragmentManager();
        FragmentTransaction ft = fm.beginTransaction()
                .replace(paneId, fragment);
        if (placeOnBackstack)
                ft.addToBackStack(null);
        ft.commit();

        fm.executePendingTransactions();
    }

    @Override
    public void displayColorInfo(String colorName) {
        DetailsFragment detailsFragment = new DetailsFragment();

        Bundle bundle = new Bundle();
        bundle.putString(DetailsFragment.dataKey, colorName);
        detailsFragment.setArguments(bundle);

        loadFragment(twoPanes ? R.id.fragment_details : R.id.fragment_nav, detailsFragment, !twoPanes);
    }

}
