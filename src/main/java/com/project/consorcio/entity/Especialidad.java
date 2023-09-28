package com.project.consorcio.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;


	@Entity
	@Table(name="tb_especialidad")
	
	public class Especialidad {
	@Id
	@Column(name="cod_espe")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer codigo;
	@Column(name="nom_espe")
	private String nombre;
	
	//anotacion para anular json
	@JsonIgnore
	//relaciòn uno a muchos
		@OneToMany(mappedBy = "especialidad")//ASOC
		private List<Medico> listaespecialidad;
		public Integer getCodigo() {
			return codigo;
		}
		public void setCodigo(Integer codigo) {
			this.codigo = codigo;
		}
		public String getNombre() {
			return nombre;
		}
		public void setNombre(String nombre) {
			this.nombre = nombre;
		}
		public List<Medico> getListaespecialidad() {
			return listaespecialidad;
		}
		public void setListaespecialidad(List<Medico> listaespecialidad) {
			this.listaespecialidad = listaespecialidad;
		}
		
		
}
