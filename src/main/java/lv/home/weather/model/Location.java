package lv.home.weather.model;

public class Location {
    private String status; //"success",
    private String country; //": "Canada",
    private String countryCode; //": "CA",
    private String region; //": "QC",
    private String regionName;//": "Quebec",
    private String city; //": "Montreal",
    private String zip; //": "H1S",
    private double lat;//": 45.5808,
    private double lon; //": -73.5825,
    private String timezone; //": "America/Toronto",
    private String isp; //": "Le Groupe Videotron Ltee",
    private String org; //": "Videotron Ltee",
    private String as; //": "AS5769 Videotron Telecom Ltee",
    private String query; //": "24.48.0.1"

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getRegionName() {
        return regionName;
    }

    public void setRegionName(String regionName) {
        this.regionName = regionName;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLon() {
        return lon;
    }

    public void setLon(double lon) {
        this.lon = lon;
    }

    public String getTimezone() {
        return timezone;
    }

    public void setTimezone(String timezone) {
        this.timezone = timezone;
    }

    public String getIsp() {
        return isp;
    }

    public void setIsp(String isp) {
        this.isp = isp;
    }

    public String getOrg() {
        return org;
    }

    public void setOrg(String org) {
        this.org = org;
    }

    public String getAs() {
        return as;
    }

    public void setAs(String as) {
        this.as = as;
    }

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    @Override
    public String toString() {
        return "Location{" +
                "status='" + status + '\'' +
                ", country='" + country + '\'' +
                ", countryCode='" + countryCode + '\'' +
                ", region='" + region + '\'' +
                ", regionName='" + regionName + '\'' +
                ", city='" + city + '\'' +
                ", zip='" + zip + '\'' +
                ", lat=" + lat +
                ", lon=" + lon +
                ", timezone='" + timezone + '\'' +
                ", isp='" + isp + '\'' +
                ", org='" + org + '\'' +
                ", as='" + as + '\'' +
                ", query='" + query + '\'' +
                '}';
    }
}
