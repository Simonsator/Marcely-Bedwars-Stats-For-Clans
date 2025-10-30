package de.simonsator.partyandfriends.clans.stats.marcelybedwars;

import de.simonsator.partyandfriends.api.pafplayers.OnlinePAFPlayer;
import de.simonsator.partyandfriends.api.pafplayers.PAFPlayer;
import de.simonsator.partyandfriends.clan.api.Clan;
import de.simonsator.partyandfriends.clan.api.ClanStat;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;

public class MBStats implements ClanStat {
	private final String NAME;
	protected final MBStatsConnection CONNECTION;
	private final Matcher KILLS_MESSAGE;
	private final Matcher WINS_MESSAGE;
	private final Matcher DEFEATS_MESSAGE;
	private final Matcher DEATHS_MESSAGE;
	private final Matcher PLAYED_MESSAGE;
	private final Matcher DESTROYED_MESSAGE;
	private final Matcher RANK_MESSAGE;

	public MBStats(String pName, MBStatsConnection pCon, Matcher pKillsMessage, Matcher pWinsMessage, Matcher pDefeatsMessage, Matcher pDeathsMessage, Matcher pDestroyedMessage, Matcher pPlayedMessage, Matcher pRankMessage) {
		NAME = pName;
		CONNECTION = pCon;
		KILLS_MESSAGE = pKillsMessage;
		WINS_MESSAGE = pWinsMessage;
		DEFEATS_MESSAGE = pDefeatsMessage;
		DEATHS_MESSAGE = pDeathsMessage;
		DESTROYED_MESSAGE = pDestroyedMessage;
		PLAYED_MESSAGE = pPlayedMessage;
		RANK_MESSAGE = pRankMessage;
	}

	public void stats(OnlinePAFPlayer pSender, Clan pClan) {
		List<PAFPlayer> players = pClan.getAllPlayers();
		List<PlayerData> playerData = new ArrayList<PlayerData>();
		for (PAFPlayer player : players) {
			PlayerData data = CONNECTION.getPlayerData(player.getUniqueId());
			if (data != null)
				playerData.add(data);
		}
		int wins = 0;
		int deaths = 0;
		int defeats = 0;
		int kills = 0;
		int destroyedBeds = 0;
		int played = 0;
		int rank = 0;
		for (PlayerData data : playerData) {
			wins += data.wins;
			deaths += data.deaths;
			kills += data.kills;
			destroyedBeds += data.destroyedBeds;
			defeats += data.defeats;
			played += data.played;
			rank += data.rank;
		}
		pSender.sendMessage(KILLS_MESSAGE.replaceFirst(kills + ""));
		pSender.sendMessage(DEATHS_MESSAGE.replaceFirst(deaths + ""));
		pSender.sendMessage(DESTROYED_MESSAGE.replaceFirst(destroyedBeds + ""));
		pSender.sendMessage(WINS_MESSAGE.replaceFirst(wins + ""));
		pSender.sendMessage(DEFEATS_MESSAGE.replaceFirst(defeats + ""));
		pSender.sendMessage(PLAYED_MESSAGE.replaceFirst(played + ""));
		pSender.sendMessage(RANK_MESSAGE.replaceFirst(rank / playerData.size() + ""));
	}

	public String getName() {
		return NAME;
	}
}
