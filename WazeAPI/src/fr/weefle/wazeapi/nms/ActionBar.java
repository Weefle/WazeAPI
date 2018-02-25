package fr.weefle.wazeapi.nms;

import java.lang.reflect.InvocationTargetException;
import org.bukkit.entity.Player;

public interface ActionBar {
	
	public void sendActionBar(Player player, String message) throws ClassNotFoundException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException, InstantiationException, NoSuchFieldException;
	
}
