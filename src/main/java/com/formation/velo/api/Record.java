package com.formation.velo.api;



import com.formation.velo.api.client.Field;
import com.google.gson.annotations.SerializedName;
import lombok.Getter;
import lombok.Setter;

import java.io.DataOutputStream;
import java.net.HttpURLConnection;
import java.net.URL;


@Getter
@Setter
public class Record {

    @SerializedName("recordid")
    private String recordId;
    @SerializedName("fields")
    private Field field;


    public static void main(String[] args) throws Exception {

        URL url = new URL("https://data.economie.gouv.fr/explore/dataset/prix-carburants-fichier-instantane-test-ods-copie/api/?q=nantes");
        HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();

        httpURLConnection.setRequestMethod("POST");
        httpURLConnection.setRequestProperty("Accept-Language", "en-US,en;q=0.5");
        httpURLConnection.setDoOutput(true);
        DataOutputStream wr = new DataOutputStream(httpURLConnection.getOutputStream());

        String serializedMessage = "{}";
        wr.writeBytes(serializedMessage);
        wr.flush();
        wr.close();

        int responseCode = httpURLConnection.getResponseCode();
        System.out.println(responseCode);
    }

}
