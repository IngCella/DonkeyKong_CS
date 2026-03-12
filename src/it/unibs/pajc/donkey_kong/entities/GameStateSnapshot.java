package it.unibs.pajc.donkey_kong.entities;

import java.io.Serializable;

public class GameStateSnapshot implements Serializable {

	private Player player;
	private Barrel[] barrels;
	private Collisions collisions;

}
