package ch.lifeguardz.projects.pokeClean.entities;

public class GoogleLogin {

	private String refreshToken;

	public GoogleLogin(String refreshToken) {
		super();
		this.refreshToken = refreshToken;
	}

	public String getRefreshToken() {
		return refreshToken;
	}

	public void setRefreshToken(String refreshToken) {
		this.refreshToken = refreshToken;
	}

}
