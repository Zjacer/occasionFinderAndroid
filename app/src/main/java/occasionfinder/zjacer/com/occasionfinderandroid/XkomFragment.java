package occasionfinder.zjacer.com.occasionfinderandroid;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Paint;
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
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        rootView.setBackgroundColor(Color.WHITE);
        TextView tv1 = (TextView) rootView.findViewById(R.id.xkom_textView);
        TextView tv2 = (TextView) rootView.findViewById(R.id.xkom_textView2);
        TextView tv3 = (TextView) rootView.findViewById(R.id.xkom_textView3);
        TextView tv4 = (TextView) rootView.findViewById(R.id.xkom_textView4);

        tv1.setText(shopData.get("xkomItemName"));
        tv2.setText(shopData.get("xkomItemOldPrice"));
        tv2.setPaintFlags(tv2.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
        tv3.setText(shopData.get("xkomItemNewPrice"));
        tv4.setText("Oszczędzasz: " + PriceReductionCalculator.calculateReduction(shopData.get("xkomItemOldPrice"), shopData.get("xkomItemNewPrice")));
        tv4.setTypeface(null, Typeface.BOLD_ITALIC);

        ImageView logo = (ImageView) rootView.findViewById(R.id.xkom_imageView);
        ImageView iv = (ImageView) rootView.findViewById(R.id.xkom_imageView2);

        new DownloadImageTask(iv).execute(shopData.get("xkomItemImageUrl"));
        iv.setAdjustViewBounds(true);

        // User can click logo to open shop in web browser
        logo.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_VIEW);
                intent.addCategory(Intent.CATEGORY_BROWSABLE);
                intent.setData(Uri.parse("https://www.x-kom.pl"));
                startActivity(intent);
            }
        });

        // User can click image to open item tab in web browser
        iv.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_VIEW);
                intent.addCategory(Intent.CATEGORY_BROWSABLE);
                intent.setData(Uri.parse(shopData.get("xkomItemLinkUrl")));
                startActivity(intent);
            }
        });

        return rootView;
    }
}