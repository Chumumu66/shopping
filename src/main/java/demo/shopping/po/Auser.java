package demo.shopping.po;

import javax.validation.constraints.NotBlank;

public class Auser {
	@NotBlank
	private String aname;

	@NotBlank
	private String apwd;

	public String getAname() {
		return aname;
	}
	public void setAname(String aname) {
		this.aname = aname;
	}
	public String getApwd() {
		return apwd;
	}
	public void setApwd(String apwd) {
		this.apwd = apwd;
	}
}
