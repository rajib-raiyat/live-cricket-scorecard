package search;

import com.meilisearch.sdk.Client;
import com.meilisearch.sdk.Config;
import com.meilisearch.sdk.Index;

import java.util.Map;
import java.util.Objects;

import static com.database.DatabaseConfig.*;

public class Search {
    public static StringBuilder main(String search_query) throws Exception {
        Client client = new Client(new Config(SEARCH_URL, SEARCH_API_KEY));
        Index index = client.index(DB_TABLE_NAME);
        Object[] search = index.search(search_query).getHits().toArray();

        StringBuilder result = new StringBuilder();

        if (search.length == 0) {
            return result.append("No result found");
        }

        Map<String, String> temp;

        for (int i = 0; i < search.length; i++) {
            temp = (Map<String, String>) search[i];

            result.append(i + 1).append(".");

            for (String s : temp.keySet()) {
                String value = temp.get(s);

                if (Objects.equals(value, "")) {
                    value = null;
                }
                result.append("\n").append(s.replace("_", " ")).append(" ").append(value)
                        .append("\n---------------------------------------------------");

            }
            result.append("\n");
        }

        return result;
    }
}
