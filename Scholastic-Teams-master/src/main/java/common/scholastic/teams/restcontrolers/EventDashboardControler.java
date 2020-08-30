package common.scholastic.teams.restcontrolers;

import java.io.File;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import javax.validation.ValidationException;

import org.apache.commons.io.FilenameUtils;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import ch.qos.logback.classic.Logger;

//import com.example.demo.Employee;

import common.scholastic.teams.Quesnaire.repository.JdbcQuesnaireRepositary;
//import common.scholastic.teams.Quesnaire.repository.Quesnaire;
import common.scholastic.teams.Quesnaire.repository.QuesnaireJDBCRepository;

//import com.scholastic.teams.repo.Quesrepo;

import common.scholastic.teams.entities.EventDashboardEntity;
import common.scholastic.teams.entities.Quesnairee;
//import common.scholastic.teams.entities.Quesnaire;
import common.scholastic.teams.services.impl.EventDashboardServiceImpl;
@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/fau")
public class EventDashboardControler {
//	private QuesnaireSender quesnaireSender;
	@Autowired
	QuesnaireJDBCRepository quesev;
	public JdbcQuesnaireRepositary jdbc;
	
	Map<String,Quesnairee> map= new HashMap<String,Quesnairee>();
	String requestforUpdate="";
	
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
	

    @GetMapping(value = "/que")
    public List<Quesnairee> getAllQuesnaire(){
        return quesev.findAll();
    }
 
    
	
    @PostMapping("/quesnaire")
	public Quesnairee  saveQue(@RequestBody Quesnairee quesnaire) {
		return quesev.save( quesnaire);
	}
    
    
    @DeleteMapping(value="/quesnaire/{id}")
    public void studentDelete(@PathVariable Integer id) {
    	quesev.deleteById(id);
    }
	
	@GetMapping(value="/quee/{quename}")
	
	public String Description(@PathVariable Quesnairee quename) {
		return quesev.findByName(quename);
	}
	
//	@GetMapping(value = "/quesnair/{quename}")
//	public Quesnaire getQue(@PathVariable("quename") String quename) {
//		return quesev.findByString(quename); 
//	}
//	
	
//	 @PostMapping("/quesnaire")
//	 public String editsave(@ModelAttribute("quesnaire") Quesnaire quesnaire) {
//		 	System.out.println(quesnaire);
////		 	map.put(quesnaire.getId(), quesnaire);
//			map.put(quesnaire.getQuename(), quesnaire);
//			map.put(quesnaire.getQuediscr(),quesnaire );
//			map.put(quesnaire.getCreatedby(),quesnaire );
//			map.put(quesnaire.getUpdateby(),quesnaire);
//			map.put(quesnaire.getDeleteby(),quesnaire);
//			map.put(quesnaire.getActive(),quesnaire);		
//			jdbc.findAll();	
//			return "redirect:/viewresults";
//		}
//	 @RequestMapping(value="/que", method=RequestMethod.POST)
//	 public List<Quesnairee>getQuesnaire(){
//		 return jdbc.findAll();
//	 }
//	 @RequestMapping(value="/quesnaire", method=RequestMethod.POST)
//	 public int insertQuesnaire(@RequestBody Quesnairee quesnaire) {
//		 return jdbc.insert(quesnaire);
//	 }
//	
//	@RequestMapping(value = "/quesnaire", method = RequestMethod.POST)
//	public String save(@ModelAttribute("quesnaire") Quesnaire quesnaire) {
//	    jdbc.save(quesnaire);
//	      
//	    return "redirect:/";
//	}
		
	 
	
	public void test(){
	}	

}
