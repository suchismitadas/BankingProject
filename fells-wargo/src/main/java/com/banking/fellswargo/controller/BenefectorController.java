package com.banking.fellswargo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.banking.fellswargo.service.BenefectorService;

@RestController
public class BenefectorController {
	@Autowired
	BenefectorService benefectorService;
	
//	@GetMapping("/benefector")
//	public  List<Benefector> getAllBenefector(){
//		return benefectorService.getAllBenefactor();
//	}
//	

}
