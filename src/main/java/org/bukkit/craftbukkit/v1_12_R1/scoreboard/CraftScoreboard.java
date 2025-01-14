package org.bukkit.craftbukkit.v1_12_R1.scoreboard;

import com.google.common.base.Function;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Iterables;
import net.minecraft.scoreboard.ScoreObjective;
import net.minecraft.scoreboard.ScorePlayerTeam;
import net.minecraft.scoreboard.Scoreboard;
import org.apache.commons.lang3.Validate;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Score;
import org.bukkit.scoreboard.Team;

import java.util.Collection;

public final class CraftScoreboard implements org.bukkit.scoreboard.Scoreboard {
    final Scoreboard board;

    CraftScoreboard(Scoreboard board) {
        this.board = board;
    }

    @Override
    public CraftObjective registerNewObjective(String name, String criteria) throws IllegalArgumentException {
        Validate.notNull(name, "Objective name cannot be null");
        Validate.notNull(criteria, "Criteria cannot be null");
        Validate.isTrue(name.length() <= 16, "The name '" + name + "' is longer than the limit of 16 characters");
        Validate.isTrue(board.getObjective(name) == null, "An objective of name '" + name + "' already exists");

        CraftCriteria craftCriteria = CraftCriteria.getFromBukkit(criteria);
        ScoreObjective objective = board.addScoreObjective(name, craftCriteria.criteria);
        return new CraftObjective(this, objective);
    }

    @Override
    public Objective getObjective(String name) throws IllegalArgumentException {
        Validate.notNull(name, "Name cannot be null");
        ScoreObjective nms = board.getObjective(name);
        return nms == null ? null : new CraftObjective(this, nms);
    }

    @Override
    public ImmutableSet<Objective> getObjectivesByCriteria(String criteria) throws IllegalArgumentException {
        Validate.notNull(criteria, "Criteria cannot be null");

        ImmutableSet.Builder<Objective> objectives = ImmutableSet.builder();
        for (ScoreObjective netObjective : this.board.getScoreObjectives()) {
            CraftObjective objective = new CraftObjective(this, netObjective);
            if (objective.getCriteria().equals(criteria)) {
                objectives.add(objective);
            }
        }
        return objectives.build();
    }

    @Override
    public ImmutableSet<Objective> getObjectives() {
        return ImmutableSet.copyOf(Iterables.transform((Collection<ScoreObjective>) this.board.getScoreObjectives(), new Function<ScoreObjective, Objective>() {

            @Override
            public Objective apply(ScoreObjective input) {
                return new CraftObjective(CraftScoreboard.this, input);
            }
        }));
    }

    @Override
    public Objective getObjective(DisplaySlot slot) throws IllegalArgumentException {
        Validate.notNull(slot, "Display slot cannot be null");
        ScoreObjective objective = board.getObjectiveInDisplaySlot(CraftScoreboardTranslations.fromBukkitSlot(slot));
        if (objective == null) {
            return null;
        }
        return new CraftObjective(this, objective);
    }

    @Override
    public ImmutableSet<Score> getScores(OfflinePlayer player) throws IllegalArgumentException {
        Validate.notNull(player, "OfflinePlayer cannot be null");

        return getScores(player.getName());
    }

    @Override
    public ImmutableSet<Score> getScores(String entry) throws IllegalArgumentException {
        Validate.notNull(entry, "Entry cannot be null");

        ImmutableSet.Builder<Score> scores = ImmutableSet.builder();
        for (ScoreObjective objective : this.board.getScoreObjectives()) {
            scores.add(new CraftScore(new CraftObjective(this, objective), entry));
        }
        return scores.build();
    }

    @Override
    public void resetScores(OfflinePlayer player) throws IllegalArgumentException {
        Validate.notNull(player, "OfflinePlayer cannot be null");

        resetScores(player.getName());
    }

    @Override
    public void resetScores(String entry) throws IllegalArgumentException {
        Validate.notNull(entry, "Entry cannot be null");

        for (ScoreObjective objective : this.board.getScoreObjectives()) {
            board.removeObjectiveFromEntity(entry, objective);
        }
    }

    @Override
    public Team getPlayerTeam(OfflinePlayer player) throws IllegalArgumentException {
        Validate.notNull(player, "OfflinePlayer cannot be null");

        ScorePlayerTeam team = board.getPlayersTeam(player.getName());
        return team == null ? null : new CraftTeam(this, team);
    }

    @Override
    public Team getEntryTeam(String entry) throws IllegalArgumentException {
        Validate.notNull(entry, "Entry cannot be null");

        ScorePlayerTeam team = board.getPlayersTeam(entry);
        return team == null ? null : new CraftTeam(this, team);
    }

    @Override
    public Team getTeam(String teamName) throws IllegalArgumentException {
        Validate.notNull(teamName, "Team name cannot be null");

        ScorePlayerTeam team = board.getTeam(teamName);
        return team == null ? null : new CraftTeam(this, team);
    }

    @Override
    public ImmutableSet<Team> getTeams() {
        return ImmutableSet.copyOf(Iterables.transform((Collection<ScorePlayerTeam>) this.board.getTeams(), new Function<ScorePlayerTeam, Team>() {

            @Override
            public Team apply(ScorePlayerTeam input) {
                return new CraftTeam(CraftScoreboard.this, input);
            }
        }));
    }

    @Override
    public Team registerNewTeam(String name) throws IllegalArgumentException {
        Validate.notNull(name, "Team name cannot be null");
        Validate.isTrue(name.length() <= 16, "Team name '" + name + "' is longer than the limit of 16 characters");
        Validate.isTrue(board.getTeam(name) == null, "Team name '" + name + "' is already in use");

        return new CraftTeam(this, board.createTeam(name));
    }

    @Override
    public ImmutableSet<OfflinePlayer> getPlayers() {
        ImmutableSet.Builder<OfflinePlayer> players = ImmutableSet.builder();
        for (Object playerName : board.getObjectiveNames()) {
            players.add(Bukkit.getOfflinePlayer(playerName.toString()));
        }
        return players.build();
    }

    @Override
    public ImmutableSet<String> getEntries() {
        ImmutableSet.Builder<String> entries = ImmutableSet.builder();
        for (Object entry : board.getObjectiveNames()) {
            entries.add(entry.toString());
        }
        return entries.build();
    }

    @Override
    public void clearSlot(DisplaySlot slot) throws IllegalArgumentException {
        Validate.notNull(slot, "Slot cannot be null");
        board.setObjectiveInDisplaySlot(CraftScoreboardTranslations.fromBukkitSlot(slot), null);
    }

    // CraftBukkit method
    public Scoreboard getHandle() {
        return board;
    }
}
