package search;

import com.meilisearch.sdk.Client;
import com.meilisearch.sdk.Config;
import com.meilisearch.sdk.Index;

import java.util.ArrayList;
import java.util.HashMap;

import static com.database.DatabaseConfig.*;

public class Search {
    public static ArrayList<HashMap<String, Object>> main(String search_query) throws Exception {
        Client client = new Client(new Config(SEARCH_URL, SEARCH_API_KEY));
        Index index = client.index(DB_TABLE_NAME);
        return index.search(search_query).getHits();
    }
}
