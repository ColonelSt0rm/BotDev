import java.util.Random;

import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

public class BotListener extends ListenerAdapter {
	
	private Leaderboard lb = new Leaderboard();
	
	private boolean debug = false;
	
	Random rand = new Random();

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
					String out = "";
					if(e.getMessage().getRawContent().endsWith("?")){
						int choice = rand.nextInt(3);
						if(debug) {
							out += "Got " + choice + " ";
						}
						switch(choice){
						case(0):
							out = "Negative.";
							break;
						case(1):
							out = "Affirmative.";
							break;
						case(2):
							out = "Uncertain.";
							break;
						default:
							out = "Insufficient data for meaningful answer.";
						}
					}
					else
						out = "Greetings";
						
					e.getChannel().sendMessage(out).complete();
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
				
				if(e.getMessage().getRawContent().equalsIgnoreCase("list")){
					e.getChannel().sendMessage(printCommandList()).complete();
				}
			}
		}
	}
	
	public void getCommand(String input, MessageReceivedEvent e){
		String rawinput = input.substring(1, 3);
		String argument;
		
		if(input.length() > 4){
			argument = input.substring(4, input.length());
		}
		else
			argument = "";
		
		//System.out.println(argument);
		
		
		if(rawinput.contentEquals("sb")){
			e.getChannel().sendMessage("No soundboard yet, soon(TM)").complete();
		}
		
		if(rawinput.contentEquals("bl")){
			if(argument.length() != 0){
				String newOut = buildBlueLetterString(argument);
				e.getChannel().sendMessage(newOut).complete();
			}
			else
				e.getChannel().sendMessage("Text parameter required. Please try again.").complete();
		}
		if(rawinput.contentEquals("rl")){
			if(argument.length() != 0) {
				int out = rollDie(argument);
				if(out == 20 && out == Integer.parseInt(argument)){
					e.getChannel().sendMessage("Critical Hit!").complete();
				}
				else
					e.getChannel().sendMessage(
							e.getAuthor().getAsMention() + " rolled " + out + " on a d" + Integer.parseInt(argument)).complete();
			}
			else
				e.getChannel().sendMessage("Side count parameter required. Please try again.").complete();
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
	
	private int rollDie(String argument) {
		int sides = Integer.parseInt(argument);
		
		
		int out = rand.nextInt(sides) + 1;
		
		return out;
		
	}
	
	private String printCommandList(){
		String out = "";
		out += ("Here is a list of the commands I know:\n");
		out += ("'-sb'\t\t\t\t\t\t\t\t\t\tSoundboard (Nonfunctional)\n");
		out += ("'-bl'\t\t\t\t\t\t\t\t\t\tConvert string to dank blue letters\n");
		out += ("'-rl'\t\t\t\t\t\t\t\t\t\tRoll a die, specifying number of sides\n");
		out += ("'I control the cubes'\t\t\t\tControl the cubes\n");
		out += ("'leaderboard'\t\t\t\t\t\tDisplay cube leaderboard\n");
		out += ("'list'\t\t\t\t\t\t\t\t\tDisplay this list of commands\n");
		out += ("Adding 'MC?' to the end of a message triggers my Magic 8-Ball function\n");
		return out;
	}
	
	
}
