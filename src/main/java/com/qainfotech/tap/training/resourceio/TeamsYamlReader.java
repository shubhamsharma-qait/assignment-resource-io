package com.qainfotech.tap.training.resourceio;

import com.qainfotech.tap.training.resourceio.exceptions.ObjectNotFoundException;
import com.qainfotech.tap.training.resourceio.model.Individual;
import com.qainfotech.tap.training.resourceio.model.Team;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.yaml.snakeyaml.Yaml;
/**
 *
 * @author Ramandeep RamandeepSingh AT QAInfoTech.com
 */
public class TeamsYamlReader{
    
    /**
     * get a list of individual objects from db yaml file
     * 
     * @return 
     */
	List<Individual> individualList;
    public List<Individual> getListOfIndividuals(){
        //throw new UnsupportedOperationException("Not implemented.");
    	List<Individual> individualList=new ArrayList();
    	try
    	{
    		Yaml yaml = new Yaml();
    	InputStream ios = new FileInputStream(new File("src/main/resources/db.yaml"));
    	
    	
    	Map<String,Object> result = (Map<String,Object>)yaml.load(ios);
    	ArrayList yamlList = (ArrayList) result.get("individuals");
    	
    	for (int i = 0; i < yamlList.size(); i++) 
    	{
			Map map = (Map<String, Object>)yamlList.get(i);
			
			//System.out.println("Map Values........"+map);
			 Map<String, Object> myMap = new HashMap<String, Object>();
			
			myMap.put("name",map.get("name"));
			myMap.put("id",map.get("id"));
			myMap.put("active",map.get("active"));
			
			
    	Individual individualObject = new Individual(myMap);
    	individualList.add(individualObject);
    	}
    	}
    	catch(Exception e)
    	{
    		System.out.println(e);
    	}
    	return individualList;
    	
    }
    
    

	/**
     * get individual object by id
     * 
     * @param id individual id
     * @return 
     * @throws com.qainfotech.tap.training.resourceio.exceptions.ObjectNotFoundException 
     */
    public Individual getIndividualById(Integer id) throws ObjectNotFoundException{
        //throw new UnsupportedOperationException("Not implemented.");
    	 Individual individual = null;
    	List<Individual> individualList=getListOfIndividuals();
    	 int c=0;
        for(int i=0;i<individualList.size();i++)
        {
        	  individual = individualList.get(i);
        	  
        	  if(individual.getId().equals(id))
        	  {
        		  c=1;
        		  break;
        	  }
        }
        if(c==1)
         return individual;
        else throw new ObjectNotFoundException("Individual", "id", id.toString());
    }
    
    /**
     * get individual object by name
     * 
     * @param name
     * @return 
     * @throws com.qainfotech.tap.training.resourceio.exceptions.ObjectNotFoundException 
     */
    public Individual getIndividualByName(String name) throws ObjectNotFoundException 
    {
        //throw new UnsupportedOperationException("Not implemented.");
    	 Individual individual = null;
     	List<Individual> individualList=getListOfIndividuals();
     	 int c=0;
         for(int i=0;i<individualList.size();i++)
         {
         	  individual = individualList.get(i);
         	  
         	  if(individual.getName().equals(name))
         	  {
         		 c=1;
         		  break;
         		  
         	  }
         }
         
         
         if(c==1)
          return individual;
         else throw new ObjectNotFoundException("Individual", "Name", name);
    }
    
    
    /**
     * get a list of individual objects who are not active
     * 
     * @return List of inactive individuals object
     */
    public List<Individual> getListOfInactiveIndividuals(){
       // throw new UnsupportedOperationException("Not implemented.");
   	 Individual individual = null;
   	 List<Individual> inactive= new ArrayList();;
  	List<Individual> individualList=getListOfIndividuals();
  	 
      for(int i=0;i<individualList.size();i++)
      {
      	  individual = individualList.get(i);
      	  
      	  if(individual.isActive()==false)
      	  {
      		  inactive.add(individual);
      	  }
      }
      
      
      
       return inactive;
    }
    
    /**
     * get a list of individual objects who are active
     * 
     * @return List of active individuals object
     */
    public List<Individual> getListOfActiveIndividuals(){
       // throw new UnsupportedOperationException("Not implemented.");
    	Individual individual = null;
      	 List<Individual> active= new ArrayList();;
     	List<Individual> individualList=getListOfIndividuals();
     	 
         for(int i=0;i<individualList.size();i++)
         {
         	  individual = individualList.get(i);
         	  
         	  if(individual.isActive().equals(true))
         	  {
         		  active.add(individual);
         	  }
         }
         
         
         
          return active;
    }
    
    /**
     * get a list of team objects from db yaml
     * 
     * @return 
     */
    public List<Team> getListOfTeams()  {
		File file = new File("src/main/resources/db.yaml");
		InputStream fis = null;
try
{
		fis = new FileInputStream(file);
		org.yaml.snakeyaml.Yaml yaml = new org.yaml.snakeyaml.Yaml();

		Map<String, ArrayList> map = (Map<String, ArrayList>) yaml.load(fis);

		ArrayList<Team> teamlist = map.get("teams");
		List<Team> list = new ArrayList<>();

		TeamsYamlReader reader = new TeamsYamlReader();

		for (int i = 0; i < teamlist.size(); i++) {
			List<Individual> individualList = new ArrayList<>();
			Map<String, ArrayList> singleteam = (Map<String, ArrayList>) teamlist.get(i);
			Map temp = new HashMap();
			temp.put("id", singleteam.get("id"));
			temp.put("name", singleteam.get("name"));
			// List<Individual> memberlist=new ArrayList<>();

			List memberlist = singleteam.get("members"); // ERROR yaha h

			for (int j = 0; j < memberlist.size(); j++) {

				int id = (Integer) memberlist.get(j);
				individualList.add(reader.getIndividualById(id));
			}

			temp.put("members", individualList);

			list.add(new Team(temp));
		}
		return list;
}
	catch(Exception e)
{
		System.out.println(e);
}


		return null;
		
	}
}
