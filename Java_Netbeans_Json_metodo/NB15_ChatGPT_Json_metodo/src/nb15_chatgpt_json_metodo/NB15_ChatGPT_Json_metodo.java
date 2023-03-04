/*
    org.apache.http
	https://hc.apache.org/downloads.cgi
	https://jar-download.com/download-handling.php

 */
package nb15_chatgpt_json_metodo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class NB15_ChatGPT_Json_metodo {

    public static String chamadaGPT_Json(String apiKey, String question) throws MalformedURLException, IOException {

        System.out.println("Usando Netbeans15 - retornando Json object");
        System.out.println("**** Testando ChatGPT - EducaCiencia FastCode 27/02/2023 **** ");

        String endpoint = "https://api.openai.com/v1/completions";

        String models = "text-davinci-003";
        String payload = "{\"model\": \"" + models + "\",\"prompt\": \"" + question + "\",\"max_tokens\": 700,\"temperature\": 0.0}";

        URL url = new URL(endpoint);
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("POST");
        con.setRequestProperty("Content-Type", "application/json");
        con.setRequestProperty("Authorization", "Bearer " + apiKey);
        con.setDoOutput(true);

        byte[] out = payload.getBytes();
        int length = out.length;

        con.setFixedLengthStreamingMode(length);
        con.getOutputStream().write(out);

        StringBuilder response;
        try ( BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()))) {
            String inputLine;
            response = new StringBuilder();
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
        }

        System.out.println(response.toString());
        return response.toString();
    }

    public static void main(String[] args) throws IOException {
        String token_Generate = "sk-";
        String pergunta = "Oque é Java ?";

        chamadaGPT_Json(token_Generate, pergunta);
    }

}
