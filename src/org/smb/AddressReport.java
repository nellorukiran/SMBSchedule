package org.smb;

public class AddressReport {

	public static void main(String[] args) {

		try{				
			CustomerDownloadBizlogic.userDownloadDeatils("Address");			
		}catch(Exception e){
			e.printStackTrace();
			System.out.println("Exception ####"+e);
		}
		

	}

}
