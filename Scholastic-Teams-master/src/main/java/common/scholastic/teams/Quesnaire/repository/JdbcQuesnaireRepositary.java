package common.scholastic.teams.Quesnaire.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import common.scholastic.teams.entities.Quesnairee;


@Repository
public class JdbcQuesnaireRepositary {

	@Autowired
    JdbcTemplate jdbc;
	
	class OuesnaireeRowMapper implements RowMapper < Quesnairee > {
        @Override
        public Quesnairee mapRow(ResultSet rs, int rowNum) throws SQLException {
        	Quesnairee e = new Quesnairee();
        	e.setQueid(rs.getInt(1));
            e.setQuename(rs.getString(2));  
            e.setQuedesc(rs.getString(3));    
            e.setCreatedby(rs.getString(4));
            e.setUpdateby(rs.getString(5));    
            e.setDeleteby(rs.getString(6));  
            e.setAvtive(rs.getString(7));    
            return e;    
        }
    }
	
	

//    public int save(Quesnaire p){    
//        String sql="insert into Quesnaire(id,quename,quediscr,createdby,updateby,deleteby,active)  values("+"\""+p.getId()+"\""+","+"\""+p.getQuename()+"\""+","+"\""+p.getQuediscr()+"\""+","+"\""+p.getCreatedby()+"\""+","+"\""+p.getUpdateby()+"\""+"+",.getActive()+"\""+")";
//        return jdbc.update(sql);    
//    } 
//   


public List<Quesnairee> findAll(){    
    return jdbc.query("select * from Quesnairee ",new RowMapper<Quesnairee >(){    
        public Quesnairee mapRow(ResultSet rs, int row) throws SQLException {    
        	Quesnairee e=new Quesnairee();    
            e.setQueid(rs.getInt(1));
            e.setQuename(rs.getString(2));  
            e.setQuedesc(rs.getString(3));    
            e.setCreatedby(rs.getString(4));
            e.setUpdateby(rs.getString(5));    
            e.setDeleteby(rs.getString(6));  
            e.setAvtive(rs.getString(7));    
            return e;    
        }    
    }); 

}

public int save(Quesnairee p){    
    String sql="insert into Quesnairee(quename,quedesc,createdby,updateby,deleteby,avtive)  values("+"\""+p.getQuename()+"\""+","+"\""+p.getQuedesc()+"\""+","+"\""+p.getCreatedby()+"\""+","+"\""+p.getUpdateby()+"\""+","+"\""+p.getDeleteby()+"\""+","+"\""+p.getAvtive()+"\""+")";
    return jdbc.update(sql);    
}  

public int deleteById(int id){    
    String sql="delete from Quesnairee where queid="+id+"";    
    return jdbc.update(sql);    
} 

public  String findByName(Quesnairee quename) {
	String sql=this.jdbc.queryForObject("select quedesc from Quesnairee where quename=?",new Object[] {1212l},String.class);
		return sql;
}}

		
		

