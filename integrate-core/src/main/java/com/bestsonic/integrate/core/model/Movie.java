package com.bestsonic.integrate.core.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name="movie")
public class Movie {
	private String id;
	private String url;
	private String date;
	private String country;
	private String directors;
	private String type;
	private String rate;
	private String name;
	private String actors;
	private String language;
	private String intro;
	private String length;
	private String picture;
	
	public Movie() {
		super();
	}

	public Movie(String url, String date, String country, String directors,
			String type, String rate, String name, String actors,
			String language, String intro, String length, String picture) {
		super();
		this.url = url;
		this.date = date;
		this.country = country;
		this.directors = directors;
		this.type = type;
		this.rate = rate;
		this.name = name;
		this.actors = actors;
		this.language = language;
		this.intro = intro;
		this.length = length;
		this.picture = picture;
	}

	public Movie(String id, String url, String date, String country,
			String directors, String type, String rate, String name,
			String actors, String language, String intro, String length,
			String picture) {
		super();
		this.id = id;
		this.url = url;
		this.date = date;
		this.country = country;
		this.directors = directors;
		this.type = type;
		this.rate = rate;
		this.name = name;
		this.actors = actors;
		this.language = language;
		this.intro = intro;
		this.length = length;
		this.picture = picture;
	}

	@Id
	@GeneratedValue(generator="uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid2")
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Column(unique=true,nullable=false,length=50)
	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	@Column(length=50)
	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	@Column(length=50)
	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	@Column(length=100)
	public String getDirectors() {
		return directors;
	}

	public void setDirectors(String directors) {
		this.directors = directors;
	}
	
	@Column(length=30)
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Column(length=5)
	public String getRate() {
		return rate;
	}

	public void setRate(String rate) {
		this.rate = rate;
	}

	@Column(length=150)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(length=400)
	public String getActors() {
		return actors;
	}

	public void setActors(String actors) {
		this.actors = actors;
	}

	@Column(length=100)
	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	@Lob
	public String getIntro() {
		return intro;
	}

	public void setIntro(String intro) {
		this.intro = intro;
	}

	@Column(length=100)
	public String getLength() {
		return length;
	}

	public void setLength(String length) {
		this.length = length;
	}

	@Column(length=100)
	public String getPicture() {
		return picture;
	}

	public void setPicture(String picture) {
		this.picture = picture;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((actors == null) ? 0 : actors.hashCode());
		result = prime * result + ((country == null) ? 0 : country.hashCode());
		result = prime * result + ((date == null) ? 0 : date.hashCode());
		result = prime * result
				+ ((directors == null) ? 0 : directors.hashCode());
		result = prime * result + ((intro == null) ? 0 : intro.hashCode());
		result = prime * result
				+ ((language == null) ? 0 : language.hashCode());
		result = prime * result + ((length == null) ? 0 : length.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((picture == null) ? 0 : picture.hashCode());
		result = prime * result + ((rate == null) ? 0 : rate.hashCode());
		result = prime * result + ((type == null) ? 0 : type.hashCode());
		result = prime * result + ((url == null) ? 0 : url.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Movie))
			return false;
		Movie other = (Movie) obj;
		
		if (actors == null) {
			if (other.actors != null)
				return false;
		} else if (!actors.equals(other.getActors()))
			return false;
		if (country == null) {
			if (other.country != null)
				return false;
		} else if (!country.equals(other.getCountry()))
			return false;
		if (date == null) {
			if (other.date != null)
				return false;
		} else if (!date.equals(other.getDate()))
			return false;
		if (directors == null) {
			if (other.directors != null)
				return false;
		} else if (!directors.equals(other.getDirectors()))
			return false;
		if (intro == null) {
			if (other.intro != null)
				return false;
		} else if (!intro.equals(other.getIntro()))
			return false;
		if (language == null) {
			if (other.language != null)
				return false;
		} else if (!language.equals(other.getLanguage()))
			return false;
		if (length == null) {
			if (other.length != null)
				return false;
		} else if (!length.equals(other.getLength()))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.getName()))
			return false;
		if (picture == null) {
			if (other.picture != null)
				return false;
		} else if (!picture.equals(other.getPicture()))
			return false;
		if (rate == null) {
			if (other.rate != null)
				return false;
		} else if (!rate.equals(other.getRate()))
			return false;
		if (type == null) {
			if (other.type != null)
				return false;
		} else if (!type.equals(other.getType()))
			return false;
		if (url == null) {
			if (other.url != null)
				return false;
		} else if (!url.equals(other.getUrl()))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Movie [id=" + id + ", url=" + url + ", date=" + date
				+ ", country=" + country + ", directors=" + directors
				+ ", type=" + type + ", rate=" + rate + ", name=" + name
				+ ", actors=" + actors + ", language=" + language + ", intro="
				+ intro + ", length=" + length + ", picture=" + picture + "]";
	}
	
	
}
