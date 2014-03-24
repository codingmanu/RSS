package network;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class HTTPFetcher {

    private List<String> srcLines;
    public String[][] cleanLinks;

    public HTTPFetcher(String inputURL) {
        try {
            URL my_url = new URL(inputURL);
            BufferedReader br = new BufferedReader(new InputStreamReader(my_url.openStream()));

            srcLines = new ArrayList<>();
            String line;

            while ((line = br.readLine()) != null) {
                srcLines.add(line);
            }
            br.close();
            srcLines.toArray(new String[srcLines.size()]);


        } catch (IOException IOEx) {
        }

    }


}
