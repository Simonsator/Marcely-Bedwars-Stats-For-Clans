package de.simonsator.partyandfriends.clans.stats.marcelybedwars;

import de.simonsator.partyandfriends.api.PAFExtension;
import de.simonsator.partyandfriends.clan.commands.ClanCommands;
import de.simonsator.partyandfriends.clan.commands.subcommands.Stats;
import de.simonsator.partyandfriends.communication.sql.MySQLData;
import de.simonsator.partyandfriends.utilities.Language;
import net.md_5.bungee.config.Configuration;

import java.io.File;
import java.io.IOException;
import java.util.regex.Pattern;

/**
 * @author simonbrungs
 * @version 1.0.0 17.01.17
 */
public class MBStatsMain extends PAFExtension {
	private Configuration config;
	private MBStatsConnection connection;
	private Configuration messagesConfig;

	public void onEnable() {
		try {
			config = (new MBStatsConfig(new File(getConfigFolder(), "config.yml"))).getCreatedConfiguration();
			messagesConfig = (new MBStatsMessages(Language.OWN, new File(getDataFolder(), "messages.yml"))).getCreatedConfiguration();
		} catch (IOException e) {
			e.printStackTrace();
		}
		MySQLData mySQLData = new MySQLData(config.getString("database.host"), config.getString("database.user"), config.getString("database.password"), config.getInt("database.port"), config.getString("database.db"), "", config.getBoolean("database.use-ssl"));
		connection = new MBStatsConnection(mySQLData);
		((Stats) ClanCommands.getInstance().getSubCommand(Stats.class)).registerClanStats(new MBStats(messagesConfig.getString("Name"), connection, Pattern.compile("[KILLS]", Pattern.LITERAL).matcher(messagesConfig.getString("Kills")), Pattern.compile("[WINS]", Pattern.LITERAL).matcher(messagesConfig.getString("Wins")), Pattern.compile("[DEFEATS]", Pattern.LITERAL).matcher(messagesConfig.getString("Defeats")), Pattern.compile("[DEATHS]", Pattern.LITERAL).matcher(messagesConfig.getString("Deaths")), Pattern.compile("[DESTROYED]", Pattern.LITERAL).matcher(messagesConfig.getString("DestroyedBeds")), Pattern.compile("[PLAYED]", Pattern.LITERAL).matcher(messagesConfig.getString("Played")), Pattern.compile("[RANK]", Pattern.LITERAL).matcher(messagesConfig.getString("Rank"))), this);
		registerAsExtension();
	}
}
