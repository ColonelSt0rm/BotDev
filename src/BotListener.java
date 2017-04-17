import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

public class BotListener extends ListenerAdapter {
	
	private Leaderboard lb = new Leaderboard();
	
	private boolean debug = false;

	public void onMessageReceived(MessageReceivedEvent e){
		
		if(!e.getAuthor().isBot()){
			if(!e.getAuthor().isBot() && debug)
				e.getChannel().sendMessage("Processing: " + e.getMessage().getRawContent()).complete();
			
			if(e.getMessage().getRawContent().startsWith("\\")){
	
			}
			
			else if(!e.getMessage().getAttachments().isEmpty()){
				e.getChannel().sendMessage("Dank meme, adventurer").complete();
			}
			
			//hello there
			
			
			
			else{
			
				if(e.getMessage().getRawContent().equalsIgnoreCase("ping")){
					e.getChannel().sendMessage("pong").complete();
				}
				
				if(e.getMessage().getRawContent().equalsIgnoreCase("Identify yourself")){
					e.getChannel().sendMessage("I am 40177-Visgarn, \"MC\"").complete();
				}
				if(e.getMessage().getRawContent().equalsIgnoreCase("debug") && !debug){
					e.getChannel().sendMessage("Activating debug subsystems...").queue();
					this.debug = true;
					e.getChannel().sendMessage("Done. Debug mode is now ON").complete();
				}
				
				else if(e.getMessage().getRawContent().equalsIgnoreCase("debug") && debug){
					e.getChannel().sendMessage("Deactivating debug subsystems...").complete();
					this.debug = false;
					e.getChannel().sendMessage("Done. Debug mode is now OFF").complete();
				}
				
				if(e.getMessage().getRawContent().contains("MC")) {
					e.getChannel().sendMessage("Greetings").complete();
				}
				
				if(e.getMessage().getRawContent().contains("I control the cubes")){
					lb.addUser(e.getAuthor().getName());
					e.getChannel().sendMessage(e.getAuthor().getAsMention() + 
							" controls the cubes. Compared to him? You are nothing.").complete();
				}
				
				if(e.getMessage().getRawContent().equals("leaderboard")){
					lb.printLeaderboard(e);
				}
				
				if(e.getMessage().getRawContent().startsWith("-")) {
					getCommand(e.getMessage().getRawContent(), e);
				}
			}
		}
	}
	
	public void getCommand(String input, MessageReceivedEvent e){
		String rawinput = input.substring(1, 3);
		
		String argument = input.substring(4, input.length());
		
		String suffix = input.substring(input.length()-4, input.length());
		
		
		
		//System.out.println(argument);
		
		
		if(rawinput.contentEquals("sb")){
			e.getChannel().sendMessage("No soundboard yet, soon(TM)").complete();
		}
		
		if(rawinput.contentEquals("bl")){
			String newOut = buildBlueLetterString(argument);
			e.getChannel().sendMessage(newOut).complete();
		}
		
	}
	
	private String buildBlueLetterString(String in){
		String out = "";
		
		for(int i = 0; i < in.length(); i++) {
			if(Character.isAlphabetic(in.charAt(i))){
				out = out + ":regional_indicator_";
				out = out + Character.toLowerCase(in.charAt(i));
				out = out + ": ";
			}
			else if(in.charAt(i) == ' '){
				out += "  ";
			}
			else
				out += in.charAt(i);
		}
		
		return out;
	}
	
	
}
