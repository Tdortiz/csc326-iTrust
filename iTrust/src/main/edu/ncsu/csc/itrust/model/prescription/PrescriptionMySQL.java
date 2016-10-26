package edu.ncsu.csc.itrust.model.prescription;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import edu.ncsu.csc.itrust.DBUtil;
import edu.ncsu.csc.itrust.exception.DBException;

public class PrescriptionMySQL {
    private DataSource ds;
    
    /**
     * Standard constructor for use in deployment
     * @throws DBException
     */
    public PrescriptionMySQL() throws DBException {
        try {
            Context ctx = new InitialContext();
            this.ds = ((DataSource) (((Context) ctx.lookup("java:comp/env"))).lookup("jdbc/itrust"));
        } catch (NamingException e) {
            throw new DBException(new SQLException("Context Lookup Naming Exception: " + e.getMessage()));
        }
    }
    
    /**
     * Constructor for testing purposes
     * @param ds The DataSource to use
     */
    public PrescriptionMySQL(DataSource ds) {
        this.ds = ds;
    }
    
    /**
     * Gets all prescriptions for the patient with the given MID with ending
     * dates equal to or after the given end date
     * 
     * @param mid The mid of the patient whose prescriptions we should get
     * @param endDate The end date to get prescriptions after
     * @return A List of prescriptions for the given patient that end on or
     *         after the given date.
     * @throws DBException 
     */
    public List<Prescription> getPrescriptionsForPatientEndingAfter(long mid, LocalDate endDate) throws DBException{
        Connection conn = null;
        PreparedStatement pstring = null;
        ResultSet results = null;
        
        try {
            conn = ds.getConnection();
            pstring = conn.prepareStatement("SELECT * FROM prescription WHERE patientMID=? AND endDate>=? ORDER BY endDate DESC");

            pstring.setLong(1, mid);
            pstring.setDate(2, Date.valueOf(endDate));

            results = pstring.executeQuery();
            return loadRecords(results);
        } catch (SQLException e){
            e.printStackTrace();
            throw new DBException(e);
        } finally {
            try {
                if (results != null){
                    results.close();
                }
            } catch (SQLException e) {
                throw new DBException(e);
            } finally {
                DBUtil.closeConnection(conn, pstring);
            }
        }
    }
    
    /**
     * A utility method that loads all Prescriptions from a ResultSet into a
     * List<Prescription> and returns it.
     * @param rs The ResultSet to load
     * @return A List of all Prescriptions in the ResultSet
     * @throws SQLException
     */
    private List<Prescription> loadRecords(ResultSet rs) throws SQLException{
        List<Prescription> prescriptions = new ArrayList<>();
        while (rs.next()){
            Prescription newP = new Prescription();
            newP.setDrugCode(rs.getString("drugCode"));
            newP.setEndDate(rs.getDate("endDate").toLocalDate());
            newP.setStartDate(rs.getDate("startDate").toLocalDate());
            newP.setOfficeVisitId(rs.getLong("officeVisitId"));
            newP.setPatientMID(rs.getLong("patientMID"));
            prescriptions.add(newP);
        }
        return prescriptions;
    }
}