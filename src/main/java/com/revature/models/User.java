package com.revature.models;

import java.util.Set;
import java.util.TreeSet;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OrderColumn;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import lombok.AllArgsConstructor;
import lombok.Data;

@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
@Entity
@Table(name="users")
@Data
@AllArgsConstructor
public class User {

	public static final int MINDECKSIZE = 5;
	public static final int MAXDECKSIZE = 20;
	
	@Id
	@Column(name = "user_id", nullable=false, unique=true, updatable=false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonView({ JsonViewProfiles.User.class })
	private int id;
	
	@Length(min=1)
	private String firstName;
	
	@Length(min=1)
	private String lastName;
	
	@Length(min=3)
	@Pattern(regexp="[a-zA-Z0-9]*")
	@Column(unique=true)
	private String username;
	
	@Length(min=3)
	@NotBlank
	private String password;
	
	@Email
	@Column(unique=true)
	private String email;
	
	@ElementCollection
	@CollectionTable(name="card_collections", joinColumns=@JoinColumn(name="user_id"))
	@Column(name="card_collection")
	private Set<Integer> cards;
	
	@ElementCollection
	@OrderColumn(name="hero_deck_pos")
	@Column(name="hero_deck")
	private int[] heroDeck;
	
	@ElementCollection
	@Column(name="villian_deck")
	@OrderColumn(name="villian_deck_pos")
	private int[] villianDeck;
	
	public User() {
		this.id = -1;

		this.cards = new TreeSet<Integer>();
		this.fillStartingDecks();
	}
	
	public User(final String firstName, final String lastName, final String username, final String password, final String email) {
		this.id = -1;
		this.firstName = firstName;
		this.lastName = lastName;
		this.username = username;
		this.password = password;
		this.email = email;

		this.cards = new TreeSet<Integer>();
		this.fillStartingDecks();
	}
	
	private void fillStartingDecks() {
		//TODO: assign to default card inv and decks
		this.cards.add(-1);
		this.cards.add(-2);
		this.cards.add(70);
		this.heroDeck = new int[] { 
				70, //batman
				-1,
				-1,
				-1,
				-1
			};
		this.villianDeck = new int[] { 
				70, //batman
				-2,
				-2,
				-2,
				-2
			};
	}
	
}
