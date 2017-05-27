package occasionfinder.zjacer.com.occasionfinderandroid;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class AmforaFragment extends Fragment {

    public AmforaFragment() {
    }

    public static AmforaFragment newInstance() {
        AmforaFragment fragment = new AmforaFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.amfora_fragment, container, false);

        return rootView;
    }
}
