package network;


import java.util.ArrayList;
import java.util.List;

public class ArticleExtractor {

    //private List<String> inputLinks;
    private List<String> links;
    private String[][] cleanLinks;

    
    public ArticleExtractor(List<String> inputLinks) {
        
        //Initializing the links ArrayList
        links = new ArrayList<String>();

        //Extracting a valid "href="http://" URL with substrings from "inputLinks" rows and saving them to "links" ArrayList.
        for (int i = 0; i < inputLinks.size(); i++) {
            if (inputLinks.get(i).indexOf("href=\"http") != -1) {
                String link = inputLinks.get(i).trim();
                link = link.substring(link.indexOf("http"));
                try {
                    link = link.substring(0, link.indexOf("\""));
                    if (link.indexOf("?") != -1) link = link.substring(0, link.indexOf("?"));
                    if (link.substring(0, 7).equals("http://")) {
                        if (!isFile(link)) {
                            links.add(link);
                        }
                    }

                } catch (Exception ex) {

                }
                links.toArray(new String[links.size()]);
            }
        }

        //Print for debugging process. Remove after tests.
        /*
        System.out.println("\n***********************  [ LinkExtractor DEBUG ]  *****************************");
        for (int i = 0; i < links.size(); i++) {
            System.out.printf("* PARENT WEB: %s\n* --CHILD WEB: %s\n* \n", parentWeb(links.get(i)), links.get(i));
        }
        System.out.println("***********************  [ /LinkExtractor DEBUG ]  *****************************\n\n");
        */
    }

    //Detecting if the link is pointing to a file (ico, css, image...).
    private boolean isFile(String link) {
        String[] fileExtension = new String[]{".css", ".ico", ".png", ".jpg", ".jpeg", ".gif"};
        for (int j = 0; j < fileExtension.length - 1; j++) {
            if (link.substring(link.lastIndexOf(".")).equals(fileExtension[j])) {
                return true;
            }
        }
        return false;
    }

    //Return the parent web or domain from the link as a String detecting the Generic Domains from "webDomain" array.
    private String parentWeb(String link) {
        String[] webDomain = new String[]{".com", ".es", ".net", ".org", ".co.uk", ".info"};
        String domain = "";
        while (true) {
            for (int j = 0; j < webDomain.length - 1; j++) {
                try {
                    if (link.substring(link.indexOf(webDomain[j]), link.lastIndexOf(webDomain[j]) + webDomain[j].length()).equals(webDomain[j])) {
                        domain = webDomain[j];
                        break;
                    }
                } catch (Exception ex) {
                }
            }
            break;
        }
        return link.substring(0, link.indexOf(domain) + domain.length());
    }

    //Returns a matrix of [i] links (as much as "links" array rows has) and 2 rows: [0] parent web from link and [1] child web from link.
    public String[][] returnCleanLinks() {
        cleanLinks = new String[links.size()][links.size()];
        for (int i = 0; i < links.size() - 1; i++) {
            cleanLinks[i][0] = parentWeb(links.get(i));
            cleanLinks[i][1] = links.get(i);
        }
        return cleanLinks;
    }

}
