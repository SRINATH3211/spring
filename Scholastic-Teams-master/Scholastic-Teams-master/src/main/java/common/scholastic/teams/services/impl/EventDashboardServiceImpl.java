package common.scholastic.teams.services.impl;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import common.scholastic.teams.dao.EventDashboardDAO;
import common.scholastic.teams.dto.EventDashboardDTO;
import common.scholastic.teams.entities.EventDashboardEntity;
import common.scholastic.teams.services.EventDashboardService;

@Service
public class EventDashboardServiceImpl implements EventDashboardService {
	@Autowired
	public EventDashboardDAO eventDashboardDAO;

	@Override
	public List<EventDashboardEntity> getEventDashboardImages(EventDashboardEntity entity) { // map to dao
		List<EventDashboardEntity> responseList = new ArrayList<EventDashboardEntity>();
		List<EventDashboardDTO> listofImages = eventDashboardDAO.getEventDashboardImages(entity.getPostedBy(),
				entity.getPostedDate());
		for (EventDashboardDTO result : listofImages) {
			EventDashboardEntity responseData = new EventDashboardEntity();
			responseData.setPostedBy(result.getPostedBy());
			responseData.setPostedDate(result.getPostedDate());
			responseData.setImage(result.getImage());
			responseList.add(responseData);
		}
		return responseList;
	}
	
	public String addEventDashboardImages(List<EventDashboardEntity> listtostoreimages) throws Exception {
		System.out.println("invoked service");
		List<EventDashboardDTO> sender = new ArrayList<EventDashboardDTO>();
		
		for (EventDashboardEntity result : listtostoreimages) {
			EventDashboardDTO senderdto = new EventDashboardDTO();
			senderdto.setImage(result.getImage());
			senderdto.setImage_uid(result.getImageid());
			senderdto.setImagename(result.getImagename());
			senderdto.setPostedBy(result.getPostedBy());
			senderdto.setPostedDate(getCurrentDate());

			sender.add(senderdto);

			System.out.println(result.getImage());
			System.out.println(result.getImagename());
			System.out.println(result.getPostedBy());
			System.out.println(senderdto.getPostedDate());
		}
		eventDashboardDAO.insertEventDashboardImages(sender);
		return "success";

	}
	
	public static Date getCurrentDate() {

		Date currentDdate = null;
		try {
			
			SimpleDateFormat dateformat = new SimpleDateFormat("dd/MM/yy");
			String stringDate = dateformat.format(new java.util.Date());
			currentDdate = dateformat.parse(stringDate);
			
		} catch (ParseException e) {
			
			e.printStackTrace();
		}
		return currentDdate;
	}

}
