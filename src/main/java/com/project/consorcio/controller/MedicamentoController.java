package com.project.consorcio.controller;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.project.consorcio.entity.Medicamento;
import com.project.consorcio.entity.TipoMedicamento;
import com.project.consorcio.services.MedicamentoServices;
import com.project.consorcio.services.TipoMedicamentoServices;
//anotacion que indica que la clase es un controlador, por lo tanto 
//permite recibir peticion de los clientes y envia respuesta
@Controller
//permite crear una dureccion url o ruta para macceder al controlador
@RequestMapping("/medicamento")
public class MedicamentoController {
@Autowired
private MedicamentoServices servicioMed;

@Autowired 
private TipoMedicamentoServices servicioTipo;

@RequestMapping("/lista")
//model es una interfaz que permite crear atributos que luego seran 
//enviados a la pagina
public String index(Model model) {
	//atributos
	model.addAttribute("medicamentos",servicioMed.listarTodos());
	model.addAttribute("tipos",servicioTipo.listarTodos());
return "medicamento";
}

//RequestParam permite recuperar valores que se encuentran en los 
//controles del formulario(cajas,checkbox,radio,etc)
@RequestMapping("/grabar")
public String grabar(
		@RequestParam("codigo")Integer cod,
		@RequestParam("nombre")String nom,
		@RequestParam("descripcion")String des,
		@RequestParam("stock")int stock,
		@RequestParam("precio")double pre,
		@RequestParam("fecha")String fec,
		@RequestParam("tipo")int codTipo,
		RedirectAttributes redirect) {
	try {
		//crear objeto de la entidad medicamento
		Medicamento med=new Medicamento();
		//setear atributos del objeto med con los parametros
		med.setNombre(nom);
		med.setDescripcion(des);
		med.setStock(stock);
		med.setPrecio(pre);
		med.setFecha(LocalDate.parse(fec));
		//crear objeto de la entidad tipomedicamento
		TipoMedicamento tm=new TipoMedicamento();
		//setear atributo "codigo" del objeto "tm" con el parametro codTipo
		tm.setCodigo(codTipo);
		//invocar al metodo settipo y enviar el objeto "tm"
		med.setTipo(tm);
		//validar parametro "cod"
		if(cod==0) {
		//invocar metodo registrar
		servicioMed.registrar(med);
		//crear atributo de tipo flash
		redirect.addFlashAttribute("MENSAJE","MEDICAMENTO REGISTRADO");
		}
		else {
			//setear atributo codigo
			med.setCodigo(cod);
			servicioMed.actualizar(med);
			redirect.addFlashAttribute("MENSAJE","MEDICAMENTO ACTUALIZADO");
		}
	} catch (Exception e) {
		e.printStackTrace();
	}
	
	return "redirect:/medicamento/lista";
}
	//crear ruta o direccion ULR para buscar medicamento segun codigo
@RequestMapping("/buscar")
@ResponseBody
public Medicamento buscar(@RequestParam("codigo")Integer cod) {
	return servicioMed.buscarPorID(cod);
}

}
