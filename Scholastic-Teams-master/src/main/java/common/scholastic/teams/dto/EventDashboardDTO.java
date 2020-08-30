package common.scholastic.teams.dto;

import java.util.Date;

public class EventDashboardDTO {
	private static final long serialVersionUID = 1L;
	private int img_id;
	private String image_uid;
	private String imagename;
	private byte[] image;
	private String postedBy;
	private Date postedDate;

	public Date getPostedDate() {
		return postedDate;
	}

	public void setPostedDate(Date postedDate) {
		this.postedDate = postedDate;
	}

	public String getImage_uid() {
		return image_uid;
	}

	public void setImage_uid(String image_uid) {
		this.image_uid = image_uid;
	}

	public String getImagename() {
		return imagename;
	}

	public void setImagename(String imagename) {
		this.imagename = imagename;
	}

	public byte[] getImage() {
		return image;
	}

	public void setImage(byte[] image) {
		this.image = image;
	}

	public int getImg_id() {
		return img_id;
	}

	public void setImg_id(int img_id) {
		this.img_id = img_id;
	}

	public String getPostedBy() {
		return postedBy;
	}

	public void setPostedBy(String postedBy) {
		this.postedBy = postedBy;
	}

}
