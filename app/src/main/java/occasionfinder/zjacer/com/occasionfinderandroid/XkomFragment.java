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

public class XkomFragment extends Fragment {

    private final DataGatherer dataGathered = new DataGatherer();
    private Map<String, String> shopData = new HashMap<String, String>();

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

        if(shopData.isEmpty()) {
            try {
                shopData = dataGathered.getShopData("xkom");
                Log.d("SKLEP", shopData.toString());
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        TextView tv = (TextView) rootView.findViewById(R.id.xkom_textView);
        tv.setText(shopData.get("xkomItemName"));

        new DownloadImageTask((ImageView) rootView.findViewById(R.id.xkom_imageView2))
                .execute(shopData.get("xkomItemImageUrl"));

        return rootView;
    }
}