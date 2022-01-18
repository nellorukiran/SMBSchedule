package org.smb;

public class CusmerIdReport {

	public static void main(String[] args) {

		try{				
			CustomerDownloadBizlogic.userDownloadDeatils("CustomerId");			
		}catch(Exception e){
			e.printStackTrace();
			System.out.println("Exception ####"+e);
		}
		

	}

}
