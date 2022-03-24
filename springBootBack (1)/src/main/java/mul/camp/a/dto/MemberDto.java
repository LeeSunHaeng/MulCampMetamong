package mul.camp.a.dto;
public class MemberDto {

	private String id;
	private String pwd;
	private String name;
	private String email;
	private String gender;
	private String phone;
	private String nickname;
	private Double height;
	private Double weight;
	private String birth;
	private int auth;
	private int subscribe;
	private String del;
	
	public MemberDto() {
	}

	public MemberDto(String id, String pwd, String name, String email, String gender, String phone, String nickname,
			Double height, Double weight, String birth, int auth, int subscribe, String del) {
		super();
		this.id = id;
		this.pwd = pwd;
		this.name = name;
		this.email = email;
		this.gender = gender;
		this.phone = phone;
		this.nickname = nickname;
		this.height = height;
		this.weight = weight;
		this.birth = birth;
		this.auth = auth;
		this.subscribe = subscribe;
		this.del = del;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public Double getHeight() {
		return height;
	}

	public void setHeight(Double height) {
		this.height = height;
	}

	public Double getWeight() {
		return weight;
	}

	public void setWeight(Double weight) {
		this.weight = weight;
	}

	public String getBirth() {
		return birth;
	}

	public void setBirth(String birth) {
		this.birth = birth;
	}

	public int getAuth() {
		return auth;
	}

	public void setAuth(int auth) {
		this.auth = auth;
	}

	public int getSubscribe() {
		return subscribe;
	}

	public void setSubscribe(int subscribe) {
		this.subscribe = subscribe;
	}

	public String getDel() {
		return del;
	}

	public void setDel(String del) {
		this.del = del;
	}

	@Override
	public String toString() {
		return "MemberDto [id=" + id + ", pwd=" + pwd + ", name=" + name + ", email=" + email + ", gender=" + gender
				+ ", phone=" + phone + ", nickname=" + nickname + ", height=" + height + ", weight=" + weight
				+ ", birth=" + birth + ", auth=" + auth + ", subscribe=" + subscribe + ", del=" + del + "]";
	}

}
