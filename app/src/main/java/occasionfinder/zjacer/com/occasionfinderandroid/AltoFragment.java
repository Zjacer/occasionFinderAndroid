package occasionfinder.zjacer.com.occasionfinderandroid;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class AltoFragment extends Fragment {

    public AltoFragment() {
    }

    public static AltoFragment newInstance() {
        AltoFragment fragment = new AltoFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.alto_fragment, container, false);

        return rootView;
    }
}
