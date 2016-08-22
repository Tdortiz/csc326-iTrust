package edu.ncsu.csc.itrust.controller.user.patient;

import java.io.IOException;
import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

import edu.ncsu.csc.itrust.controller.user.UserControllerBean;
import edu.ncsu.csc.itrust.exception.DBException;
import edu.ncsu.csc.itrust.model.DataBean;
import edu.ncsu.csc.itrust.model.ValidationFormat;
import edu.ncsu.csc.itrust.model.user.User;
import edu.ncsu.csc.itrust.model.user.patient.Patient;

@ManagedBean(name="patient")
public class PatientControllerBean extends UserControllerBean implements Serializable{
	private DataBean<Patient> patientData;
	public PatientControllerBean() throws DBException {
		
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = -164769440967020045L;
	

	
	public boolean doesPatientExistWithID(String mid) throws DBException{
		User user = null;
		if( mid == null) return false;
		if(!(ValidationFormat.NPMID.getRegex().matcher(mid).matches())) return false;
		long id = -1;
		try{
			id = Long.parseLong(mid);
		}
		catch(NumberFormatException ne){
			return false;
		}
		user = patientData.getByID(id);
		if(!(user == null)){
				return true;
		}
		else{
			return false;
		}

				
	}


}
