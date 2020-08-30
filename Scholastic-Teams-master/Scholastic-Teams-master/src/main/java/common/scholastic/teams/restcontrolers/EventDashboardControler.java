package common.scholastic.teams.restcontrolers;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.UUID;

import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import common.scholastic.teams.entities.EventDashboardEntity;
import common.scholastic.teams.services.impl.EventDashboardServiceImpl;

@RestController
public class EventDashboardControler {
	
	@Autowired
	public EventDashboardServiceImpl eventDashboardServiceImpl; // map to serviceimpl

	@GetMapping(path = "/getEventDashboardImages", consumes = "application/json")
	public List<EventDashboardEntity> getEventDashboardImages(@RequestBody EventDashboardEntity entity) {

		List<EventDashboardEntity> responseList = eventDashboardServiceImpl.getEventDashboardImages(entity);
		return responseList;
	}
	
	@PostMapping(path = "/uploadimages", consumes = { "multipart/form-data" })
	public String uploadmultipleFile(@RequestParam("files") MultipartFile[] files, @RequestParam("postedBy") String postedBy  ) throws Exception {
		
		List<EventDashboardEntity> listtostoreimages = new ArrayList<EventDashboardEntity>();
		 System.out.println("invoked controller" + files);
		for (MultipartFile multipartFile : files) {
			EventDashboardEntity eventDashboardEntity = new EventDashboardEntity();
			UUID uuid = UUID.randomUUID();
			eventDashboardEntity.setImageid(uuid.toString());
			eventDashboardEntity.setImagename(multipartFile.getOriginalFilename());
			eventDashboardEntity.setImage(multipartFile.getBytes());
			eventDashboardEntity.setPostedBy(postedBy);
			listtostoreimages.add(eventDashboardEntity);
		}
		eventDashboardServiceImpl.addEventDashboardImages(listtostoreimages);
		return "success";
	}
	
	
	@GetMapping(value="/getImages")
	public ResponseEntity<List<String>> getImages(){
		List<String> images = new ArrayList<String>();
		File folder = new File("C:\\vinod_learning\\angular_images");
		if(folder != null) {
			for(final File file : folder.listFiles()) {
				if(!file.isDirectory()) {
					String encodeBase64 = null;
					try {
						String extension = FilenameUtils.getExtension(file.getName());
						FileInputStream fileInputStream = new FileInputStream(file);
						byte bytes[] = new byte[(int)file.length()];
						fileInputStream.read(bytes);
						encodeBase64 = Base64.getEncoder().encodeToString(bytes);
						images.add("data:image/"+extension+";base64,"+encodeBase64);
						fileInputStream.close();
					} catch (Exception e) {
					}
				}
			}
		}
		return new ResponseEntity<List<String>>(images, HttpStatus.OK);
	
	}
	
	public void test(){
	}	

}
