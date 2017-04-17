import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import net.dv8tion.jda.core.entities.User;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

public class Leaderboard {
	
	private class Controller{
		private int cubeControls;
		private String name;
		
		public Controller(String name) {
			this.name = name;
			this.cubeControls = 0;
		}
		
		public String getName(){
			return this.name;
		}
		
		public int getStanding(){
			return this.cubeControls;
		}
		
		public void upStanding(){
			this.cubeControls++;
		}
		
	}

	private ArrayList<Controller> users;
	
	public Leaderboard(){
		this.users = new ArrayList<Controller>();
	}
	
	public void printLeaderboard(MessageReceivedEvent e){
		if(!users.isEmpty()){	
			String out = "The Cube Control Leaderboard (since last MC restart):\n";
			for(int i = 0; i < users.size(); i++){
				out += users.get(i).getName();
				out += ": " + users.get(i).getStanding() + "\n";
			}
			e.getChannel().sendMessage(out).complete();
		}	
	}
	
	private boolean checkExists(String name){
		for(int i = 0; i < users.size(); i++){
			if(users.get(i).getName().equals(name)){
				return true;
			}
		}
		
		
		return false;
	}
	
	private void incStanding(String name){
		for(int i = 0; i < users.size(); i++){
			if(users.get(i).getName().equals(name)){
				users.get(i).upStanding();
			}
		}
	}
	
	public boolean addUser(String name){
		
		boolean out;
		
		if(!checkExists(name)){
			users.add(new Controller(name));
			out = true;
		}
		else{
			out = false;
		}
		
		incStanding(name);
		
		return out;
		
	}
	
	
}

/*
 * 
	
	Collections.sort(users, new Comparator<Controller>() {

		@Override
		public int compare(Controller arg0, Controller arg1) {
			if(arg0.cubeControls > arg1.getStanding()){
				return 1;
			}
			else
				return 0;
		}
	}
		
	);
 */


