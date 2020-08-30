package common.scholastic.teams.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

public class Quesnairee {
	  private String quename;
		
	  private String quedesc;
		
	  private String createdby;
		
	  private String updateby;
		
	  private String deleteby;
		
	  private String avtive;
	
	public Quesnairee(String quename, String quedesc, String createdby, String updateby, String deleteby, String avtive,
			int queid) {
		super();
		this.quename = quename;
		this.quedesc = quedesc;
		this.createdby = createdby;
		this.updateby = updateby;
		this.deleteby = deleteby;
		this.avtive = avtive;
		this.queid = queid;
	}

	public Quesnairee() {
		super();
		this.queid = queid;
		this.quename = quename;
		this.quedesc = quedesc;
		this.createdby = createdby;
		this.updateby = updateby;
		this.deleteby = deleteby;
		this.avtive = avtive;
	}

	private int queid;
	
	
	public int getQueid() {
		return queid;
	}

	public void setQueid(int queid) {
		this.queid = queid;
	}

	public String getQuename() {
		return quename;
	}

	public void setQuename(String quename) {
		this.quename = quename;
	}

	public String getQuedesc() {
		return quedesc;
	}

	public void setQuedesc(String quedesc) {
		this.quedesc = quedesc;
	}

	public String getCreatedby() {
		return createdby;
	}

	public void setCreatedby(String createdby) {
		this.createdby = createdby;
	}

	public String getUpdateby() {
		return updateby;
	}

	public void setUpdateby(String updateby) {
		this.updateby = updateby;
	}

	public String getDeleteby() {
		return deleteby;
	}

	public void setDeleteby(String deleteby) {
		this.deleteby = deleteby;
	}

	public String getAvtive() {
		return avtive;
	}

	public void setAvtive(String avtive) {
		this.avtive = avtive;
	}

	
	  

	
}
