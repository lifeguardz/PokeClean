package ch.lifeguardz.projects.pokeClean.entities;

public class Login {

	private GoogleLogin googleLogin;
	private PtcLogin ptcLogin;

	public Login(GoogleLogin googleLogin) {
		this.googleLogin = googleLogin;
	}

	public Login(PtcLogin ptcLogin) {
		super();
		this.ptcLogin = ptcLogin;
	}

	public GoogleLogin getGoogleLogin() {
		return googleLogin;
	}

	public void setGoogleLogin(GoogleLogin googleLogin) {
		this.googleLogin = googleLogin;
	}

	public PtcLogin getPtcLogin() {
		return ptcLogin;
	}

	public void setPtcLogin(PtcLogin ptcLogin) {
		this.ptcLogin = ptcLogin;
	}

	public boolean isGoogleLogin() {
		if (googleLogin == null)
			return false;
		return true;
	}

	public boolean isPtcLogin() {
		if (ptcLogin == null)
			return false;
		return true;
	}

}
