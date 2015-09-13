package com.bestsonic.integrate.core.model;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

@Entity
@Table(name="user")
public class User {
	private String id;
	private String username;
	private String password;
	private String age;
	private String sex;
	private String location;
	private String mail;
	private String tags;
	private String portrait;
	private Set<History> historyList;
	private Set<Recommend> recommendList;
	
	public User() {
		super();
	}
	
	public User(String username, String password, String age, String sex,
			String location, String mail, String tags, String portrait,
			Set<History> historyList, Set<Recommend> recommendList) {
		super();
		this.username = username;
		this.password = password;
		this.age = age;
		this.sex = sex;
		this.location = location;
		this.mail = mail;
		this.tags = tags;
		this.portrait = portrait;
		this.historyList = historyList;
		this.recommendList = recommendList;
	}


	public User(String id, String username, String password, String age,
			String sex, String location, String mail, String tags,
			String portrait, Set<History> historyList,
			Set<Recommend> recommendList) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.age = age;
		this.sex = sex;
		this.location = location;
		this.mail = mail;
		this.tags = tags;
		this.portrait = portrait;
		this.historyList = historyList;
		this.recommendList = recommendList;
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

	@Column(unique=true,nullable=false,length=20)
	public String getUsername() {
		return username;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	
	@Column(nullable=false,length=18)
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	@Column(length=3)
	public String getAge() {
		return age;
	}
	
	public void setAge(String age) {
		this.age = age;
	}
	
	@Column(length=2)
	public String getSex() {
		return sex;
	}
	
	public void setSex(String sex) {
		this.sex = sex;
	}
	
	@Column(length=50)
	public String getLocation() {
		return location;
	}
	
	public void setLocation(String location) {
		this.location = location;
	}
	
	@Column(length=20)
	public String getMail() {
		return mail;
	}
	
	public void setMail(String mail) {
		this.mail = mail;
	}
	
	@Column(length=100)
	public String getTags() {
		return tags;
	}
	
	public void setTags(String tags) {
		this.tags = tags;
	}
	
	@Column(length=100)
	public String getPortrait() {
		return portrait;
	}
	
	public void setPortrait(String portrait) {
		this.portrait = portrait;
	}
	
	@OneToMany(mappedBy="user",fetch=FetchType.LAZY)
	@LazyCollection(LazyCollectionOption.EXTRA)
	public Set<History> getHistoryList() {
		return historyList;
	}
	
	public void setHistoryList(Set<History> historyList) {
		this.historyList = historyList;
	}
	
	@OneToMany(mappedBy="user",fetch=FetchType.LAZY)
	@LazyCollection(LazyCollectionOption.EXTRA)
	public Set<Recommend> getRecommendList() {
		return recommendList;
	}
	public void setRecommendList(Set<Recommend> recommendList) {
		this.recommendList = recommendList;
	}
	
	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", password=" + password
				+ ", age=" + age + ", sex=" + sex + ", location=" + location
				+ ", mail=" + mail + ", tags=" + tags + ", portrait="
				+ portrait + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((age == null) ? 0 : age.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result
				+ ((location == null) ? 0 : location.hashCode());
		result = prime * result + ((mail == null) ? 0 : mail.hashCode());
		result = prime * result
				+ ((password == null) ? 0 : password.hashCode());
		result = prime * result
				+ ((portrait == null) ? 0 : portrait.hashCode());
		result = prime * result + ((sex == null) ? 0 : sex.hashCode());
		result = prime * result + ((tags == null) ? 0 : tags.hashCode());
		result = prime * result
				+ ((username == null) ? 0 : username.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof User))
			return false;
		User other = (User) obj;
		if (age == null) {
			if (other.age != null)
				return false;
		} else if (!age.equals(other.getAge()))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.getId()))
			return false;
		if (location == null) {
			if (other.location != null)
				return false;
		} else if (!location.equals(other.getLocation()))
			return false;
		if (mail == null) {
			if (other.mail != null)
				return false;
		} else if (!mail.equals(other.getMail()))
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.getPassword()))
			return false;
		if (portrait == null) {
			if (other.portrait != null)
				return false;
		} else if (!portrait.equals(other.getPortrait()))
			return false;
		if (sex == null) {
			if (other.sex != null)
				return false;
		} else if (!sex.equals(other.getSex()))
			return false;
		if (tags == null) {
			if (other.tags != null)
				return false;
		} else if (!tags.equals(other.getTags()))
			return false;
		if (username == null) {
			if (other.username != null)
				return false;
		} else if (!username.equals(other.getUsername()))
			return false;
		return true;
	}
}
