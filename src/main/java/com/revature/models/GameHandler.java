package com.revature.models;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

import com.revature.models.json_mapping.UserTurn;

public class GameHandler implements Runnable {
	
	//singleton design because autowiring has failed me
	private static final GameHandler QUEUEHANDLER = new GameHandler();
	public static GameHandler getInstance() {
		return QUEUEHANDLER;
	}
	
	private static final int TIMEOUT = 15000;
	
	private boolean active;

	private final Map<Integer,User> users;
	private final Map<Integer,Long> times;
	private final Queue<Integer> gameQueue;

	private final List<GameState> games;
	private final Map<Integer,GameState> userGame;
	
	private GameHandler() {
		this.active = true;

		this.users = new HashMap<Integer,User>();
		this.gameQueue = new LinkedList<>();
		this.times = new HashMap<>();
		
		this.games = new LinkedList<>();
		this.userGame = new HashMap<>();
	}
	
	/**
	 * Updates a user, refreshing their timeout period. If the user is new, adds them to the game
	 * queue.
	 * @param id of the user
	 * @return The game the player is in, if they are in one. Otherwise returns null (they are in the queue).
	 */
	public GameState updateUser(final User user) {
		if(!this.active)
			return null;
		
		int state = 0;
		if(this.times.containsKey(user.getId()))
			state = 1;
		if(this.gameQueue.contains(user.getId()))
			state = 2;
		if(this.userGame.containsKey(user.getId()))
			state = 3;

		this.users.put(user.getId(), user);
		this.times.put(user.getId(), System.currentTimeMillis());
		
		if(state == 0)
			this.gameQueue.add(user.getId());
		
		if(state == 3)
			return this.userGame.get(user.getId());
		else
			return null;
	}
	
	/**
	 * Sets the user's next move. Returns true if the move was set, otherwise
	 * returns false (already set move, not in a game, etc.)
	 * @param id User id to set the move
	 * @param moves List of objects to update
	 * @return if the move was set
	 */
	public String setUserMove(final User user, final UserTurn moves) {
		if(!this.active)
			return null;
		
		if(this.userGame.containsKey(user.getId())) {
			final GameState game = this.userGame.get(user.getId());
			return game.setTurn(user.getId(), moves);
		}
		return "not in a game";
	}
	
	/**
	 * Turns off the control loop and allows the thread to terminate.
	 */
	public void shutdown() {
		this.active = false;
	}
	
	public synchronized void leaveGame(final int id) {
		if(this.times.containsKey(id))
			this.times.remove(id);
		if(this.gameQueue.contains(id))
			this.gameQueue.remove(id);
		if(this.userGame.containsKey(id)) {
			this.userGame.get(id).setLoser(id);
			this.userGame.remove(id);
		}
	}

	@Override
	public void run() {
		
		while(this.active) {
			
			final long time = System.currentTimeMillis();
			
			//remove users that have timed out
			final List<Integer> leaveGame = new LinkedList<>();
			for(final Map.Entry<Integer, Long> set: this.times.entrySet()) {
				final int user = set.getKey();
				final long lastResponse = set.getValue();
				
				final GameState game = this.userGame.get(user);
				
				if(time - lastResponse > TIMEOUT || (game != null && game.hasWinner()))
					leaveGame.add(user);
			}
			for(final int user: leaveGame)
				leaveGame(user);
			
			//start a game if 2 people are queued
			while(this.gameQueue.size() >= 2) {
				final int heros = this.gameQueue.poll();
				if(heros != this.gameQueue.peek()) {
						
					final int villians = this.gameQueue.poll();
					if(this.users.containsKey(heros) && this.users.containsKey(villians)) {
						final GameState game = new GameState(this.users.get(heros), this.users.get(villians));
						this.games.add(game);
						this.userGame.put(heros, game);
						this.userGame.put(villians, game);
					} else {
						leaveGame(heros);
						leaveGame(villians);
					}
				}
			}
			
			/*
			//start a game if 1 person is queued (for testing)
			while(this.gameQueue.peek() != null) {
				final int heros = this.gameQueue.poll();
				
				final User villians = new User();
				final GameState game = new GameState(this.users.get(heros), villians);
				this.games.add(game);
				this.userGame.put(heros, game);
			}
			*/
			
			//update games
			final List<GameState> remove = new LinkedList<GameState>();
			for(final GameState game: this.games) {
				if(game.hasWinner()) {
					remove.add(game);
				}
				game.update();
			}
			synchronized(this) {
				this.games.removeAll(remove);
			}
			
			//delay for up to 500 ms
			final long processTime = System.currentTimeMillis() - time;
			final long delay = 500 - processTime;
			if(delay > 0) {
				try {
					Thread.sleep(delay);
				} catch (final InterruptedException e) {}
			}
			
		}
	}
	
}
