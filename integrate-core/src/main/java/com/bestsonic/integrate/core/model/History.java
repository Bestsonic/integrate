package com.bestsonic.integrate.core.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name="history")
public class History {
	private String id;
	private User user;
	private Movie movie;
	private String date;
	private String rate;
	private String comment;
	public History() {
		super();
	}
	
	public History(User user, Movie movie, String date, String rate,
			String comment) {
		super();
		this.user = user;
		this.movie = movie;
		this.date = date;
		this.rate = rate;
		this.comment = comment;
	}

	public History(String id, User user, Movie movie, String date, String rate,
			String comment) {
		super();
		this.id = id;
		this.user = user;
		this.movie = movie;
		this.date = date;
		this.rate = rate;
		this.comment = comment;
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

	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="user_id")
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="movie_id")
	public Movie getMovie() {
		return movie;
	}

	public void setMovie(Movie movie) {
		this.movie = movie;
	}

	@Column(nullable=false)
	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}
	
	@Column(nullable=false,length=5)
	public String getRate() {
		return rate;
	}

	public void setRate(String rate) {
		this.rate = rate;
	}

	@Lob
	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}
	@Override
	public String toString() {
		return "History [id=" + id + ", user=" + user + ", movie=" + movie
				+ ", date=" + date + ", rate=" + rate + ", comment=" + comment
				+ "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((comment == null) ? 0 : comment.hashCode());
		result = prime * result + ((date == null) ? 0 : date.hashCode());
		result = prime * result + ((movie == null) ? 0 : movie.hashCode());
		result = prime * result + ((rate == null) ? 0 : rate.hashCode());
		result = prime * result + ((user == null) ? 0 : user.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof History))
			return false;
		History other = (History) obj;
		if (comment == null) {
			if (other.comment != null)
				return false;
		} else if (!comment.equals(other.getComment()))
			return false;
		if (date == null) {
			if (other.date != null)
				return false;
		} else if (!date.equals(other.getDate()))
			return false;
		if (movie == null) {
			if (other.movie != null)
				return false;
		} else if (!movie.equals(other.getMovie()))
			return false;
		if (rate == null) {
			if (other.rate != null)
				return false;
		} else if (!rate.equals(other.getRate()))
			return false;
		if (user == null) {
			if (other.user != null)
				return false;
		} else if (!user.equals(other.getUser()))
			return false;
		return true;
	}
}
