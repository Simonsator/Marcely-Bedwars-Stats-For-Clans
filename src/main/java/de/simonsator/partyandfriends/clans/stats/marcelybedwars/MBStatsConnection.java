package de.simonsator.partyandfriends.clans.stats.marcelybedwars;

import de.simonsator.partyandfriends.communication.sql.MySQLData;
import de.simonsator.partyandfriends.communication.sql.SQLCommunication;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

/**
 * @author simonbrungs
 * @version 1.0.0 17.01.17
 */
public class MBStatsConnection extends SQLCommunication {
	protected MBStatsConnection(MySQLData pMySQLData) {
		super(pMySQLData);
	}

	public PlayerData getPlayerData(UUID pUUID) {
		Connection con = getConnection();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			// Initialize variables to store the stats
			int wins = 0, bedsDestroyed = 0, deaths = 0, kills = 0, loses = 0, roundsPlayed = 0, rank = 0, playTime = 0;

			// Query the new key-value table structure
			stmt = con.prepareStatement("SELECT `key`, `value` FROM mbedwars_player_stats WHERE player_uuid = ?");
			stmt.setString(1, pUUID.toString());
			rs = stmt.executeQuery();

			// Process each key-value pair
			while (rs.next()) {
				String key = rs.getString("key");
				double value = rs.getDouble("value");

				switch (key) {
					case "bedwars:wins":
						wins = (int) value;
						break;
					case "bedwars:beds_destroyed":
						bedsDestroyed = (int) value;
						break;
					case "bedwars:deaths":
						deaths = (int) value;
						break;
					case "bedwars:kills":
						kills = (int) value;
						break;
					case "bedwars:loses":
						loses = (int) value;
						break;
					case "bedwars:rounds_played":
						roundsPlayed = (int) value;
						break;
					case "bedwars:play_time":
						playTime = (int) value;
						break;
					// Note: rank is not present in the sample data, keeping default value of 0
				}
			}

			// Return PlayerData with the collected values
			return new PlayerData(wins, bedsDestroyed, deaths, kills, loses, roundsPlayed, rank, playTime);

		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		} finally {
			close(rs, stmt);
		}
	}
}