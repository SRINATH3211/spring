package common.scholastic.teams.Quesnaire.repository;

import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import common.scholastic.teams.entities.Quesnairee;
import common.scholastic.teams.entities.Quesnairee;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

@Service
public class QuesnaireJDBCRepository {
	@Autowired
	JdbcQuesnaireRepositary querep;
	public Quesnairee save(Quesnairee quesnaire) {
		querep.save(quesnaire);
		return quesnaire;
	}
	public List<Quesnairee>findAll(){
		return querep.findAll();
	}
	
	public void deleteById(int id) {
		querep.deleteById(id);
	}
	public String findByName(Quesnairee quename) {
		return querep.findByName(quename);
	}
}
	
	

