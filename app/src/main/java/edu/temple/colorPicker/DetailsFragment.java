package edu.temple.colorPicker;

import android.graphics.Color;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

public class DetailsFragment extends Fragment {

    public static final String dataKey = "bundle_data_key";

    ImageView colorDisplay;

    public static DetailsFragment newInstance(Bundle bundle) {
        DetailsFragment fragment = new DetailsFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    public DetailsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_details, container, false);

        colorDisplay = (ImageView) v.findViewById(R.id.colorDisplay);

        TextView headerTextView = (TextView) v.findViewById(R.id.detailsHeadingTextView);

        if (getArguments() != null && getArguments().getString(dataKey) != null) {

            String colorName = getArguments().getString(dataKey);

            headerTextView.setText("");



            colorDisplay = (ImageView) v.findViewById(R.id.colorDisplay);

            final String[] colors = getResources().getStringArray(R.array.colors);

            int colorToDisplay = 0;

            if (colorName.equals(colors[0])) {
                colorToDisplay = Color.RED;
            } else if (colorName.equals(colors[1])) {
                colorToDisplay = Color.GREEN;
            } else if (colorName.equals(colors[2])) {
                colorToDisplay = Color.BLUE;
            } else if (colorName.equals(colors[3])) {
                colorToDisplay = Color.CYAN;
            } else if (colorName.equals(colors[4])) {
                colorToDisplay = Color.DKGRAY;
            } else if (colorName.equals(colors[5])) {
                colorToDisplay = Color.WHITE;
            } else if (colorName.equals(colors[6])) {
                colorToDisplay = Color.YELLOW;
            } else if (colorName.equals(colors[7])) {
                colorToDisplay = Color.MAGENTA;
            }

            colorDisplay.setBackgroundColor(colorToDisplay);
            headerTextView.setBackgroundColor(colorToDisplay);
        }
        return v;
    }

    public void setImageVisibility(boolean visibility) {
        if (colorDisplay != null) {
            if (visibility)
                colorDisplay.setVisibility(View.VISIBLE);
            else
                colorDisplay.setVisibility(View.INVISIBLE);
        }
    }

    public boolean isImageVisibile(){
        if (colorDisplay != null)
            return colorDisplay.getVisibility() == View.VISIBLE;
        else
            return false;
    }

}
