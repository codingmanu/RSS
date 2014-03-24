package network;

import item.Item;
import java.util.ArrayList;

public class ArticleExtractor {

    private static ArrayList<Item> items = new ArrayList();

    public ArticleExtractor() {

    }

    public static ArrayList<Item> extractNewItems(ArrayList<String> data) {
        int beginPosition, endPosition;

        for (String s : data) {
            do {
                beginPosition = s.indexOf("<item>");
                endPosition = s.indexOf("</item>");

                String stringItem = s.substring(beginPosition, endPosition);

                String title = stringItem.substring(stringItem.indexOf("<title>") + 7, stringItem.indexOf("</title>"));
                String URL = stringItem.substring(stringItem.indexOf("<link>") + 6, stringItem.indexOf("</link>"));

                Item item = new Item(title, URL);
                items.add(item);

                s = s.substring(endPosition + 1);

            } while (beginPosition != -1);
        }
        return items;
    }

}
