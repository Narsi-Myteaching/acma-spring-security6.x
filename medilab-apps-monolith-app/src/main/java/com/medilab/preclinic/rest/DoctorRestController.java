package com.medilab.preclinic.rest;

import com.medilab.preclinic.bean.DepartmentBean;
import com.medilab.preclinic.bean.DoctorBean;
import com.medilab.preclinic.service.MedilabDepartmentService;
import com.medilab.preclinic.service.MedilabDoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/secured")
public class DoctorRestController {

	@Autowired
	private MedilabDoctorService doctService;
	
	@Autowired
	private MedilabDepartmentService deptService;
	

	@PostMapping(value = {"/doctors"})
	public List<DoctorBean> addDoctor(@RequestBody DoctorBean doctBean) {
		System.out.println("doctBean data is:\t"+doctBean.toString());
		doctBean = doctService.save(doctBean);
		List<DoctorBean> doctBeanList = doctService.findAll();
		return  doctBeanList;
	}

	@GetMapping(value = {"/doctors"})
	public List<DoctorBean> getAllDoctor() {
		List<DoctorBean> doctBeanList = doctService.findAll();
		return  doctBeanList;
	}
	@DeleteMapping(value={"/delete/{doctId}"})
	public List<DoctorBean> deleteDoctor(@PathVariable("doctId") int doctId) {
		System.out.println("doctBean data is:\t"+doctId);
		List<DoctorBean> doctBeanList  = doctService.delete(doctId);
		return  doctBeanList;
	}
	
	@PutMapping(value = {"/updateDoctor"})
	public List<DoctorBean> updateDoctor(@RequestBody DoctorBean doctBean) {
		System.out.println("doctBean data is:\t"+doctBean.toString());
		doctBean = doctService.update(doctBean);
		List<DoctorBean> doctBeanList = doctService.findAll();
		return doctBeanList;
	}
}
