package common.scholastic.teams.services;

import java.util.List;

import org.springframework.stereotype.Service;

import common.scholastic.teams.entities.EventDashboardEntity;

@Service
public interface EventDashboardService  {

	public List<EventDashboardEntity>  getEventDashboardImages(EventDashboardEntity entity);
	
	public String addEventDashboardImages(List<EventDashboardEntity> listtostoreimages) throws Exception;

}
