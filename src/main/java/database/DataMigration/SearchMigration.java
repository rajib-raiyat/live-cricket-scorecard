package database.DataMigration;

import com.meilisearch.sdk.Client;
import com.meilisearch.sdk.Config;
import org.json.simple.JSONObject;

import java.io.StringWriter;

import static database.DatabaseConfig.*;

public class SearchMigration {
    public static void bulk_index(String[] column, String data) throws Exception {
        JSONObject obj = new JSONObject();

        for (int i = 0; i < column.length; i++) {
            obj.put(column[i], data.split(",", -1)[i]);
        }

        StringWriter out = new StringWriter();
        obj.writeJSONString(out);

        Client client = new Client(new Config(SEARCH_URL, SEARCH_API_KEY));
        client.index(DB_TABLE_NAME).addDocuments("[" + out + "]");
    }
}
