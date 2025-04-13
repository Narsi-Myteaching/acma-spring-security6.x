/**
 * 
 */
package com.medilab.preclinic.service;

import java.util.List;

import com.medilab.preclinic.bean.DoctorBean;
import org.springframework.security.access.prepost.PreAuthorize;

/**
 * @author nsanda
 *
 */
public interface MedilabDoctorService {

	@PreAuthorize("hasAuthority('CREATE')")
	public DoctorBean save(DoctorBean deptBean);

	@PreAuthorize("hasAuthority('VIEW')")
	public List<DoctorBean> findAll();

	@PreAuthorize("hasAuthority('VIEW')")
	public DoctorBean findById(int id);

	@PreAuthorize("hasAuthority('VIEW')")
	public DoctorBean findByName(String name);

	@PreAuthorize("hasAuthority('DELETE')")
	public List<DoctorBean> delete(int id);

	@PreAuthorize("hasAuthority('DELETE')")
	public List<DoctorBean> delete(DoctorBean deptBean);

	@PreAuthorize("hasAuthority('UPDATE')")
	public DoctorBean update(DoctorBean deptBean);
	
}
