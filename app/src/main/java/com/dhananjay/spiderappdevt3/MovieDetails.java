package com.dhananjay.spiderappdevt3;

import com.google.gson.annotations.SerializedName;
import com.j256.ormlite.field.DataType;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "MoviesDb")
public class MovieDetails {

    @DatabaseField(generatedId = true)
    long id;

    @DatabaseField
    @SerializedName("Title")
    String title;

    @DatabaseField
    @SerializedName("Year")
    String year;

    @DatabaseField
    @SerializedName("Rated")
    String rated;

    @DatabaseField
    @SerializedName("Released")
    String released;

    @DatabaseField
    @SerializedName("Runtime")
    String runtime;

    @DatabaseField
    @SerializedName("Genre")
    String genre;

    @DatabaseField
    @SerializedName("Director")
    String director;

    @DatabaseField
    @SerializedName("Writer")
    String writer;

    @DatabaseField
    @SerializedName("Actors")
    String actors;

    @DatabaseField
    @SerializedName("Plot")
    String plot;

    @DatabaseField
    @SerializedName("Language")
    String language;

    @DatabaseField
    @SerializedName("Country")
    String country;

    @DatabaseField
    @SerializedName("Awards")
    String awards;

    @DatabaseField
    @SerializedName("Poster")
    String poster;

    @DatabaseField
    @SerializedName("Metascore")
    String metascore;

    @DatabaseField
    @SerializedName("imdbRating")
    String imdbrating;

    @DatabaseField
    @SerializedName("imdbVotes")
    String imdbvotes;

    @DatabaseField
    @SerializedName("imdbID")
    String imdbid;

    @DatabaseField
    @SerializedName("Type")
    String type;

    @DatabaseField
    @SerializedName("Response")
    String response;

    @DatabaseField(dataType = DataType.BYTE_ARRAY)
    byte[] imagebytes;
}
