package com.bjitgroup.android.farhan.bengaligeowordnet.model;


import java.sql.Date;

/**
 * Created by bjit on 10/7/15.
 */
public class GeonamesModel {

    /**
     * integer id of record in geonames database
     * @var int(11) NOT NULL
     */
    private int geonameid;

    /**
     * Name of geographical point (utf8)
     * @var varchar(400) DEFAULT NULL
     */
    private String name;

    /**
     * name of geographical point in plain ascii characters,
     * varchar(200)
     * @var varchar(400) DEFAULT NULL
     */
    private String asciiname;


    /**
     * alternatenames, comma separated, ascii names automatically
     * transliterated, convenience attribute from alternatename
     * table, varchar(10000)
     * @var varchar(10000)/text DEFAULT NOT NULL
     */
    private String alternatenames;

    /**
     * latitude in decimal degrees (wgs84)
     * @var
     */
    private double latitude;

    /**
     * longitude in decimal degrees (wgs84)
     * @var type
     */
    private double longitude;

    /**
     * Feature class of a geoname entity
     * @var char(1)
     */
    private String fclass;


    /**
     * Feature code of a geoname entity
     * @var varchar(10)
     */
    private String fcode;


    /**
     * ISO-3166 2-letter country code, 2 characters
     * @var char(2)
     */
    private String country;

    /**
     * alternate country codes, comma separated,
     * ISO-3166 2-letter country code, 200 characters
     * @var varchar(200)
     */
    private String cc2;

    /**
     * fipscode (subject to change to iso code), see exceptions below, see file admin1Codes.txt for display names of this code; varchar(20)
     * @var type
     */
    private String admin1;

    /**
     * code for the second administrative division, a county in the US, see file admin2Codes.txt; varchar(80)
     * @var type
     */
    private String admin2;


    /**
     * code for third level administrative division
     * @var varchar(20)
     */
    private String admin3;

    /**
     * code for fourth level administrative division
     * @var varchar(20)
     */
    private String admin4;

    /**
     * The population of an area
     * @var bigint (8 byte int)
     */
    private long population;

    /**
     * Elevation of an area, which is also defined as height from
     * standard sea level.
     * @var integer in meters
     */
    private int elevation;

    /**
     * digital elevation model, srtm3 or gtopo30, average elevation
     * of 3''x3'' (ca 90mx90m) or 30''x30'' (ca 900mx900m) area in
     * meters, integer. srtm processed by cgiar/ciat.
     * @var integer area in meters
     */
    private int dem;


    /**
     * the timezone id (see file timeZone.txt)
     * @link timeZone.txt
     * @var varchar(40)
     */
    private String timezone;

    /**
     * date of last modification in yyyy-MM-dd format
     * @var  Date type
     */
    private Date moddate;

    public int getGeonameid() {
        return geonameid;
    }

    public void setGeonameid(int geonameid) {
        this.geonameid = geonameid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAsciiname() {
        return asciiname;
    }

    public void setAsciiname(String asciiname) {
        this.asciiname = asciiname;
    }

    public String getAlternatenames() {
        return alternatenames;
    }

    public void setAlternatenames(String alternatenames) {
        this.alternatenames = alternatenames;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public String getFclass() {
        return fclass;
    }

    public void setFclass(String fclass) {
        this.fclass = fclass;
    }

    public String getFcode() {
        return fcode;
    }

    public void setFcode(String fcode) {
        this.fcode = fcode;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCc2() {
        return cc2;
    }

    public void setCc2(String cc2) {
        this.cc2 = cc2;
    }

    public String getAdmin1() {
        return admin1;
    }

    public void setAdmin1(String admin1) {
        this.admin1 = admin1;
    }

    public String getAdmin2() {
        return admin2;
    }

    public void setAdmin2(String admin2) {
        this.admin2 = admin2;
    }

    public String getAdmin3() {
        return admin3;
    }

    public void setAdmin3(String admin3) {
        this.admin3 = admin3;
    }

    public String getAdmin4() {
        return admin4;
    }

    public void setAdmin4(String admin4) {
        this.admin4 = admin4;
    }

    public long getPopulation() {
        return population;
    }

    public void setPopulation(long population) {
        this.population = population;
    }

    public int getElevation() {
        return elevation;
    }

    public void setElevation(int elevation) {
        this.elevation = elevation;
    }

    public int getDem() {
        return dem;
    }

    public void setDem(int dem) {
        this.dem = dem;
    }

    public String getTimezone() {
        return timezone;
    }

    public void setTimezone(String timezone) {
        this.timezone = timezone;
    }

    public Date getModdate() {
        return moddate;
    }

    public void setModdate(Date moddate) {
        this.moddate = moddate;
    }
}
