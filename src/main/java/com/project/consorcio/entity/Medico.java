package com.project.consorcio.entity;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_medico")
public class Medico {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "cod_med")
	private Integer codigo;
	
	@Column(name = "nom_med")
	private String nombre;
	
	@Column(name = "ape_med")
	private String apellido;
	
	@Column(name = "fec_nac_med")
	private LocalDate fecha;
	
	@Column(name = "sexo_med")
	private String estado;
	
	@Column(name = "est_civ_med")
	private String sexo;
	
	@Column(name = "dni_med")
	private String dni;
	
	@Column(name = "sue_med")
	private int sueldo;
	
	@Column(name = "dir_emp")
	private String direccion;
	
	//relación muchos a uno
		@ManyToOne
		@JoinColumn(name="cod_dis")
		private Distrito distrito;//ASOC
		
		//relación muchos a uno
				@ManyToOne
				@JoinColumn(name="cod_espe")
				private Especialidad especialidad;//ASOC
				
				//relación muchos a uno
				@ManyToOne
				@JoinColumn(name="cod_sede")
				private Sede sede;//ASOC
				

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

				public String getApellido() {
					return apellido;
				}

				public void setApellido(String apellido) {
					this.apellido = apellido;
				}

				public LocalDate getFecha() {
					return fecha;
				}

				public void setFecha(LocalDate fecha) {
					this.fecha = fecha;
				}

				public String getEstado() {
					return estado;
				}

				public void setEstado(String estado) {
					this.estado = estado;
				}

				public String getSexo() {
					return sexo;
				}

				public void setSexo(String sexo) {
					this.sexo = sexo;
				}

				public String getDni() {
					return dni;
				}

				public void setDni(String dni) {
					this.dni = dni;
				}

				public int getSueldo() {
					return sueldo;
				}

				public void setSueldo(int sueldo) {
					this.sueldo = sueldo;
				}
				
				public String getDireccion() {
					return direccion;
				}

				public void setDireccion(String direccion) {
					this.direccion = direccion;
				}

				public Distrito getDistrito() {
					return distrito;
				}

				public void setDistrito(Distrito distrito) {
					this.distrito = distrito;
				}

				public Especialidad getEspecialidad() {
					return especialidad;
				}

				public void setEspecialidad(Especialidad especialidad) {
					this.especialidad = especialidad;
				}

				public Sede getSede() {
					return sede;
				}

				public void setSede(Sede sede) {
					this.sede = sede;
				}
				
				
	
}
