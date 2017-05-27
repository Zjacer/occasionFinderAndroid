package occasionfinder.zjacer.com.occasionfinderandroid;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class MoreleFragment extends Fragment {

    public MoreleFragment() {
    }

    public static MoreleFragment newInstance() {
        MoreleFragment fragment = new MoreleFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.morele_fragment, container, false);

        return rootView;
    }
}