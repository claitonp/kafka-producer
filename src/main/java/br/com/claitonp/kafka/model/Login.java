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
public class Login implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private String login;
	private LocalDateTime dh;

}