package mul.camp.a.dto;

public class SnsCommentDto {

	private int seq;
	private String id;
	private String nickname;
	private String profile;
	private String wdate;
	private String content;
	
	public SnsCommentDto(int seq, String id, String nickname, String profile, String wdate, String content) {
		super();
		this.seq = seq;
		this.id = id;
		this.nickname = nickname;
		this.profile = profile;
		this.wdate = wdate;
		this.content = content;
	}

	public int getSeq() {
		return seq;
	}

	public void setSeq(int seq) {
		this.seq = seq;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getProfile() {
		return profile;
	}

	public void setProfile(String profile) {
		this.profile = profile;
	}

	public String getWdate() {
		return wdate;
	}

	public void setWdate(String wdate) {
		this.wdate = wdate;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Override
	public String toString() {
		return "SnsCommentDto [seq=" + seq + ", id=" + id + ", nickname=" + nickname + ", profile=" + profile
				+ ", wdate=" + wdate + ", content=" + content + "]";
	}
	
	
	
	
}
