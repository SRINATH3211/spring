package common.scholastic.teams.dao;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import common.scholastic.teams.dto.EventDashboardDTO;
import common.scholastic.teams.utilities.ConfigUtil;

@Repository
public class EventDashboardDAO {
	
	private static final String selectImagesEventDashboard= ConfigUtil.getProperty("selectImagesEventDashboard");
	private static final String storeImagesEventDashboard= ConfigUtil.getProperty("storeImagesEventDashboard");

	@Autowired
	private JdbcTemplate template;

	public void setJdbcTemplate(JdbcTemplate template) {
		this.template = template;
	}

	public List<EventDashboardDTO> getEventDashboardImages(String postedby, Date postedDate) {
	
		ArrayList<EventDashboardDTO> listofimages = new ArrayList<EventDashboardDTO>();

		try {
			listofimages = (ArrayList<EventDashboardDTO>) template.query(
					selectImagesEventDashboard,
					new Object[] { postedby, getCurrentDate(postedDate) },
					new BeanPropertyRowMapper<EventDashboardDTO>(EventDashboardDTO.class));
		} catch (DataAccessException e) {
			e.printStackTrace();
		}
		
		for (EventDashboardDTO result : listofimages) {
			System.out.println("PostedBy"+result.getPostedBy());
			System.out.println("Image"+result.getImage());
			
		}
		
		return listofimages;
	}
	
	public String insertEventDashboardImages(List<EventDashboardDTO> sender) {
		System.out.println("invoked dao");
		for (EventDashboardDTO result : sender) {

			System.out.println(result.getImagename());
			template.update(
					storeImagesEventDashboard,
					new Object[] { result.getImage_uid(), result.getImagename(), result.getPostedBy(),
							result.getPostedDate(), result.getImage() });
		}

		return "saved in DB";

	}
	
	public  Date getCurrentDate(Date date) {

		Date getDate = null;
		try {
			
			SimpleDateFormat dateformat = new SimpleDateFormat("dd/MM/yyyy");
			String stringDate = dateformat.format(date);
			getDate = dateformat.parse(stringDate);
			
		} catch (ParseException e) {
			
			e.printStackTrace();
		}
		return getDate;
	}

}
