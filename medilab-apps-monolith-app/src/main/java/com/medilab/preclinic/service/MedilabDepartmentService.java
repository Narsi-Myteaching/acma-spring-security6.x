/**
 * 
 */
package com.medilab.preclinic.service;

import java.util.List;

import com.medilab.preclinic.bean.DepartmentBean;
import org.springframework.security.access.prepost.PreAuthorize;

/**
 * @author nsanda
 *
 */
public interface MedilabDepartmentService {

	@PreAuthorize("hasAuthority('DEPT_CREATE')")
	public DepartmentBean save(DepartmentBean deptBean);

	@PreAuthorize("hasAuthority('DEPT_VIEW')")
	public List<DepartmentBean> findAll();

	@PreAuthorize("hasAuthority('DEPT_VIEW')")
	public DepartmentBean findById(int id);

	@PreAuthorize("hasAuthority('DEPT_VIEW')")
	public DepartmentBean findByName(String name);

	@PreAuthorize("hasAuthority('DEPT_DELETE')")
	public List<DepartmentBean> delete(int id);

	@PreAuthorize("hasAuthority('DEPT_DELETE')")
	public List<DepartmentBean> delete(DepartmentBean deptBean);

	@PreAuthorize("hasAuthority('DEPT_DELETE')")
	public DepartmentBean update(DepartmentBean deptBean);
	
}
