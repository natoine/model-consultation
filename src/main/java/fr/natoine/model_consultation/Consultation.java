/*
 * Copyright 2010 Antoine Seilles (Natoine)
 *   This file is part of model-consultation.

    model-annotation is free software: you can redistribute it and/or modify
    it under the terms of the GNU Lesser General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    model-annotation is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU Lesser General Public License for more details.

    You should have received a copy of the GNU Lesser General Public License
    along with model-annotation.  If not, see <http://www.gnu.org/licenses/>.

 */
package fr.natoine.model_consultation;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

import fr.natoine.model_resource.URI;
import fr.natoine.model_user.UserAccount;

@Entity
@Table(name = "CONSULTATION")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@XmlRootElement
public class Consultation 
{
	@Id @GeneratedValue(strategy = GenerationType.TABLE)
    @Column(name = "CONSULTATION_ID")
	private Long id;
	
	@ManyToOne(cascade = CascadeType.ALL, targetEntity = UserAccount.class)
	@JoinColumn(name = "USERACCOUNT_ID")
	private UserAccount person ;
	
	@Column(name = "START")
	private Date start ;
	
	@Column(name = "STOP")
	private Date stop ;
	
	@Column(name = "DURATION")
	private long duration ;
	
	@Column(name = "CONTEXTCREATION")
	private String context_creation ;
	
	@ManyToOne(cascade = CascadeType.ALL, targetEntity = URI.class)
	@JoinColumn(name = "URI_ID")
	private URI consulted ;
	
	public UserAccount getPerson() {
		return person;
	}
	public void setPerson(UserAccount person) {
		this.person = person;
	}
	public Date getStart() {
		return start;
	}
	public void setStart(Date start) {
		this.start = start;
	}
	public Date getStop() {
		return stop;
	}
	public void setStop(Date stop) {
		this.stop = stop;
	}
	public URI getConsulted() {
		return consulted;
	}
	public void setConsulted(URI consulted) {
		this.consulted = consulted;
	}
	
	public long computeDuration()
	{
		long duration = 0 ;
		duration = stop.getTime() - start.getTime() ;
		return duration ;
	}
	public void setDuration() {
		duration = computeDuration();
	}
	public long getDuration() {
		return duration;
	}
	public void setContextCreation(String context_creation) {
		this.context_creation = context_creation;
	}
	public String getContextCreation() {
		return context_creation;
	}
	
	public Long getId() {
		return id;
	}
}