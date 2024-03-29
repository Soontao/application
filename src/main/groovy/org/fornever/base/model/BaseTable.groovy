package org.fornever.base.model

import static javax.persistence.GenerationType.AUTO

import javax.persistence.Column
import javax.persistence.EntityListeners
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.persistence.MappedSuperclass
import javax.persistence.PostLoad
import javax.persistence.PrePersist
import javax.persistence.PreRemove
import javax.persistence.PreUpdate
import javax.persistence.Transient

import org.springframework.data.annotation.CreatedBy
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedBy
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.jpa.domain.support.AuditingEntityListener

import com.fasterxml.jackson.annotation.JsonIgnore

import groovy.transform.AutoClone
import groovy.transform.EqualsAndHashCode

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@AutoClone
@EqualsAndHashCode
class BaseTable {

	@Id
	@GeneratedValue(strategy=AUTO)
	Long id;

	@CreatedBy
	@Column(updatable=false)
	String createdBy = "SYSTEM";

	@CreatedDate
	@Column(updatable=false)
	Date createdDate;

	@LastModifiedBy
	String lastModifiedBy = "SYSTEM";

	@LastModifiedDate
	Date lastModifiedDate;

	/**
	 * The snapshot data from DB,
	 * I think you will need this
	 */
	@Transient
	@JsonIgnore
	Object snapshot;

	@PostLoad
	private void _afterLoad() {

		this.snapshot = this.clone()
	}

	@PrePersist
	@PreUpdate
	void _beforeSave() {}

	@PreRemove
	void _beforeDelete() {}
}
