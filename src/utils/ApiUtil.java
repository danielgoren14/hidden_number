package utils;


import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class ApiUtil {

    public static JSONObject sendApiGetRequest(String path) {
        return sendApiGetRequest(path, new HashMap<>());
    }

    public static JSONObject sendApiGetRequest(String path, Map<String, String> params) {
        JSONObject jsonObject = null;
        StringBuilder result = new StringBuilder();
        try {
            if (params == null) {
                params = new HashMap<>();
            }
            StringBuilder paramBuilder = new StringBuilder();
            for (Map.Entry<String, String> entry : params.entrySet()) {
                if (paramBuilder.length() != 0) paramBuilder.append(Constants.AND);
                paramBuilder.append(entry.getKey()).append(Constants.EQUALS).append(entry.getValue());
            }

            URL fullUrl = new URL(Constants.REST_DOMAIN + path + Constants.QUESTION_MARK + paramBuilder);
            HttpURLConnection conn = (HttpURLConnection) fullUrl.openConnection();

            conn.setRequestMethod(Constants.HTTP_REQUEST_GET);

            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String inputLine;

            while ((inputLine = in.readLine()) != null) {
                result.append(inputLine);
            }
            in.close();
            jsonObject = new JSONObject(result.toString());
        } catch (Exception ex) {
            jsonObject = new JSONObject();
            ex.printStackTrace();
        }
        return jsonObject;
    }

    public static void submit (String userId, int hiddenNumber) {
        Map<String, String> params = new HashMap<>();
        params.put("userId", userId);
        params.put("hiddenNumber", String.valueOf(hiddenNumber));
        ApiUtil.sendApiGetRequest("/fm1/hidden-number" ,params);
        System.out.println("Your answer was submitted!");
    }

}
