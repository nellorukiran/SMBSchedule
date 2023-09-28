package org.smb;

import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.Format;
import java.text.SimpleDateFormat;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

public class CustomerDownloadBizlogic {
	static String TITLE = "SREE MAHALAKSHMI BINDUHASINI FINANCE";
	static String FOLFDER = "D:\\Application\\Reports\\";
	static String displayMsg = "";

	public static String userDownloadDeatils(String inputVal) {
		System.out.println("Downloading the User Details by " + inputVal);
		Connection con = null;
		Connection awsCon = null;
		PreparedStatement paymentDetailsQury = null;
		ResultSet rs = null;
		// PreparedStatement maxdueAmtQury = null ,maxPerDueAmtQury = null
		// ,maxNextDueAmtqury =null ;

		String displayMsg = "";
		FileOutputStream fileOut = null;
		String dateString = "";
		String formatedDate = null;
		java.util.Date date = new java.util.Date();
		Format formatter = new SimpleDateFormat("dd-MMM-yyyy");
		formatedDate = formatter.format(date);
		dateString = formatedDate.replace(":", "-");
		if (inputVal.length() > 0) {
			try {
				awsCon = DBConnection.getAWSDBConnection();
				if (awsCon != null) {
					if ("CustomerId".equals(inputVal)) {
						paymentDetailsQury = awsCon
								.prepareStatement(CommonConstents.PAYMENT_DETAILS_DOWNLOAD_QUERY_CUSID);
					}
					if ("Address".equals(inputVal)) {
						paymentDetailsQury = awsCon
								.prepareStatement(CommonConstents.PAYMENT_DETAILS_DOWNLOAD_QUERY_ADDRESS);
					}
					rs = paymentDetailsQury.executeQuery();

				} else {
					con = DBConnection.getDBConnection();
					if ("CustomerId".equals(inputVal)) {
						paymentDetailsQury = con.prepareStatement(CommonConstents.PAYMENT_DETAILS_DOWNLOAD_QUERY_CUSID);
					}
					if ("Address".equals(inputVal)) {
						paymentDetailsQury = con
								.prepareStatement(CommonConstents.PAYMENT_DETAILS_DOWNLOAD_QUERY_ADDRESS);
					}
					rs = paymentDetailsQury.executeQuery();

				}
			} catch (Exception e) {
				System.out.println("Exception while AWS DB Connect / Operation #####-1" + e);
				try {
					con = DBConnection.getDBConnection();
					if ("CustomerId".equals(inputVal)) {
						paymentDetailsQury = con.prepareStatement(CommonConstents.PAYMENT_DETAILS_DOWNLOAD_QUERY_CUSID);
					}
					if ("Address".equals(inputVal)) {
						paymentDetailsQury = con
								.prepareStatement(CommonConstents.PAYMENT_DETAILS_DOWNLOAD_QUERY_ADDRESS);
					}
					rs = paymentDetailsQury.executeQuery();
				} catch (Exception e1) {
					System.out.println("Exception while DB Connect / Operation #####-2" + e);
				}
				displayMsg = "notdownloaded";
			}
			try {

				HSSFWorkbook hwb = new HSSFWorkbook();
				HSSFSheet sheet = hwb.createSheet("SMB Finance");
				HSSFRow rowTitle = sheet.createRow((short) 0);
				rowTitle.createCell((short) 0).setCellValue(TITLE);

				HSSFRow rowhead = sheet.createRow((short) 2);
				rowhead.createCell((short) 0).setCellValue("S NO");
				rowhead.createCell((short) 1).setCellValue("CUS ID");
				rowhead.createCell((short) 2).setCellValue("REMARKS");
				rowhead.createCell((short) 3).setCellValue("CUSTMER NAME");
				rowhead.createCell((short) 4).setCellValue("ADDRESS");
				rowhead.createCell((short) 5).setCellValue("ITEM");
				rowhead.createCell((short) 6).setCellValue("BUY DATE");
				rowhead.createCell((short) 7).setCellValue("D TIME");
				rowhead.createCell((short) 8).setCellValue("T DUE");
				rowhead.createCell((short) 9).setCellValue("DUES");
				rowhead.createCell((short) 10).setCellValue("D AMT");
				rowhead.createCell((short) 11).setCellValue("FINE");
				rowhead.createCell((short) 12).setCellValue("NEXT D");
				rowhead.createCell((short) 13).setCellValue("PHONE");
				int index = 3;
				int sno = 0;
				while (rs.next()) {
					sno++;

					HSSFRow row = sheet.createRow((short) index);
					row.createCell((short) 0).setCellValue(sno);
					row.createCell((short) 1).setCellValue(rs.getInt(1));
					row.createCell((short) 2).setCellValue("");
					row.createCell((short) 3).setCellValue(rs.getString(2));
					row.createCell((short) 4).setCellValue(rs.getString(3));
					row.createCell((short) 5).setCellValue(rs.getString(4));
					String buy_date = rs.getString(5);
					buy_date = (buy_date != null) ? buy_date : "";

					row.createCell((short) 6).setCellValue(buy_date);
					row.createCell((short) 7).setCellValue(rs.getString(6));
					row.createCell((short) 8).setCellValue(rs.getInt(7));
					row.createCell((short) 9).setCellValue(rs.getInt(8));
					row.createCell((short) 10).setCellValue(rs.getInt(9));
					row.createCell((short) 11).setCellValue(rs.getInt(10));
					row.createCell((short) 12).setCellValue(rs.getInt(11));
					row.createCell((short) 13).setCellValue((rs.getString(12)));

					index++;
				}

				fileOut = new FileOutputStream(FOLFDER + "OrderBy_" + inputVal + "_" + dateString + ".xls");
				hwb.write(fileOut);
				paymentDetailsQury.close();
				if (con != null)
					con.close();
				if (awsCon != null)
					awsCon.close();
				fileOut.close();
				displayMsg = "downloaded";

				System.out.println(FOLFDER + "OrderBy_" + inputVal + "_" + dateString + ".xls");
				System.out.println("File Downloaded Successfully....");
			} catch (Exception e) {
				displayMsg = "notdownloaded";
				System.out.println("Exception While Report Generation in CustomerDownloadBizlogic ####" + e);
			}
		}
		return displayMsg;

	}
}
