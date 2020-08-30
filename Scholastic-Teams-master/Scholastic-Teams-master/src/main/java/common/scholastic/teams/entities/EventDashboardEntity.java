package common.scholastic.teams.entities;

import java.io.Serializable;
import java.util.Date;

public class EventDashboardEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	private String imageid;

	private String imagename;

	private byte[] image;

	private String postedBy;
	
	private Date postedDate;
	
	
	public String getImageid() {
		return imageid;
	}

	public Date getPostedDate() {
		return postedDate;
	}

	public void setPostedDate(Date postedDate) {
		this.postedDate = postedDate;
	}

	public void setImageid(String imageid) {
		this.imageid = imageid;
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

	public String getPostedBy() {
		return postedBy;
	}

	public void setPostedBy(String postedBy) {
		this.postedBy = postedBy;
	}

}
