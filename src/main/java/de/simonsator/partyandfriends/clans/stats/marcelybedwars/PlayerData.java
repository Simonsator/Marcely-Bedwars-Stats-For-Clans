package de.simonsator.partyandfriends.clans.stats.marcelybedwars;

/**
 * @author simonbrungs
 * @version 1.0.0 30.11.16
 */
public class PlayerData {
	public final int wins;
	public final int deaths;
	public final int kills;
	public final int defeats;
	public final int destroyedBeds;
	public final int played;
	public final int rank;
	public final int playTime;

	public PlayerData(int wins, int destroyedBeds, int deaths, int kills, int defeats, int played, int rank, int playTime) {
		this.wins = wins;
		this.destroyedBeds = destroyedBeds;
		this.deaths = deaths;
		this.kills = kills;
		this.defeats = defeats;
		this.played = played;
		this.rank = rank;
		this.playTime = playTime;
	}
}
