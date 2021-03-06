import java.util.List;

import javax.security.auth.login.LoginException;

import net.dv8tion.jda.core.AccountType;
import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.JDABuilder;
import net.dv8tion.jda.core.entities.TextChannel;
import net.dv8tion.jda.core.exceptions.RateLimitedException;

public class Bot {

	private static JDA jda;
	
	private static List<TextChannel> tChannels;
	
	public static final String BOT_TOKEN = "MzAzNDAxODE5ODk4NjQyNDMz.C9XnGw.OAddH4VhyFG8FPQ2HAGOz0Qvj1c";
	
	
	//Just a small change
	
	public static void main(String[] args) {
		try {
			jda = new JDABuilder(AccountType.BOT).addListener(new BotListener()).setToken(BOT_TOKEN).buildBlocking();
			tChannels = jda.getTextChannels();
			for(int i = 0; i< tChannels.size(); i++){
				tChannels.get(i).sendMessage("MC is online and ready to respond").complete();
			}
			
		} catch (LoginException | IllegalArgumentException | InterruptedException | RateLimitedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
