import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

public class BotListener extends ListenerAdapter {

	public void onMessageReceived(MessageReceivedEvent e){
		
		if(e.getMessage().getRawContent().startsWith("\\")){

		}
		
		//hello there
		
		else{
		
			if(e.getMessage().getRawContent().equalsIgnoreCase("ping")){
				e.getChannel().sendMessage("pong").complete();
			}
			
			if(e.getMessage().getRawContent().equalsIgnoreCase("Identify yourself")){
				e.getChannel().sendMessage("I am 40177-Visgarn, \"MC\"").complete();
			}
			
			if(e.getMessage().getRawContent().contains("MC")) {
				e.getChannel().sendMessage("Greetings").complete();
			}
			
			if(e.getMessage().getRawContent().startsWith("-")) {
				getCommand(e.getMessage().getRawContent(), e);
			}
		}
	}
	
	public void getCommand(String input, MessageReceivedEvent e){
		String rawinput = input.substring(1, 3);
		
		String argument = input.substring(4, input.length());
		
		//System.out.println(argument);
		
		if(rawinput.contentEquals("sb")){
			e.getChannel().sendMessage("No soundboard yet, soon(TM)").complete();
		}
	}
	
	
}
