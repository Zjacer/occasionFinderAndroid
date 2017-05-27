package occasionfinder.zjacer.com.occasionfinderandroid;

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
    private String[] shopDataArray = {"ND", "ND", "ND", "ND", "ND", "ND"};
    private JSONObject jsonObject = null;
    Map<String, String> shopsData = new HashMap<String, String>();
   public void setShop(String shop) {
        this.shopName = shop;
    }

    private void getDataFromJSONFile() {
        try {
            jsonObject = new GetJSONTask().execute("https://query.yahooapis.com/v1/public/yql?q=select%20*%20from%20html%20where%20url%3D'http%3A%2F%2Fwww.zadowolenie.pl%2F'%20and%20xpath%3D'%2F%2F*%5B%40id%3D%22sb-site%22%5D%2Fmain%2Fdiv%5B2%5D%2Fdiv%5B1%5D%2Fdiv%2Fdiv%2Fsection%2Fdiv%2Fdiv%2Fdiv%2Fdiv%2Fdiv'&format=json&env=store%3A%2F%2Fdatatables.org%2Falltableswithkeys").get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        try {
            transferJSONDataToArray(jsonObject);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void transferJSONDataToArray(JSONObject jsonObject) throws JSONException {
        switch (shopName) {
            case "xkom":
                shopDataArray[0] = "1";
                shopDataArray[1] = "2";
                shopDataArray[2] = "3";
                shopDataArray[3] = "4";
                shopDataArray[4] = "5";
                break;
            case "morele":
                shopDataArray[0] = "1";
                shopDataArray[1] = "2";
                shopDataArray[2] = "3";
                shopDataArray[3] = "4";
                shopDataArray[4] = "5";
                break;
                /*
            [0] - item image src
            [1] - item name
            [2] - item price
            [3] - item price after reduction
            [4] - reduction
            [5] - item url
    */

            case "zadowolenie":
                JSONObject newObj = jsonObject.getJSONObject("query").getJSONObject("results").getJSONObject("div").getJSONArray("p").getJSONObject(1).getJSONObject("a");
                //shopsData.put("zadowolenieItemImageSrc", newObj.getString("data-offer-name"));
                shopDataArray[1] = "No default price";
                shopDataArray[3] = newObj.getString("data-offer-price");
                shopDataArray[4] = "FUNCTION HERE";
                shopDataArray[5] = newObj.getString("href");
                newObj = jsonObject.getJSONObject("query").getJSONObject("results").getJSONObject("div").getJSONArray("div").getJSONObject(0).getJSONObject("a").getJSONObject("img");
                shopDataArray[0] = newObj.getString("src");
                break;
            default:
                shopDataArray[0] = "NIEPRAWIDLOWY SKLEP";
                shopDataArray[1] = "NIEPRAWIDLOWY SKLEP";
                shopDataArray[2] = "NIEPRAWIDLOWY SKLEP";
                shopDataArray[3] = "NIEPRAWIDLOWY SKLEP";
                shopDataArray[4] = "NIEPRAWIDLOWY SKLEP";
        }
    }

    public String[] getData() throws JSONException {
        getDataFromJSONFile();
        transferJSONDataToArray(this.jsonObject);
        return shopDataArray;
    }
}