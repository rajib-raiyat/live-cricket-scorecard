package search;

import com.meilisearch.sdk.Client;
import com.meilisearch.sdk.Config;
import com.meilisearch.sdk.Index;
import com.meilisearch.sdk.model.SearchResult;

import static com.database.DatabaseConfig.*;

public class Search {
    public static void main(String search_query) throws Exception {
        Client client = new Client(new Config(SEARCH_URL, SEARCH_API_KEY));

        Index index = client.index(DB_TABLE_NAME);

        SearchResult results = index.search(search_query);
        System.out.println(results);
    }
}
