package laurent.benard.meteosfrommikdo;

public class Location {

    /* premiere chose : déclarer les variables pour chaque paramêtre désiré
    * deuxieme chose : ecrire le constructeur
    * troisieme chose : ecrire les get / set
    * tout se fait automatiquement avec clic droit generate */

    int id;
    float lat;
    float lon;
    String ville;
    String country;

    public Location(int id, float lat, float lon, String ville, String country) {
        this.id = id;
        this.lat = lat;
        this.lon = lon;
        this.ville = ville;
        this.country = country;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public float getLat() {
        return lat;
    }

    public void setLat(float lat) {
        this.lat = lat;
    }

    public float getLon() {
        return lon;
    }

    public void setLon(float lon) {
        this.lon = lon;
    }

    public String getVille() {
        return ville;
    }

    public void setVille(String city) {
        this.ville = ville;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}
