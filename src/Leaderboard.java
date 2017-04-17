import java.util.ArrayList;

import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

public class Leaderboard {

	private ArrayList<User> users;
	
	public Leaderboard(){
		this.users = new ArrayList<User>();
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
			users.add(new User(name));
			out = true;
		}
		else{
			out = false;
		}
		
		incStanding(name);
		
		return out;
		
	}
	
	
	private class User{
		private int cubeControls;
		private String name;
		
		public User(String name) {
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
	
}
