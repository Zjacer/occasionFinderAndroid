package occasionfinder.zjacer.com.occasionfinderandroid;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class XkomFragment extends Fragment {

    public XkomFragment() {
    }

    public static XkomFragment newInstance() {
        XkomFragment fragment = new XkomFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.xkom_fragment, container, false);

        return rootView;
    }
}