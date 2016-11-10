package model;

import java.io.Serializable;
import java.sql.Date;

public class RiskItem implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int riskItemId;
	private int projectId;
	private int submitterId;
	private Date createDate;
	private String riskName;
	private String riskContent;
	private String trigger;
	private int possibility;//1,2,3
	private int impact;//1,2,3
	private String riskStatus;
	private 
	

}
