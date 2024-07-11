package com.example.conexatest.conexatest.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class FilmDto {

    private FilmProperties properties;
    private String description;

    private int uid;
    private int __v;
    private String _id;

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public int get__v() {
        return __v;
    }

    public void set__v(int __v) {
        this.__v = __v;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public FilmProperties getProperties() {
        return properties;
    }

    public void setProperties(FilmProperties properties) {
        this.properties = properties;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class FilmProperties {
        private String title;
        private String url;
        private int episode_id;
        private String opening_crawl;
        private String director;
        private String producer;
        private String release_date;
        private List<String> species;
        private List<String> starships;
        private List<String> vehicles;
        private List<String> characters;
        private List<String> planets;
        private String created;
        private String edited;

        public String getCreated() {
            return created;
        }

        public void setCreated(String created) {
            this.created = created;
        }

        public String getEdited() {
            return edited;
        }

        public void setEdited(String edited) {
            this.edited = edited;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public int getEpisode_id() {
            return episode_id;
        }

        public void setEpisode_id(int episode_id) {
            this.episode_id = episode_id;
        }

        public String getOpening_crawl() {
            return opening_crawl;
        }

        public void setOpening_crawl(String opening_crawl) {
            this.opening_crawl = opening_crawl;
        }

        public String getDirector() {
            return director;
        }

        public void setDirector(String director) {
            this.director = director;
        }

        public String getProducer() {
            return producer;
        }

        public void setProducer(String producer) {
            this.producer = producer;
        }

        public String getRelease_date() {
            return release_date;
        }

        public void setRelease_date(String release_date) {
            this.release_date = release_date;
        }

        public List<String> getSpecies() {
            return species;
        }

        public void setSpecies(List<String> species) {
            this.species = species;
        }

        public List<String> getStarships() {
            return starships;
        }

        public void setStarships(List<String> starships) {
            this.starships = starships;
        }

        public List<String> getVehicles() {
            return vehicles;
        }

        public void setVehicles(List<String> vehicles) {
            this.vehicles = vehicles;
        }

        public List<String> getCharacters() {
            return characters;
        }

        public void setCharacters(List<String> characters) {
            this.characters = characters;
        }

        public List<String> getPlanets() {
            return planets;
        }

        public void setPlanets(List<String> planets) {
            this.planets = planets;
        }
    }
}
