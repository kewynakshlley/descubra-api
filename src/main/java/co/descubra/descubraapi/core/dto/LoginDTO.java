package co.descubra.descubraapi.core.dto;

import java.util.ArrayList;
import java.util.List;

public class LoginDTO {
	
	private Long id;
	
	private String username;
	
	private String accessToken;
	
	private List<String> roles = new ArrayList<String>();

	public List<String> getRoles() {
		return roles;
	}

	public void setRoles(List<String> roles) {
		this.roles = roles;
	}
	
	public LoginDTO() { }

	public String getAccessToken() {
		return accessToken;
	}

	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	
}