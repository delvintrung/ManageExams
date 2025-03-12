/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package app.BLL;
import app.DAL.Dashboard_DAL;
import app.DTO.Dashboard_DTO;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author ADMIN
 */
public class Dashboard_BLL {
        private Dashboard_DAL dbDal = new Dashboard_DAL();
    	public ArrayList<Dashboard_DTO> getDashboard() {
		return dbDal.getDashboard();
	}
        
        public ArrayList<Dashboard_DTO> getDashboardfilter(String testcode,Date from,Date to) {
            return dbDal.getDashboardfilter(testcode, from, to);
        }
}
