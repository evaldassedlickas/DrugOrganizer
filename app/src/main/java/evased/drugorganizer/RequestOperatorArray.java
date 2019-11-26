package evased.drugorganizer;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class RequestOperatorArray extends Thread {

    public interface RequestOperatorListener{
        void success (int dataCount);
        void failed (int responseCode);
    }

    private RequestOperatorListener listener;
    private int responseCode;

    public void setListener (RequestOperatorListener listener){
        this.listener = listener;
    }


    @Override
    public void run(){
        super.run();
        try{
            int size = request();

            if (size > 0){
                success(size);
            }
            else{
                failed(responseCode);
            }
        }
        catch (IOException e){
            failed(-1);
        }
        catch (JSONException e){
            failed(-2);
        }
    }


    private int request() throws IOException, JSONException{
        URL obj = new URL("http://jsonplaceholder.typicode.com/posts");
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        con.setRequestMethod("GET");

        con.setRequestProperty("Content-Type", "application/json");
        responseCode = con.getResponseCode();

        InputStreamReader streamReader;

        if (responseCode == 200){
            streamReader = new InputStreamReader(con.getInputStream());
        }
        else{
            streamReader = new InputStreamReader(con.getErrorStream());
        }

        BufferedReader in = new BufferedReader(streamReader);
        String inputLine;
        StringBuffer response = new StringBuffer();

        while ((inputLine = in.readLine()) != null){
            response.append(inputLine);
        }
        in.close();

        if (responseCode == 200){
            return parsingJsonObject((response.toString()));
        }
        else{
            return -1;
        }
    }

    public int parsingJsonObject(String response) throws JSONException{
    int size = new JSONArray(response).length();
        return size;
    }

    private void failed(int code){
        if (listener != null){
            listener.failed(code);
        }
    }

    private void success(int count){
        if (listener != null){
            listener.success(count);
        }
    }
}
