package fr.weefle.wazeapi.nms;

import org.bukkit.entity.Player;
import fr.weefle.wazeapi.Reflection;
import fr.weefle.wazeapi.WazeAPI;

public class AutoRespawnOld implements AutoRespawn {
	
	Reflection reflection = new Reflection();
	
	private WazeAPI main;
	public AutoRespawnOld(WazeAPI main) {
		this.main = main;
	}
	
	public void respawn(Player p) {
		main.getServer().getScheduler().scheduleSyncDelayedTask(main, new Runnable() {
			
			@Override
			public void run() {
				if(p.isDead()) {
					try {
						Object nmsplayer = p.getClass().getMethod("getHandle").invoke(p);
					Object packet = Class.forName(nmsplayer.getClass().getPackage().getName() + ".PacketPlayInClientCommand").newInstance();
					Class<?> enumclass = Class.forName(nmsplayer.getClass().getPackage().getName() + ".PacketPlayInClientCommand$EnumClientCommand");
					
			        for(Object obj : enumclass.getEnumConstants()) {
			        	if(obj.toString().equals("PERFORM_RESPAWN")) {
			        		packet = packet.getClass().getConstructor(enumclass).newInstance(obj);
			        	}
			        }
			        reflection.getConnection(p).getClass().getMethod("a", packet.getClass()).invoke(reflection.getConnection(p), packet);
				}catch(Exception e) {
					e.printStackTrace();
				}
				
			}
		}
		
	});

}
}
