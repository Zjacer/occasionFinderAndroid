package occasionfinder.zjacer.com.occasionfinderandroid;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;

/**
 * Created by Zjacer @ zjacer@gmail.com.
 */

// set shop type
// getDataFromJSONFile
// getData

//zadowolenie.pl
//https://query.yahooapis.com/v1/public/yql?q=select%20*%20from%20html%20where%20url%3D'http%3A%2F%2Fwww.zadowolenie.pl%2F'%20and%20xpath%3D'%2F%2F*%5B%40id%3D%22sb-site%22%5D%2Fmain%2Fdiv%5B2%5D%2Fdiv%5B1%5D%2Fdiv%2Fdiv%2Fsection%2Fdiv%2Fdiv%2Fdiv%2Fdiv%2Fdiv'&format=json&env=store%3A%2F%2Fdatatables.org%2Falltableswithkeys


public class DataGatherer {

    private String shopName = "ND";
    private JSONObject jsonObject = null;

    Map<String,String> links = new HashMap<String, String>(){{
        put("xkom","https://query.yahooapis.com/v1/public/yql?q=select%20*%20from%20html%20where%20url%3D'https%3A%2F%2Fx-kom.pl'%20and%20xpath%3D'%2F%2F*%5B%40id%3D%22hotShot%22%5D%2Fdiv%5B2%5D%20%7C%20%2F%2F*%5B%40id%3D%22pageWrapper%22%5D%2Fdiv%5B4%5D%2Fdiv%5B1%5D%2Fdiv%5B1%5D%2Fscript%2Ftext()'&format=json&env=store%3A%2F%2Fdatatables.org%2Falltableswithkeys");
        put("morele","test");
        put("zadowolenie", "https://query.yahooapis.com/v1/public/yql?q=select%20*%20from%20html%20where%20url%3D%27http%3A%2F%2Fwww.zadowolenie.pl%2F%27%20and%20xpath%3D%27%2F%2F*%5B%40id%3D%22sb-site%22%5D%2Fmain%2Fdiv%5B2%5D%2Fdiv%5B1%5D%2Fdiv%2Fdiv%2Fsection%2Fdiv%2Fdiv%2Fdiv%2Fdiv%2Fdiv%27&format=json&env=store%3A%2F%2Fdatatables.org%2Falltableswithkeys");
        put("alto","test");
        put("amfora","test");
    }};

    private void setShop(String shop) {
        this.shopName = shop;
    }

    private JSONObject dataFromUrlToJSONObject(String shopName) {

        try {
            jsonObject = new GetJSONTask().execute(links.get(shopName)).get();
            return jsonObject;
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } finally {
            return jsonObject;
        }
    }

    private Map<String, String> getDataFromJSONobject(JSONObject jsonObject) throws JSONException {
        Map<String, String> shopData = new HashMap<String, String>();
        JSONObject newObj = new JSONObject();

        switch (shopName) {
            case "xkom":
                newObj = jsonObject.getJSONObject("query").getJSONObject("results").getJSONObject("div").getJSONArray("div").getJSONObject(0);
                shopData.put("xkomItemName", newObj.getString("data-product-name"));
                newObj = newObj.getJSONObject("img");
                shopData.put("xkomItemImageUrl", newObj.getString("src"));
                newObj = jsonObject.getJSONObject("query").getJSONObject("results").getJSONObject("div").getJSONArray("div").getJSONObject(1).getJSONArray("div").getJSONObject(0);
                shopData.put("xkomItemOldPrice", newObj.getJSONArray("div").getJSONObject(0).getString("content"));
                shopData.put("xkomItemNewPrice", newObj.getJSONArray("div").getJSONObject(1).getString("content"));

                String strWithUrl = jsonObject.getJSONObject("query").getJSONObject("results").getString("content");
                int urlStart = strWithUrl.indexOf("/goracy_strzal/", 450);
                int urlEnd = strWithUrl.indexOf("\";", urlStart);
                String itemUrl = strWithUrl.substring(urlStart, urlEnd);
                shopData.put("xkomItemLinkUrl", itemUrl);

                Log.d("XXX", "POSZLO");
                return shopData;
            case "zadowolenie":
                newObj = jsonObject.getJSONObject("query").getJSONObject("results").getJSONObject("div").getJSONArray("p").getJSONObject(1).getJSONObject("a");
                shopData.put("zadowolenieItemName", newObj.getString("data-offer-name"));
                shopData.put("zadowolenieItemOldPrice", "ND");
                shopData.put("zadowolenieItemNewPrice", newObj.getString("data-offer-price"));
                shopData.put("zadowolenieItemLinkUrl", newObj.getString("href"));
                newObj = jsonObject.getJSONObject("query").getJSONObject("results").getJSONObject("div").getJSONArray("div").getJSONObject(0).getJSONObject("a").getJSONObject("img");
                shopData.put("zadowolenieItemImageUrl", newObj.getString("src"));
                return shopData;
            default:
                return shopData;
        }
    }

    public Map<String, String> getShopData(String shop) throws JSONException {
        setShop(shop);
        JSONObject jsonObj = dataFromUrlToJSONObject(shop);
        Map<String, String> map = getDataFromJSONobject(jsonObj);
        return map;
    }
}