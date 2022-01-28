package com.github.meafs.recover.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MapsResultModel {

    @SerializedName("summary")
    @Expose
    private Summary summary;
    @SerializedName("results")
    @Expose
    private List<Result> results = null;

    public Summary getSummary() {
        return summary;
    }

    public void setSummary(Summary summary) {
        this.summary = summary;
    }

    public List<Result> getResults() {
        return results;
    }

    public void setResults(List<Result> results) {
        this.results = results;
    }

    public class Address {

        @SerializedName("municipalitySubdivision")
        @Expose
        private String municipalitySubdivision;
        @SerializedName("municipality")
        @Expose
        private String municipality;
        @SerializedName("countrySecondarySubdivision")
        @Expose
        private String countrySecondarySubdivision;
        @SerializedName("countrySubdivision")
        @Expose
        private String countrySubdivision;
        @SerializedName("postalCode")
        @Expose
        private String postalCode;
        @SerializedName("countryCode")
        @Expose
        private String countryCode;
        @SerializedName("country")
        @Expose
        private String country;
        @SerializedName("countryCodeISO3")
        @Expose
        private String countryCodeISO3;
        @SerializedName("freeformAddress")
        @Expose
        private String freeformAddress;
        @SerializedName("localName")
        @Expose
        private String localName;

        public String getMunicipalitySubdivision() {
            return municipalitySubdivision;
        }

        public void setMunicipalitySubdivision(String municipalitySubdivision) {
            this.municipalitySubdivision = municipalitySubdivision;
        }

        public String getMunicipality() {
            return municipality;
        }

        public void setMunicipality(String municipality) {
            this.municipality = municipality;
        }

        public String getCountrySecondarySubdivision() {
            return countrySecondarySubdivision;
        }

        public void setCountrySecondarySubdivision(String countrySecondarySubdivision) {
            this.countrySecondarySubdivision = countrySecondarySubdivision;
        }

        public String getCountrySubdivision() {
            return countrySubdivision;
        }

        public void setCountrySubdivision(String countrySubdivision) {
            this.countrySubdivision = countrySubdivision;
        }

        public String getPostalCode() {
            return postalCode;
        }

        public void setPostalCode(String postalCode) {
            this.postalCode = postalCode;
        }

        public String getCountryCode() {
            return countryCode;
        }

        public void setCountryCode(String countryCode) {
            this.countryCode = countryCode;
        }

        public String getCountry() {
            return country;
        }

        public void setCountry(String country) {
            this.country = country;
        }

        public String getCountryCodeISO3() {
            return countryCodeISO3;
        }

        public void setCountryCodeISO3(String countryCodeISO3) {
            this.countryCodeISO3 = countryCodeISO3;
        }

        public String getFreeformAddress() {
            return freeformAddress;
        }

        public void setFreeformAddress(String freeformAddress) {
            this.freeformAddress = freeformAddress;
        }

        public String getLocalName() {
            return localName;
        }

        public void setLocalName(String localName) {
            this.localName = localName;
        }

    }

    public class BtmRightPoint {

        @SerializedName("lat")
        @Expose
        private Double lat;
        @SerializedName("lon")
        @Expose
        private Double lon;

        public Double getLat() {
            return lat;
        }

        public void setLat(Double lat) {
            this.lat = lat;
        }

        public Double getLon() {
            return lon;
        }

        public void setLon(Double lon) {
            this.lon = lon;
        }

    }

    public class CategorySet {

        @SerializedName("id")
        @Expose
        private Integer id;

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

    }

    public class Classification {

        @SerializedName("code")
        @Expose
        private String code;
        @SerializedName("names")
        @Expose
        private List<Name> names = null;

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public List<Name> getNames() {
            return names;
        }

        public void setNames(List<Name> names) {
            this.names = names;
        }

    }

    public class EntryPoint {

        @SerializedName("type")
        @Expose
        private String type;
        @SerializedName("position")
        @Expose
        private Position__1 position;

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public Position__1 getPosition() {
            return position;
        }

        public void setPosition(Position__1 position) {
            this.position = position;
        }

    }

    public class GeoBias {

        @SerializedName("lat")
        @Expose
        private Double lat;
        @SerializedName("lon")
        @Expose
        private Double lon;

        public Double getLat() {
            return lat;
        }

        public void setLat(Double lat) {
            this.lat = lat;
        }

        public Double getLon() {
            return lon;
        }

        public void setLon(Double lon) {
            this.lon = lon;
        }

    }


    public class Name {

        @SerializedName("nameLocale")
        @Expose
        private String nameLocale;
        @SerializedName("name")
        @Expose
        private String name;

        public String getNameLocale() {
            return nameLocale;
        }

        public void setNameLocale(String nameLocale) {
            this.nameLocale = nameLocale;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

    }

    public class Poi {

        @SerializedName("name")
        @Expose
        private String name;
        @SerializedName("categorySet")
        @Expose
        private List<CategorySet> categorySet = null;
        @SerializedName("categories")
        @Expose
        private List<String> categories = null;
        @SerializedName("classifications")
        @Expose
        private List<Classification> classifications = null;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public List<CategorySet> getCategorySet() {
            return categorySet;
        }

        public void setCategorySet(List<CategorySet> categorySet) {
            this.categorySet = categorySet;
        }

        public List<String> getCategories() {
            return categories;
        }

        public void setCategories(List<String> categories) {
            this.categories = categories;
        }

        public List<Classification> getClassifications() {
            return classifications;
        }

        public void setClassifications(List<Classification> classifications) {
            this.classifications = classifications;
        }

    }

    public class Position__1 {

        @SerializedName("lat")
        @Expose
        private Double lat;
        @SerializedName("lon")
        @Expose
        private Double lon;

        public Double getLat() {
            return lat;
        }

        public void setLat(Double lat) {
            this.lat = lat;
        }

        public Double getLon() {
            return lon;
        }

        public void setLon(Double lon) {
            this.lon = lon;
        }

    }

    public class Result {

        @SerializedName("type")
        @Expose
        private String type;
        @SerializedName("id")
        @Expose
        private String id;
        @SerializedName("score")
        @Expose
        private Double score;
        @SerializedName("dist")
        @Expose
        private Double dist;
        @SerializedName("info")
        @Expose
        private String info;
        @SerializedName("poi")
        @Expose
        private Poi poi;
        @SerializedName("address")
        @Expose
        private Address address;
        @SerializedName("position")
        @Expose
        private Position position;
        @SerializedName("viewport")
        @Expose
        private Viewport viewport;
        @SerializedName("entryPoints")
        @Expose
        private List<EntryPoint> entryPoints = null;

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public Double getScore() {
            return score;
        }

        public void setScore(Double score) {
            this.score = score;
        }

        public Double getDist() {
            return dist;
        }

        public void setDist(Double dist) {
            this.dist = dist;
        }

        public String getInfo() {
            return info;
        }

        public void setInfo(String info) {
            this.info = info;
        }

        public Poi getPoi() {
            return poi;
        }

        public void setPoi(Poi poi) {
            this.poi = poi;
        }

        public Address getAddress() {
            return address;
        }

        public void setAddress(Address address) {
            this.address = address;
        }

        public Position getPosition() {
            return position;
        }

        public void setPosition(Position position) {
            this.position = position;
        }

        public Viewport getViewport() {
            return viewport;
        }

        public void setViewport(Viewport viewport) {
            this.viewport = viewport;
        }

        public List<EntryPoint> getEntryPoints() {
            return entryPoints;
        }

        public void setEntryPoints(List<EntryPoint> entryPoints) {
            this.entryPoints = entryPoints;
        }

    }

    public class Summary {

        @SerializedName("query")
        @Expose
        private String query;
        @SerializedName("queryType")
        @Expose
        private String queryType;
        @SerializedName("queryTime")
        @Expose
        private Integer queryTime;
        @SerializedName("numResults")
        @Expose
        private Integer numResults;
        @SerializedName("offset")
        @Expose
        private Integer offset;
        @SerializedName("totalResults")
        @Expose
        private Integer totalResults;
        @SerializedName("fuzzyLevel")
        @Expose
        private Integer fuzzyLevel;
        @SerializedName("geoBias")
        @Expose
        private GeoBias geoBias;

        public String getQuery() {
            return query;
        }

        public void setQuery(String query) {
            this.query = query;
        }

        public String getQueryType() {
            return queryType;
        }

        public void setQueryType(String queryType) {
            this.queryType = queryType;
        }

        public Integer getQueryTime() {
            return queryTime;
        }

        public void setQueryTime(Integer queryTime) {
            this.queryTime = queryTime;
        }

        public Integer getNumResults() {
            return numResults;
        }

        public void setNumResults(Integer numResults) {
            this.numResults = numResults;
        }

        public Integer getOffset() {
            return offset;
        }

        public void setOffset(Integer offset) {
            this.offset = offset;
        }

        public Integer getTotalResults() {
            return totalResults;
        }

        public void setTotalResults(Integer totalResults) {
            this.totalResults = totalResults;
        }

        public Integer getFuzzyLevel() {
            return fuzzyLevel;
        }

        public void setFuzzyLevel(Integer fuzzyLevel) {
            this.fuzzyLevel = fuzzyLevel;
        }

        public GeoBias getGeoBias() {
            return geoBias;
        }

        public void setGeoBias(GeoBias geoBias) {
            this.geoBias = geoBias;
        }

    }

    public class TopLeftPoint {

        @SerializedName("lat")
        @Expose
        private Double lat;
        @SerializedName("lon")
        @Expose
        private Double lon;

        public Double getLat() {
            return lat;
        }

        public void setLat(Double lat) {
            this.lat = lat;
        }

        public Double getLon() {
            return lon;
        }

        public void setLon(Double lon) {
            this.lon = lon;
        }

    }

    public class Viewport {

        @SerializedName("topLeftPoint")
        @Expose
        private TopLeftPoint topLeftPoint;
        @SerializedName("btmRightPoint")
        @Expose
        private BtmRightPoint btmRightPoint;

        public TopLeftPoint getTopLeftPoint() {
            return topLeftPoint;
        }

        public void setTopLeftPoint(TopLeftPoint topLeftPoint) {
            this.topLeftPoint = topLeftPoint;
        }

        public BtmRightPoint getBtmRightPoint() {
            return btmRightPoint;
        }

        public void setBtmRightPoint(BtmRightPoint btmRightPoint) {
            this.btmRightPoint = btmRightPoint;
        }

    }
}