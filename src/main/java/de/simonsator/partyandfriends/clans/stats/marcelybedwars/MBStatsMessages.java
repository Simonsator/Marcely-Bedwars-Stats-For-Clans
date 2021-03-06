package de.simonsator.partyandfriends.clans.stats.marcelybedwars;

import de.simonsator.partyandfriends.utilities.Language;
import de.simonsator.partyandfriends.utilities.LanguageConfiguration;

import java.io.File;
import java.io.IOException;

/**
 * @author simonbrungs
 * @version 1.0.0 30.11.16
 */
public class MBStatsMessages extends LanguageConfiguration {

	public MBStatsMessages(Language pLanguage, File pFile) throws IOException {
		super(pLanguage, pFile);
		readFile();
		loadDefaultValues();
		saveFile();
		process(configuration);
	}

	private void loadDefaultValues() {
		set("Name", "BedWars");
		set("Kills", "&7The clan has &a[KILLS] &7kills.");
		set("Deaths", "&7The clan has died &c[DEATHS] &7times.");
		set("DestroyedBeds", "&7The clan has &a[DESTROYED] &7beds.");
		set("Wins", "&7The clan has won &a[WINS] &7times.");
		set("Defeats", "&7The clan has &c[DEFEATS] &7defeated.");
		set("Played", "&7The clan has &a[PLAYED] &7times.");
		set("Rank", "&7The average rank of the clan is &a[RANK]&7.");
	}

	public void reloadConfiguration() throws IOException {
		configuration = (new MBStatsMessages(Language.OWN, FILE)).getCreatedConfiguration();
	}
}
