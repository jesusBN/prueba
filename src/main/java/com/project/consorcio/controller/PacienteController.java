package com.project.consorcio.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.project.consorcio.entity.Categoria;
import com.project.consorcio.entity.Paciente;
import com.project.consorcio.services.CategoriaServices;
import com.project.consorcio.services.PacienteServices;

@Controller
@RequestMapping("/paciente")
public class PacienteController {
@Autowired
private CategoriaServices servicioCate;
@Autowired
private PacienteServices servicioPac;

@RequestMapping("/lista")
public String lista(Model model) {
	model.addAttribute("categorias",servicioCate.listarTodos());
	model.addAttribute("pacientes",servicioPac.listarTodos());
	return "paciente";
}
//RequestParam permite recuperar valores que se encuentran en los 
//controles del formulario(cajas,checkbox,radio,etc)
@RequestMapping("/grabar")
public String grabar(
		@RequestParam("codigo")Integer cod,
		@RequestParam("nombre")String nom,
		@RequestParam("apellido")String ape,
		@RequestParam("sexo")String sex,
		@RequestParam("categoria")int codcate,
		RedirectAttributes redirect) {
	
		//crear objeto de la entidad medicamento
		Paciente med=new Paciente();
		//setear atributos del objeto med con los parametros
		med.setNombres(nom);
		med.setApellidos(ape);
		med.setSexo(sex);
		
		//crear objeto de la entidad tipomedicamento
		Categoria tm=new Categoria();
		//setear atributo "codigo" del objeto "tm" con el parametro codTipo
		tm.setCodigo(codcate);
		//invocar al metodo settipo y enviar el objeto "tm"
		med.setCategoria(tm);
		//validar parametro "cod"
		if(cod==0) {
		//invocar metodo registrar
		servicioPac.registrar(med);
		//crear atributo de tipo flash
		redirect.addFlashAttribute("MENSAJE","MEDICAMENTO REGISTRADO");
		
		}
		return "redirect:/paciente/lista";
	}
}




	




	

