package cn.jcb.dev.spittr.domain;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.springframework.web.multipart.MultipartFile;

public class Spitter {
	private long id;
	private MultipartFile profilePicture;
	@NotNull
	@Size(min=2, max=30, message="{firstName.size}")
	private String firstName;
	@NotNull
	@Size(min=2, max=30, message="{lastName.size}")
	private String lastName;
	@NotNull
	@Size(min=5, max=16, message="{username.size}")
	private String username;
	@NotNull
	@Size(min=5, max=25, message="{password.size}")
	private String password;
	
	public Spitter(){
		//spring在处理form request时，form binding会调用缺省无参构造函数
	}
	
	public Spitter(String firstName, String lastName, String username, String password) {
		this(0,firstName,lastName,username,password);
	}

	public Spitter(long id, String firstName, String lastName, String username, String password) {
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.username = username;
		this.password = password;
	}

	public String getUsername() {	
		return this.username;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public String getPassword() {
		return password;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public MultipartFile getProfilePicture() {
		return profilePicture;
	}

	public void setProfilePicture(MultipartFile picture) {
		this.profilePicture = picture;
	}

	@Override
	public boolean equals(Object that) {
		return EqualsBuilder.reflectionEquals(this, that, "id");
	}

	@Override
	public int hashCode() {
		return HashCodeBuilder.reflectionHashCode(this, "id");
	}
}
