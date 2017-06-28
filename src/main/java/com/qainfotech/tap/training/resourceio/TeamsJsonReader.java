
package com.qainfotech.tap.training.resourceio;

import com.qainfotech.tap.training.resourceio.exceptions.ObjectNotFoundException;
import com.qainfotech.tap.training.resourceio.model.Individual;
import com.qainfotech.tap.training.resourceio.model.Team;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;




/**
 *
 * @author Ramandeep RamandeepSingh AT QAInfoTech.com
 */
public class TeamsJsonReader{
	
	
    
    /**
     * get a list of individual objects from db json file
     * 
     * @return 
     * @throws  
     */
	
	
    public List<Individual> getListOfIndividuals(){
       // throw new UnsupportedOperationException("Not implemented.");
    	List<Individual> individual_List=null;
    	JSONObject jsonObject;
    	
    	try
    	{
    		
    		JSONParser parser=new JSONParser();
    		Object obj = parser.parse(new FileReader("src/main/resources/db.json"));
    		jsonObject=(JSONObject)(obj);
    		
    		individual_List=new ArrayList<>();
	     	
	    	JSONArray JsonArray=(JSONArray) jsonObject.get("individuals");    	
    	
	    	for(int i=0;i<JsonArray.size();i++)
	    	{
	    		JSONObject JS_OB=(JSONObject)JsonArray.get(i);   
	    		
	    		Map<String,Object> copy_map=JS_OB;
	    		
	    		Individual individual=new Individual(copy_map);
	    		
	    		individual_List.add(individual);   		
	    	}
    	}
    	catch(IOException io)
    	{
			System.out.print(io);
		}
    	catch(ParseException pe)
    	{
    		System.out.print(pe);
		}
    	return individual_List;
    	
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
    	List<Individual> individual_List=null;
        
    	individual_List=getListOfIndividuals();
    	
    	Iterator<Individual>itr=individual_List.iterator();
    	
    	Individual individual=null;
    	while(itr.hasNext()){
    		 individual=itr.next();
    		if(individual.getId()==id.intValue()){
    		    			return individual;
    		}    			
    	}
    	throw new ObjectNotFoundException("object not found exception", "id", id.toString());
    	    	
    }
    
    /**
     * get individual object by name
     * 
     * @param name
     * @return 
     * @throws com.qainfotech.tap.training.resourceio.exceptions.ObjectNotFoundException 
     */
    public Individual getIndividualByName(String name) throws ObjectNotFoundException{
       // throw new UnsupportedOperationException("Not implemented.");
    	List<Individual> individualList=null;
    	individualList=getListOfIndividuals();
    	Iterator<Individual>itr=individualList.iterator();
    	Individual individual=null;
    	while(itr.hasNext()){
    		 individual=itr.next();
    		if(individual.getName().equalsIgnoreCase(name))
    		{
    			
    			return individual;
    			
    		}
    			
    	}
    	
    	throw new ObjectNotFoundException("Object not found exception", "Name" ,name);
    }
    
    
    /**
     * get a list of individual objects who are not active
     * 
     * @return List of inactive individuals object
     */
    public List<Individual> getListOfInactiveIndividuals(){
        //throw new UnsupportedOperationException("Not implemented.");
    	 
    	List<Individual> Inactive;
    	 
    	List<Individual> individualList=null;
    	individualList=getListOfIndividuals();
    	Iterator<Individual>itr=individualList.iterator();
        Inactive=new ArrayList<>();
        System.out.print("individual+individual");
    	Individual individual=null;
    	while(itr.hasNext()){
    		 individual=itr.next();
    		if(!(individual.isActive()))
    			Inactive.add(individual);
    		//System.out.print("individual"+individual);
    	}
    	return Inactive;
    }
    
    /**
     * get a list of individual objects who are active
     * 
     * @return List of active individuals object
     */
    public List<Individual> getListOfActiveIndividuals(){
        //throw new UnsupportedOperationException("Not implemented.");
    	List<Individual> Active=null;
    	
    	List<Individual> individualList=null;
    	
    	individualList=getListOfIndividuals();
    	
    	Iterator<Individual>itr=individualList.iterator();
    	
    	Active=new ArrayList<>();
    	
    	Individual individual=null;
    	
    	while(itr.hasNext()){
    		 individual=itr.next();
    		if(individual.isActive())
    			Active.add(individual);
    	}
    	return Active;
    }
    	
    	
    
    
    /**
     * get a list of team objects from db json
     * 
     * @return 
     */
    public List<Team> getListOfTeams(){
       
    	/*int i,j;
    	TeamsJsonReader read=new TeamsJsonReader();*/
    	Map<String,Object> teamMap=new HashMap<>();
    	try
    	{
    		int i,j;
        	TeamsJsonReader read=new TeamsJsonReader();
    		JSONParser parser=new JSONParser();
    	   	JSONObject obj =(JSONObject) parser.parse(new FileReader("src/main/resources/db.json"));
    	   	JSONObject jsonObj=(JSONObject)(obj);
    	   	List<Team> teamList=new ArrayList<>();
    	   	JSONArray teamArray=(JSONArray) jsonObj.get("teams)");
    	   	
    	   	for(i=0;i<teamArray.size();i++)
    	   	{
    	   		List<Individual> memberList=new ArrayList<>();
    	   		JSONObject JS_OB=(JSONObject)teamArray.get(i);
    	   		teamMap.put("name",(Object) JS_OB.get("name"));
    	   		teamMap.put("id", (Object)JS_OB.get("id"));
    	   		
    	   		JSONArray memberArray=(JSONArray) JS_OB.get("members");
    	   		for(j=0;j<memberList.size();j++)
    	   		{
    	   			memberList.add(read.getIndividualById(((Long)memberArray.get(j)).intValue()));
    	   			
    	   		}
    	   		
    	   	
    	   	teamMap.put("members",memberList);
    	   	
    	   	teamList.add(new Team(teamMap));
    	//   	System.out.print("yahoo");
    	   	}
    	 
    	return teamList;
    	}
    	 catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ObjectNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	return null;
   
    }
    
		
		
		
		
    }
    	    	 
    
    
    

    
    
    
    
    
    
    
    
