package com.deviniti.multitenancy.separate.schema.entity.po;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import lombok.Data;

@Data
@MappedSuperclass
public class BaseEntity {

	@Id
	@GeneratedValue(generator="seq_gen")
	protected Long id;
	
}
