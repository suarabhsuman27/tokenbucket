package com.api.ratelimiter.tokenbucket.entities;

import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;


@Entity
@Table(name = "tenant")
@Setter
@Getter
public class Tenant {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private UUID id;
	
	@Column(name="name", unique = true)
	private String businessName;
}
