package laurent.benard.meteosfrommikdo;

import android.os.AsyncTask;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        //Permet d'executer AsynTask
        new TestRequest().execute();


    }

    private class TestRequest extends AsyncTask<Void, Void, Void>{


        @Override
        protected Void doInBackground(Void... voids) {
            Log.i("TestRequest", "Je ne suis pas synchro !");

            String urlTest = "http://api.openweathermap.org/data/2.5/weather?q=London&APPID=a1e26096d5b0804acd149724628c397c";


            OkHttpClient client = new OkHttpClient();

            Request request = new Request.Builder()
                    .url(urlTest)
                    .build();

            //Exception automatique si pour x raison le serveur ne répond pas
            try {
                Response response = client.newCall(request).execute();
                String bodyReponse = response.body().string();
                parseJSON(bodyReponse);
                Log.i("Reponse",response.toString());
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return null;
        }
    }

    private void parseJSON(String bodyReponse) throws JSONException {
        JSONObject mainJSON = new JSONObject(bodyReponse);
        String ville = mainJSON.get("name").toString();
        JSONObject sys = mainJSON.getJSONObject("sys");
        String pays = sys.get("country").toString();
        String location = ville + "," + pays;
        int salut = 1;
    }


}
