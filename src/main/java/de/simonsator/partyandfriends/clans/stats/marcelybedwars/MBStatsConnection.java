package de.simonsator.partyandfriends.clans.stats.marcelybedwars;

import de.simonsator.partyandfriends.communication.sql.SQLCommunication;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.UUID;

/**
 * @author simonbrungs
 * @version 1.0.0 17.01.17
 */
public class MBStatsConnection extends SQLCommunication {
	protected MBStatsConnection(String pDatabase, String pURL, String pUserName, String pPassword) {
		super(pDatabase, pURL, pUserName, pPassword);
	}

	public PlayerData getPlayerData(UUID pUUID) {
		Connection con = getConnection();
		Statement stmt = null;
		ResultSet rs = null;
		try {
			rs = (stmt = con.createStatement()).executeQuery("select bedsDestroyed, kills, deaths, loses, wins, roundsPlayed, rank, playTime from `"
					+ DATABASE + "`." + "MBedwars_stats WHERE UUID='" + pUUID.toString() + "' LIMIT 1");
			if (rs.next())
				return new PlayerData(rs.getInt("wins"), rs.getInt("bedsDestroyed"), rs.getInt("deaths"),
						rs.getInt("kills"), rs.getInt("loses"), rs.getInt("roundsPlayed"), rs.getInt("rank"), rs.getInt("playTime"));
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		} finally {
			close(rs, stmt);
		}
		return null;
	}
}
