package occasionfinder.zjacer.com.occasionfinderandroid;

import android.os.AsyncTask;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Zjacer @ zjacer@gmail.com.
 */

public class GetJSONTask extends AsyncTask<String, Void, JSONObject> {
    JSONObject jsonObj = null;

    @Override
    protected void onPreExecute() {

    }

    @Override
    protected void onPostExecute(JSONObject jsonObject) {
        super.onPostExecute(jsonObject);
    }

    @Override
    protected JSONObject doInBackground(String... urls) {
        String result = "";

        try {
            URL url = new URL(urls[0]);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.connect();
            InputStream is = conn.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            String s = "";
            while ((s = br.readLine()) != null) {
                result += s;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            jsonObj = new JSONObject(result);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return jsonObj;
    }
}