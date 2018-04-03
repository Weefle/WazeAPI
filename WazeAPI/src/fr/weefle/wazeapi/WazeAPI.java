package fr.weefle.wazeapi;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import fr.weefle.wazeapi.nms.*;

public class WazeAPI extends JavaPlugin {
	
	private static WazeAPI instance;
	private ActionBar actionbar;
	private Title title;
	private BossBarAPI bossbar;
	private Ping ping;
	private Particles particles;
	private ScoreBoard scoreboard;
	private AutoRespawn autorespawn;
	
	@Override
	public void onEnable() {
		if (setupNMS()) {

			getLogger().info("NMS setup was successful!");
			getLogger().info("The plugin setup process is complete!");

		} else {

			getLogger().severe("Failed to setup NMS!");
			getLogger().severe("Your server version is not compatible with this plugin!");

			Bukkit.getPluginManager().disablePlugin(this);
		}
		instance = this;
        }

	private boolean setupNMS() {

		String version;

		try {

			version = Bukkit.getServer().getClass().getPackage().getName().replace(".",  ",").split(",")[3];

		} catch (ArrayIndexOutOfBoundsException exception) {
			return false;
		}

		getLogger().info("Your server is running version " + version);
		if (version.equals("v1_12_R1")) {
			title = new Title();
			autorespawn = new AutoRespawnNew();
			scoreboard = new ScoreBoard();
			bossbar = new BossBarNew(this);
			actionbar = new ActionBarNew();
			ping = new Ping();
			particles = new Particles();

        } else if (version.equals("v1_8_R3")) {
        	title = new Title();
    		scoreboard = new ScoreBoard();
    		autorespawn = new AutoRespawnOld(this);
    		bossbar = new BossBarOld();
    		actionbar = new ActionBarOld();
    		ping = new Ping();
    		particles = new Particles();
        }else if (version.equals("v1_7_R4")){
    		scoreboard = new ScoreBoard();
    		autorespawn = new AutoRespawnOld(this);
    		ping = new Ping();
    		bossbar = new BossBarOld();
    		particles = new Particles();
    }else {
    	title = new Title();
    	autorespawn = new AutoRespawnNew();
		scoreboard = new ScoreBoard();
		bossbar = new BossBarNew(this);
		actionbar = new ActionBarOld();
		ping = new Ping();
		particles = new Particles();
    }
		return true;
	}
    public ActionBar getActionbar() {
        return actionbar;
    }
    public Title getTitle() {
        return title;
    }
    public static WazeAPI getInstance(){
	    return instance;
    }
    public BossBarAPI getBossBar(){
        return bossbar;
    }
    public Ping getPing(){
        return ping;
    }
    public ScoreBoard getScoreBoard(){
	    return scoreboard;
    }
    
    public AutoRespawn getAutoRespawn(){
	    return autorespawn;
    }
    
    public Particles getParticles(){
	    return particles;
    }

}