package mul.camp.a.dto;

/* #21# 오늘의 식단에 사용하는 Dto */
/*
create table META_SUBSCRIBE_DIETMEALS (
		subdf_seq number not null,
		subdf_time number not null,
		subdf_image varchar2(100),
		subdf_name varchar2(40) not null, 
		subdf_kcal number,
		subdf_amount number,
		CONSTRAINT PK_SUB_DIETMEAL_SEQ PRIMARY KEY(subdf_seq),
		CONSTRAINT FK_SUB_DIETMEAL_NAME FOREIGN KEY(subdf_name) REFERENCES META_FOOD
	);
	CREATE SEQUENCE subdf_seq 
	START WITH 1001 INCREMENT BY 1;
*/

public class SubTodayMealDto {
	
	private int subdfSeq;
	private int subdfTime;
	private String subdfImage;
	private String subdfName;
	private int subdfKcal;
	private int subdfAmount;
	
	public SubTodayMealDto() {
		
	}

	public SubTodayMealDto(int subdfSeq, int subdfTime, String subdfImage, String subdfName, int subdfKcal,
			int subdfAmount) {
		super();
		this.subdfSeq = subdfSeq;
		this.subdfTime = subdfTime;
		this.subdfImage = subdfImage;
		this.subdfName = subdfName;
		this.subdfKcal = subdfKcal;
		this.subdfAmount = subdfAmount;
	}

	public int getSubdfSeq() {
		return subdfSeq;
	}

	public void setSubdfSeq(int subdfSeq) {
		this.subdfSeq = subdfSeq;
	}

	public int getSubdfTime() {
		return subdfTime;
	}

	public void setSubdfTime(int subdfTime) {
		this.subdfTime = subdfTime;
	}

	public String getSubdfImage() {
		return subdfImage;
	}

	public void setSubdfImage(String subdfImage) {
		this.subdfImage = subdfImage;
	}

	public String getSubdfName() {
		return subdfName;
	}

	public void setSubdfName(String subdfName) {
		this.subdfName = subdfName;
	}

	public int getSubdfKcal() {
		return subdfKcal;
	}

	public void setSubdfKcal(int subdfKcal) {
		this.subdfKcal = subdfKcal;
	}

	public int getSubdfAmount() {
		return subdfAmount;
	}

	public void setSubdfAmount(int subdfAmount) {
		this.subdfAmount = subdfAmount;
	}

	@Override
	public String toString() {
		return "SubTodayMealDto [subdfSeq=" + subdfSeq + ", subdfTime=" + subdfTime + ", subdfImage=" + subdfImage
				+ ", subdfName=" + subdfName + ", subdfKcal=" + subdfKcal + ", subdfAmount=" + subdfAmount + "]";
	}
	

}
