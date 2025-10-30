package de.simonsator.partyandfriends.clans.stats.marcelybedwars;

import de.simonsator.partyandfriends.api.PAFExtension;
import de.simonsator.partyandfriends.utilities.ConfigurationCreator;

import java.io.File;
import java.io.IOException;

public class MBStatsConfig extends ConfigurationCreator {
	protected MBStatsConfig(File file, PAFExtension pPlugin) throws IOException {
		super(file, pPlugin, true);
		readFile();
		loadDefaultValues();
		saveFile();
	}

	private void loadDefaultValues() {
		set("database.host", "localhost");
		set("database.port", 3306);
		set("database.db", "minecraft");
		set("database.user", "root");
		set("database.password", "password");
		set("database.use-ssl", false);
	}
}
