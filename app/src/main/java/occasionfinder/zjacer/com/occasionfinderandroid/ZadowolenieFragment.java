package occasionfinder.zjacer.com.occasionfinderandroid;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.json.JSONException;

import java.util.HashMap;
import java.util.Map;

public class ZadowolenieFragment extends Fragment {

    private final DataGatherer dataGathered = new DataGatherer();
    private Map<String, String> shopData = new HashMap<String, String>();

    public ZadowolenieFragment() {
    }

    public static ZadowolenieFragment newInstance() {
        ZadowolenieFragment fragment = new ZadowolenieFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.zadowolenie_fragment, container, false);

        if(shopData.isEmpty()) {
            try {
                shopData = dataGathered.getShopData("zadowolenie");
                Log.d("SKLEP", shopData.toString());
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        return rootView;
    }
}