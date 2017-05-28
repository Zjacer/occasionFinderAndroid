package occasionfinder.zjacer.com.occasionfinderandroid;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import org.json.JSONException;

import java.util.HashMap;
import java.util.Map;

public class AltoFragment extends Fragment {

    private final DataGatherer dataGathered = new DataGatherer();
    private Map<String, String> shopData = new HashMap<String, String>();

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

        if(shopData.isEmpty()) {
            try {
                shopData = dataGathered.getShopData("alto");
                Log.d("SKLEP", shopData.toString());
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        TextView tv = (TextView) rootView.findViewById(R.id.alto_textView);
        tv.setText(shopData.get("altoItemName"));
        ImageView iv = (ImageView) rootView.findViewById(R.id.alto_imageView2);
        new DownloadImageTask(iv).execute(shopData.get("altoItemImageUrl"));

        iv.getLayoutParams().width = 500;
        iv.getLayoutParams().height = 500;
        iv.setAdjustViewBounds(true);

        return rootView;
    }
}
