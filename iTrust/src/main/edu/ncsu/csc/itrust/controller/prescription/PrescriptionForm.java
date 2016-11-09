package edu.ncsu.csc.itrust.controller.prescription;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import edu.ncsu.csc.itrust.model.old.beans.PatientBean;
import edu.ncsu.csc.itrust.model.prescription.Prescription;
import edu.ncsu.csc.itrust.webutils.SessionUtils;

@ManagedBean(name = "prescription_form")
@ViewScoped
public class PrescriptionForm {

	private String viewingReportForPID;
	private Prescription prescription;
	private PrescriptionController prescriptionController;
	private SessionUtils sessionUtils;

	public PrescriptionForm() {
		this(null, SessionUtils.getInstance());
	}

	public PrescriptionForm(PrescriptionController controller, SessionUtils sessionUtils) {
		// TODO
	}

	public List<Prescription> getPrescriptionsForCurrentPatient() {
		return prescriptionController.getPrescriptionsForCurrentPatient();
	}

	public List<Prescription> getPrescriptionsByPatientID(String patientID) {
		return prescriptionController.getPrescriptionsByPatientID(patientID);
	}

	public List<PatientBean> getListOfRepresentees() {
		return prescriptionController.getListOfRepresentees();
	}

	public String getViewingReportForPID() {
		return viewingReportForPID;
	}

	public void setViewingReportForPID(String viewingReportForPID) {
		this.viewingReportForPID = viewingReportForPID;
	}

	public Prescription getPrescription() {
		return prescription;
	}

	public void setPrescription(Prescription prescription) {
		this.prescription = prescription;
	}

}