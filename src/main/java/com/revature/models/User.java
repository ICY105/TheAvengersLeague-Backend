package com.revature.models;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.TreeSet;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;

@Entity
@Table(name="users")
public class User {
	
	@Id
	@Column(name = "user_id",nullable=false,unique=true,updatable=false)
	private int id;
	
	@Length(min=1)
	private String firstName;
	
	@Length(min=1)
	private String lastName;
	
	@Length(min=5)
	@Pattern(regexp="[a-zA-Z0-9]*")
	@Column(unique=true)
	private String username;
	
	@Length(min=2)
	@NotEmpty
	private String password;
	
	@Email
	@Column(unique=true)
	private final String email;
	
	@ElementCollection
	@CollectionTable(name="card_collections", joinColumns=@JoinColumn(name="user_id"))
	@Column(name="card_collection")
	Set<Integer> cards;
	
	@ElementCollection
	@CollectionTable(name="hero_decks", joinColumns=@JoinColumn(name="user_id"))
	@Column(name="hero_deck")
	List<Integer> heroDeck;
	
	@ElementCollection
	@CollectionTable(name="villian_decks", joinColumns=@JoinColumn(name="user_id"))
	@Column(name="villian_deck")
	List<Integer> villianDeck;
	
	public User() {
		this.id = -1;
		this.firstName = "";
		this.lastName = "";
		this.username = "";
		this.password = "";
		this.email = "";
		//TODO: assign to default card inv and decks
		this.cards = new TreeSet<Integer>();
		this.heroDeck = new LinkedList<>();
		this.villianDeck = new LinkedList<>();
	}
	
	public User(final String firstName, final String lastName, final String username, final String password, final String email) {
		this.id = -1;
		this.firstName = firstName;
		this.lastName = lastName;
		this.username = username;
		this.password = password;
		this.email = email;
		//TODO: assign to default card inv and decks
		this.cards = new TreeSet<Integer>();
		this.heroDeck = new LinkedList<>();
		this.villianDeck = new LinkedList<>();
	}

	public User(final int id, final String firstName, final String lastName, final String username,
			final String password, final Set<Integer> cards, final LinkedList<Integer> heroDeck,
			final LinkedList<Integer> villianDeck, final String email)
	{
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.username = username;
		this.password = password;
		this.email = email;
		this.cards = cards;
		this.heroDeck = heroDeck;
		this.villianDeck = villianDeck;
	}
	
	/*
	 * Getters and Setters
	 */

	public int getId() {
		return this.id;
	}

	public void setId(final int id) {
		this.id = id;
	}
	
	public String getFirstName() {
		return this.firstName;
	}

	public void setFirstName(final String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return this.lastName;
	}

	public void setLastName(final String lastName) {
		this.lastName = lastName;
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(final String username) {
		this.username = username;
	}

	public boolean checkPassword(final String password) {
		return this.password.equals(password);
	}

	public void setPassword(final String password) {
		this.password = password;
	}

	public Set<Integer> getCards() {
		return this.cards;
	}

	public void setCards(final Set<Integer> cards) {
		this.cards = cards;
	}

	public List<Integer> getHeroDeck() {
		return this.heroDeck;
	}

	public void setHeroDeck(final List<Integer> heroDeck) {
		this.heroDeck = heroDeck;
	}

	public List<Integer> getVillianDeck() {
		return this.villianDeck;
	}

	public void setVillianDeck(final List<Integer> villianDeck) {
		this.villianDeck = villianDeck;
	}
	
	@Override
	public String toString() {
		return "User [id=" + this.id + ", firstName=" + this.firstName + ", lastName=" + this.lastName + ", username=" + this.username
				+ ", email=" + this.email + ", cards=" + this.cards + ", heroDeck=" + this.heroDeck + ", villianDeck=" + this.villianDeck
				+ "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(this.cards, this.firstName, this.heroDeck, this.id, this.lastName, this.username, this.villianDeck);
	}

	@Override
	public boolean equals(final Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		final User other = (User) obj;
		return Objects.equals(this.cards, other.cards) && Objects.equals(this.firstName, other.firstName)
				&& Objects.equals(this.heroDeck, other.heroDeck) && this.id == other.id
				&& Objects.equals(this.lastName, other.lastName) && Objects.equals(this.username, other.username)
				&& Objects.equals(this.villianDeck, other.villianDeck);
	}
	
}
