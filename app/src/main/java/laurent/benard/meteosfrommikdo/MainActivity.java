package laurent.benard.meteosfrommikdo;

import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.util.Calendar;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {

    TextView titre_item;
    TextView max_temp_item;
    TextView min_temp_item;
    TextView location_item;
    ImageView img_item;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        titre_item = findViewById(R.id.titre_tv);
        max_temp_item = findViewById(R.id.temp_max_tv);
        min_temp_item = findViewById(R.id.temp_min_tv);
        location_item = findViewById(R.id.ville_tv);
        img_item = findViewById(R.id.icon_iv);

        //Permet d'executer AsynTask
        new FiveDaysRequest().execute();
        //new TestRequest().execute();


    }

    // Création d'une classe AsyncTask qui prend en paramêtre un tableau de la classe ClimatElement
    private class FiveDaysRequest extends AsyncTask<Void, Void, ClimatElement[]> {

        // Android me propose d'implémenter automatiquement le doInBackground
        @Override
        protected ClimatElement[] doInBackground(Void... voids) {

            /*J'ajouter la String correspondante à l'URL de la clé API et des informations que je souhaite obtenir
            je n'oublie pas de remplacer l'id key par le mien et ne pas oublier d'ajouter api devant openweathermap*/
            //String urlString = "https://api.openweathermap.org/data/2.5/forecast?q=London,us&appid=a1e26096d5b0804acd149724628c397c";

            final String QUERY_PARAM = "q";
            final String APPID_PARAM = "appid";
            final String UNITS_PARAM = "units";

            /*Appel de l'API de manière plus modulable*/
            Uri.Builder uriBuilder = new Uri.Builder();
            uriBuilder.scheme("http")
                    .authority("api.openweathermap.org")
                    .appendPath("data")
                    .appendPath("2.5")
                    .appendPath("forecast")
                    .appendQueryParameter(QUERY_PARAM, "London")
                    .appendQueryParameter(UNITS_PARAM, "metric")
                    .appendQueryParameter(APPID_PARAM, "a1e26096d5b0804acd149724628c397c")
                    .build();
            /* Je crée un URL avec comme valeur la variable uriBuilder, automatiquement
            android me propose un try/catch*/

            // Après avoir déclaré cette variable ici, la valeur par défaut est automatiquement demandée dans le request
            URL url = null;

            try {
                url = new URL(uriBuilder.toString());
                int hi = 1;
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }


            /* Je démarre la request */
            OkHttpClient client = new OkHttpClient();

            Request request = new Request.Builder()
                    .url(url)
                    .build();

            try {
                Response response = client.newCall(request).execute();
                String bodyReponse = response.body().string();
                Location location = parseLocation(bodyReponse);
                //Création d'une méthode parseMain
                parseMain(bodyReponse);


                //climatElement = parseJSON(bodyReponse);
                Log.i("Reponse",response.toString());
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }

            return new ClimatElement[0];
        }
    }

    private void parseMain(String bodyReponse) throws JSONException {

        JSONObject mainJSON = new JSONObject(bodyReponse);
        JSONArray list = mainJSON.getJSONArray("list");

        Temps[] tempsArray = new Temps[list.length()];
        ClimatInfo[] climatInfoArray = new ClimatInfo[list.length()];

        int i = 0;
        for(i = 0 ; i<list.length() ; i++){
            JSONObject elementi = list.getJSONObject(i);
            tempsArray[i] = parseTemps(elementi);
            climatInfoArray[i] = parseClimatInfo(elementi);

        }

        //Element 0
        JSONObject element0 = list.getJSONObject(0);

        Temps t0 = parseTemps(element0);
        ClimatInfo i0 = parseClimatInfo(element0);




    }

    private Temps parseTemps(JSONObject element0) throws JSONException {

        //Temps
        int dt = element0.getInt("dt");
        String dt_text = element0.getString("dt_text");
        Temps temp0 = new Temps(dt, dt_text);

        return temp0;
    }

    private ClimatInfo parseClimatInfo(JSONObject element0) throws JSONException {

        //Main
        JSONObject main = element0.getJSONObject("main");
        float temperature = (float) main.getDouble("temp");
        float pression = (float) main.getDouble("pressure");
        float humidite = (float) main.getDouble("humidity");

        //Weather
        JSONArray weather = element0.getJSONArray("weather");
        JSONObject weather0 = weather.getJSONObject(0);
        int weatherId = weather0.getInt("id");
        String weatherMain = weather0.getString("main");
        String weatherDescription = weather0.getString("description");
        String weatherIcon = weather0.getString("icon");

        //Vent
        JSONObject vent = element0.getJSONObject("wind");
        float vent_vitesse = (float) vent.getDouble("speed");

        ClimatInfo climatInfo = new ClimatInfo(temperature,
                pression,
                humidite,
                vent_vitesse,
                weatherMain,
                weatherDescription,
                weatherIcon,
                weatherId);

        return climatInfo;
    }

    //Déclaration d'un parse, créer l'exception (new JSONObject, clique droit)
    //L'exception est déclarée dans la méthode doInBackground ( try / catch)
    private Location parseLocation(String bodyReponse) throws JSONException {
        JSONObject mainJSON = new JSONObject(bodyReponse);
        JSONObject city = mainJSON.getJSONObject("city");
        int id = city.getInt("id");
        String ville = city.getString("name");
        String country = city.getString("country");

        JSONObject coord = city.getJSONObject("coord");
        float lat = (float) coord.getDouble("lat");
        float lon = (float) coord.getDouble("lon");

        Location location = new Location(id, lat, lon, ville, country);

        return location;
    }

    private class TestRequest extends AsyncTask<Void, Void, ClimatElement>{


        @Override
        protected ClimatElement doInBackground(Void... voids) {
            Log.i("TestRequest", "Je ne suis pas synchro !");

            String urlTest = "http://api.openweathermap.org/data/2.5/weather?q=London&APPID=a1e26096d5b0804acd149724628c397c";

            // Request obtenue par la librairie ci-dessous et l'API openweathermap
            OkHttpClient client = new OkHttpClient();

            Request request = new Request.Builder()
                    .url(urlTest)
                    .build();

            ClimatElement climatElement = null;

            //On converti l'information dans un JSON dans la classe climatElement
            try {
                Response response = client.newCall(request).execute();
                String bodyReponse = response.body().string();
                climatElement = parseJSON(bodyReponse);
                Log.i("Reponse",response.toString());
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return climatElement;
        }

        @Override
        protected void onPostExecute(ClimatElement climatElement) {
            super.onPostExecute(climatElement);

            String titreItem = climatElement.getNameOfDay() + " " + climatElement.getDay() + " "+ climatElement.getNameOfMonth() + " " + climatElement.getYear();

            //On update les variables pour l'utilisateur
            titre_item.setText(titreItem);
            max_temp_item.setText("Temp max : " + climatElement.getMaxTemp());
            min_temp_item.setText("Temp min :" + climatElement.getMinTemp());
            location_item.setText(climatElement.getLocation());

        }
    }

    private ClimatElement parseJSON(String bodyReponse) throws JSONException {
        JSONObject mainJSON = new JSONObject(bodyReponse);
        String ville = mainJSON.get("name").toString();
        JSONObject sys = mainJSON.getJSONObject("sys");
        String pays = sys.get("country").toString();
        String location = ville + "," + pays;


        JSONObject main = new JSONObject("main");
        String minTemp = main.get("temp_min").toString();
        String maxTemp = main.get("temp_max").toString();

        String dt = mainJSON.get("dt").toString();
        int timeStamp = Integer.valueOf(dt);
        Calendar mydate = Calendar.getInstance();
        mydate.setTimeInMillis((long)timeStamp*1000);


        String nameOfMonth =  Utils.getMonth(mydate.get(Calendar.MONTH));
        String nameOfDay = Utils.getDay(mydate.get(Calendar.DAY_OF_WEEK));

        ClimatElement climatElement = new ClimatElement(ville,
                pays,
                location,
                minTemp,
                maxTemp,
                timeStamp,
                nameOfMonth,
                nameOfDay,
                mydate.get(Calendar.DAY_OF_MONTH),
                mydate.get(Calendar.MONTH),
                mydate.get(Calendar.YEAR));
        return climatElement;

    }



}
