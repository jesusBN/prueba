package com.project.consorcio.controller;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.project.consorcio.entity.Distrito;
import com.project.consorcio.entity.Especialidad;
import com.project.consorcio.entity.Medico;
import com.project.consorcio.entity.Sede;
import com.project.consorcio.services.DistritoServices;
import com.project.consorcio.services.EspecialidadServices;
import com.project.consorcio.services.MedicoServices;
import com.project.consorcio.services.SedeServices;

//anotacion que indica que la clase es un controlador, por lo tanto 
//permite recibir peticion de los clientes y envia respuesta
@Controller
//permite crear una dureccion url o ruta para macceder al controlador
@RequestMapping("/medico")
public class MedicoController {
	@Autowired
	private MedicoServices servicioMedico;
	
	@Autowired 
	private SedeServices servicioSede;
	
	@Autowired 
	private DistritoServices servicioDistrito;
	
	@Autowired 
	private EspecialidadServices servicioEspecialidad;
	
	@RequestMapping("/lista")
	//model es una interfaz que permite crear atributos que luego seran 
	//enviados a la pagina
	public String index(Model model) {
		//atributos
		model.addAttribute("medicos",servicioMedico.listarTodos());
		model.addAttribute("sede",servicioSede.listarTodos());
		model.addAttribute("distrito",servicioDistrito.listarTodos());
		model.addAttribute("especialidad",servicioEspecialidad.listarTodos());
	return "medico";
	}
	//RequestParam permite recuperar valores que se encuentran en los 
	//controles del formulario(cajas,checkbox,radio,etc)
	@RequestMapping("/grabar")
	public String grabar(
			@RequestParam("codigo")Integer cod,
			@RequestParam("nombre")String nom,
			@RequestParam("apellido")String ape,
			@RequestParam("fecha")String fec,
			@RequestParam("sexo")String sex,
			@RequestParam("sueldo")int sue,
			@RequestParam("especialidad")int codespe,
			@RequestParam("distrito")int coddis,
			@RequestParam("sede")int codsed,
			RedirectAttributes redirect) {
		try {
			//crear objeto de la entidad medicamento
			Medico med=new Medico();
			//setear atributos del objeto med con los parametros
			med.setNombre(nom);
			med.setApellido(ape);
			med.setFecha(LocalDate.parse(fec));
		    med.setSexo(sex);
			med.setSueldo(sue);
			
			//crear objeto de la entidad tipomedicamento
			Especialidad tm=new Especialidad();
			Distrito dm=new Distrito();
			Sede sm=new Sede();
			
			//setear atributo "codigo" del objeto "tm" con el parametro codTipo
			tm.setCodigo(codespe);
			dm.setCodigo(coddis);
			sm.setCodigo(codsed);
			//invocar al metodo settipo y enviar el objeto "tm"
			med.setEspecialidad(tm);
			med.setDistrito(dm);
			med.setSede(sm);
			
			//validar parametro "cod"
			if(cod==0) {
			//invocar metodo registrar
			servicioMedico.registrar(med);
			//crear atributo de tipo flash
			redirect.addFlashAttribute("MENSAJE","MEDICO REGISTRADO");
			}
			else {
				//setear atributo codigo
				med.setCodigo(cod);
				servicioMedico.actualizar(med);
				redirect.addFlashAttribute("MENSAJE","MEDICO ACTUALIZADO");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return "redirect:/medico/lista";
	}
		//crear ruta o direccion ULR para buscar medicamento segun codigo
	@RequestMapping("/buscar")
	@ResponseBody
	public Medico buscar(@RequestParam("codigo")Integer cod) {
		return servicioMedico.buscarPorID(cod);
	}
	
	
}
