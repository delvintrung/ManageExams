/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package app.DTO;

/**
 *
 * @author ADMIN
 */
public class Dashboard_DTO {
        String testcode;
	int SLduthi;
	int SLdat;
        int SLkodat;
	
	public Dashboard_DTO() {
		super();
		this.testcode = "";
		this.SLduthi = 0;
		this.SLkodat = 0;
                this.SLdat = 0;
	}
	
	public Dashboard_DTO(String testcode, int SLduthi, int SLkodat, int SLdat) {
		super();
		this.testcode = testcode;
		this.SLduthi = SLduthi;
		this.SLkodat = SLkodat;
                this.SLdat = SLdat;
	}

	public String gettestcode() {
		return testcode;
	}

	public void settestcode(String testcode) {
		this.testcode = testcode;
	}

	public int getSLduthi() {
		return SLduthi;
	}

	public void setSLduthi(int SLduthi) {
		this.SLduthi = SLduthi;
	}

	public int getSLkodat() {
		return SLkodat;
	}

	public void setSLkodat(int SLkodat) {
		this.SLkodat = SLkodat;
	}

	public int getSLdat() {
		return SLdat;
	}

	public void setSLdat(int SLdat) {
		this.SLdat = SLdat;
	}

//	@Override
//	public String toString() {
//	    return this.getTpTitle();
//	}
}
