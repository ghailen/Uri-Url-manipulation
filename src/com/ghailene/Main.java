package com.ghailene;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.*;
import java.util.List;
import java.util.Map;

public class Main {

    public static void main(String[] args) {
        try {


            //URL urltest = new URL("http://example.org");
            URL urltest = new URL("https://api.flickr.com/services/feeds/photos_public.gne?tags=cats");
            //URL urltest = new URL("https://api.flickr.com/services/feeds/photos_public.gne?tags=dogs");
            // using HttpURLConnection can replace .connect using after
            HttpURLConnection connection = (HttpURLConnection) urltest.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("User-Agent", "Chrome");
            connection.setReadTimeout(30000);
            int responseCode = connection.getResponseCode();
            System.out.println("Response code :" + responseCode);
            if (responseCode != 200) {
                System.out.println("Error reading web page");
                System.out.println(connection.getResponseMessage());
                return;
            }

            URLConnection urlConnection = urltest.openConnection();
            urlConnection.setDoInput(true);
            urlConnection.connect();

            /***************** get headers from url ***/
            Map<String, List<String>> headerFields = urlConnection.getHeaderFields();
            for (Map.Entry<String, List<String>> entry : headerFields.entrySet()) {
                String key = entry.getKey();
                List<String> value = entry.getValue();
                System.out.println("-------------key = " + key);
                for (String string : value) {
                    System.out.println("value = " + value);
                }
            }

            /***  show the html of the web site using bufferReader  method 1   **/
            BufferedReader inputStream = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
            String line = "";
            while (line != null) {
                line = inputStream.readLine();
                System.out.println(line);
            }
            inputStream.close();

            /***  show the html of the web site using bufferReader  method 2   **/
           /* BufferedReader inputStream = new BufferedReader(new InputStreamReader(urltest.openStream()));
            String line = "";
            while (line != null) {
                line = inputStream.readLine();
                System.out.println(line);
            }
            inputStream.close();*/


            /**    URI can be relative but URL must be absolute **/

            URI baseUriRelative = new URI("http://username:password@myserver.com:5000");
            URI uriRelative = new URI("/catalogue/phones?os=android#samsung");

            URI resolvedURI = baseUriRelative.resolve(uriRelative);

            URI relativizedURI = baseUriRelative.relativize(uriRelative);
            System.out.println("Relative URI =" + relativizedURI);

            URL url = resolvedURI.toURL();
            System.out.println("URL =" + url);

//URI uri = new URI("http://username:password@myserver.com:5000/catalogue/phones?os=android#samsung");
            /** an example how to get info from uri **/
            URI uri = new URI("db://username:password@myserver.com:5000/catalogue/phones?os=android#samsung");
            System.out.println("Scheme = " + uri.getScheme());
            System.out.println("Scheme-specific part  = " + uri.getRawSchemeSpecificPart());
            System.out.println("authority = " + uri.getAuthority());
            System.out.println("user info = " + uri.getUserInfo());
            System.out.println("host = " + uri.getHost());
            System.out.println("port = " + uri.getPort());
            System.out.println("path = " + uri.getPath());
            System.out.println("query = " + uri.getQuery());
            System.out.println("fragment = " + uri.getFragment());

        } catch (URISyntaxException e) {
            System.out.println("URI bad Syntax " + e.getMessage());
        } catch (MalformedURLException e) {
            System.out.println("URL Malformed : " + e.getMessage());
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
