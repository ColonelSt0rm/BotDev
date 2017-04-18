import com.sun.speech.freetts.VoiceManager;
import com.sun.speech.freetts.audio.SingleFileAudioPlayer;
import java.io.*;


public class Voice {

		private String name;
		
		private com.sun.speech.freetts.Voice voice;
		
		private com.sun.speech.freetts.audio.SingleFileAudioPlayer sfap;
		
		
		public Voice(String name) {
			this.name = name;
			this.voice = VoiceManager.getInstance().getVoice(this.name);
			this.voice.allocate();
			this.sfap = new SingleFileAudioPlayer();
			this.voice.setAudioPlayer(sfap);
		}
		
		public File say(String something) {
			File out = new File("tmp.wav");
			this.voice.setWaveDumpFile("tmp.wav");
			this.voice.speak(something);
			return out;
		}
}
