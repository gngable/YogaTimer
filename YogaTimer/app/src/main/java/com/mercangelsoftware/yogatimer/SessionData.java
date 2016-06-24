package com.mercangelsoftware.yogatimer;
import java.util.*;

public class SessionData
{
	private int warmUpSeconds = -1;
	private int coolDownSeconds = -1;
	private int repititions = 0;
	private List<SessionActivity> activities = null;
	
	public class SessionActivity{
		public String name = "";
		public int seconds = 0;
		
		public SessionActivity(String name, int seconds){
			this.name = name;
			this.seconds = seconds;
		}
	}
	
	public void fromString(String data){
		if (data == null) return;
		
		String[] lines = data.split("\t");
		
		if (lines.length < 3){
			return;
		}
		
		try{
			
		} catch (Exception ex){
			
		}
	}
	
	public String toString(){
		StringBuilder sb = new StringBuilder();
		
		sb.append(Integer.toString(warmUpSeconds)).append("\n");
		sb.append(Integer.toString(coolDownSeconds)).append("\n");
		sb.append(Integer.toString(repititions)).append("\n");
		
		if (activities != null){
			for (SessionActivity act : activities){
				sb.append(act.name).append("\t").append(Integer.toString(act.seconds)).append("\n");
			}
		}
		
		return sb.toString();
	}
	
	public SessionData(String data){
		if (data != null){
			fromString(data);
		}
	}
}
