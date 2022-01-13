package br.com.claitonp.kafka.model;

import java.io.Serializable;
import java.time.LocalDateTime;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@Getter
@Setter
@NoArgsConstructor
@ToString
@EqualsAndHashCode(of= {"login"})
public class User implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private String login;
	private String nome;
	
	private LocalDateTime dh;

}
