package br.com.squamata.gastos.controller;

import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value="/gastos")
public class GastosController extends AbstractController {

	private static final Logger logger = LoggerFactory.getLogger(GastosController.class);
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView index(Locale locale, Model model) {
		return new ModelAndView("/restrito/index");
	}
	
}
