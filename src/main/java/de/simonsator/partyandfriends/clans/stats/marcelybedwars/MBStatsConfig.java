package de.simonsator.partyandfriends.clans.stats.marcelybedwars;

import de.simonsator.partyandfriends.utilities.ConfigurationCreator;

import java.io.File;
import java.io.IOException;

/**
 * @author simonbrungs
 * @version 1.0.0 30.11.16
 */
public class MBStatsConfig extends ConfigurationCreator {
    protected MBStatsConfig(File file) throws IOException {
        super(file);
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

    public void reloadConfiguration() throws IOException {
        configuration = (new MBStatsConfig(FILE)).getCreatedConfiguration();
    }
}
