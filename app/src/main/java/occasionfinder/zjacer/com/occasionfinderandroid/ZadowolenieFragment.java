package occasionfinder.zjacer.com.occasionfinderandroid;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

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
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        rootView.setBackgroundColor(Color.WHITE);
        TextView tv1 = (TextView) rootView.findViewById(R.id.zadowolenie_textView);
        TextView tv4 = (TextView) rootView.findViewById(R.id.zadowolenie_textView4);

        tv1.setText(shopData.get("zadowolenieItemName"));
        tv4.setText("Nowa cena: " + shopData.get("zadowolenieItemNewPrice") + " zł");
        tv4.setTypeface(null, Typeface.BOLD_ITALIC);

        ImageView logo = (ImageView) rootView.findViewById(R.id.zadowolenie_imageView);
        ImageView iv = (ImageView) rootView.findViewById(R.id.zadowolenie_imageView2);

        new DownloadImageTask(iv).execute(shopData.get("zadowolenieItemImageUrl"));
        iv.setAdjustViewBounds(true);

        // User can click logo to open shop in web browser
        logo.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_VIEW);
                intent.addCategory(Intent.CATEGORY_BROWSABLE);
                intent.setData(Uri.parse("https://www.zadowolenie.pl"));
                startActivity(intent);
            }
        });

        // User can long click image to open item tab in web browser
        iv.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_VIEW);
                intent.addCategory(Intent.CATEGORY_BROWSABLE);
                intent.setData(Uri.parse(shopData.get("zadowolenieItemLinkUrl")));
                startActivity(intent);
                return true;
            }
        });

        return rootView;
    }
}
