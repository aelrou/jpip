package app;

import javax.net.ssl.*;
import java.io.*;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.cert.X509Certificate;

public class WrapHttpsConnect {

    public static String useSslSocket(String host, String resource) throws NoSuchAlgorithmException, KeyManagementException, IOException, WrapHttpsConnectException {

        if (host == null || host.trim().isEmpty()) {
            throw new WrapHttpsConnectException("Host cannot be empty. Example: www.website.com");
        }
        if (resource == null || resource.trim().isEmpty()) {
            return "/";
        }

        TrustManager[] trustAllCert = new TrustManager[]{
                new X509TrustManager() {
                    public X509Certificate[] getAcceptedIssuers() {
                        return null;
                    }

                    public void checkClientTrusted(X509Certificate[] certs, String authType) {
                    }

                    public void checkServerTrusted(X509Certificate[] certs, String authType) {
                    }
                }
        };
        SSLContext sslContext = SSLContext.getInstance("SSL");
        sslContext.init(null, trustAllCert, new SecureRandom());
        SSLSocketFactory sslFactory = sslContext.getSocketFactory();
        SSLSocket sslSocket = (SSLSocket) sslFactory.createSocket(host, 443);

        Writer streamWriter = new OutputStreamWriter(sslSocket.getOutputStream());
        String rn = System.getProperty("line.separator");
        streamWriter.write("GET " + resource + " HTTP/1.1" + rn + "Connection:close" + rn + "Host:" + host + rn + rn);
        streamWriter.flush();

        InputStreamReader streamReader = new InputStreamReader(sslSocket.getInputStream());
        BufferedReader bufferedReader = new BufferedReader(streamReader);
        String response = new String();
        for (String line; (line = bufferedReader.readLine()) != null; response += line + rn) ;

        streamWriter.close();
        bufferedReader.close();
        sslSocket.close();

        System.out.println("https://" + host + resource + " " + response.substring(0, response.indexOf(rn)));
        return response;
    }

    public static String useUrlConnection(String host, String resource) throws NoSuchAlgorithmException, KeyManagementException, IOException, URISyntaxException, WrapHttpsConnectException {

        if (host == null || host.trim().isEmpty()) {
            throw new WrapHttpsConnectException("Host cannot be empty. Example: www.website.com");
        }
        if (resource == null || resource.trim().isEmpty()) {
            return "/";
        }

        TrustManager[] trustAllCerts = new TrustManager[]{
                new X509TrustManager() {
                    public X509Certificate[] getAcceptedIssuers() {
                        return null;
                    }

                    public void checkClientTrusted(X509Certificate[] certs, String authType) {
                    }

                    public void checkServerTrusted(X509Certificate[] certs, String authType) {
                    }
                }
        };
        SSLContext sslConnect = SSLContext.getInstance("SSL");
        sslConnect.init(null, trustAllCerts, new SecureRandom());
        HttpsURLConnection.setDefaultSSLSocketFactory(sslConnect.getSocketFactory());
        HostnameVerifier allHostsValid = new HostnameVerifier() {
            public boolean verify(String hostname, SSLSession session) {
                return true;
            }
        };
        HttpsURLConnection.setDefaultHostnameVerifier(allHostsValid);
        URL url = new URI("https://" + host + resource).toURL();
        HttpsURLConnection urlConnection = (HttpsURLConnection) url.openConnection();
        urlConnection.setRequestMethod("GET");
        String status = urlConnection.getHeaderField(0);

        InputStream inputStream;
        if (status.contains("200")) {
            inputStream = urlConnection.getInputStream();
        } else {
            inputStream = urlConnection.getErrorStream();
        }
        InputStreamReader streamReader = new InputStreamReader(inputStream);
        BufferedReader bufferedReader = new BufferedReader(streamReader);
        String response = new String();
        for (String line; (line = bufferedReader.readLine()) != null; response += line + System.getProperty("line.separator"));
        bufferedReader.close();

        System.out.println(url + " " + status);
        if (!status.contains("200")) {
            System.out.println(response);
        }
        return response;
    }
}
