import java.util.HashMap;
import java.util.Map;

public class NewPlayer {
	public static final int MP4 = 0;
	public static final int VIC = 1;
	public static final int AUDIO = 2;
	
	private Map<Integer, MediaPlayer> players = new HashMap<>();
	
	public NewPlayer() {
		preparePlayers();
	}
	
	protected void preparePlayers() {
		players.put(MP4, new MP4Adapter());
		players.put(VIC, new VICAdapter());
		players.put(AUDIO, new AudioAdapter());
	}
	
	public void play(int type) {
		players.get(type).play();
	}
	
}
