package mul.camp.a.dto;

/* #21# 오늘의 [다이어트] 식단에 사용하는 Dto */
public class SubDietMealDto {
	 
	private int subdfSeq;
	private int subdfTime;
	private String subdfImage;
	private String subdfName;
	private int subdfKcal;
	private int subdfAmount;
	private String subdfType;
	
	public SubDietMealDto() {
		
	}

	public SubDietMealDto(int subdfSeq, int subdfTime, String subdfImage, String subdfName, int subdfKcal,
			int subdfAmount, String subdfType) {
		super();
		this.subdfSeq = subdfSeq;
		this.subdfTime = subdfTime;
		this.subdfImage = subdfImage;
		this.subdfName = subdfName;
		this.subdfKcal = subdfKcal;
		this.subdfAmount = subdfAmount;
		this.subdfType = subdfType;
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

	public String getSubdfType() {
		return subdfType;
	}

	public void setSubdfType(String subdfType) {
		this.subdfType = subdfType;
	}

	@Override
	public String toString() {
		return "SubTodayMealDto [subdfSeq=" + subdfSeq + ", subdfTime=" + subdfTime + ", subdfImage=" + subdfImage
				+ ", subdfName=" + subdfName + ", subdfKcal=" + subdfKcal + ", subdfAmount=" + subdfAmount
				+ ", subdfType=" + subdfType + "]";
	}

	
	
	
	/* subdfType가 빠진 version
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
	} */
	

}
