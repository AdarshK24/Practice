package com.gk.ims.utils;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.nio.file.CopyOption;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.security.SecureRandom;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Properties;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.gk.ims.wageloss.domain.CustomerEnrolmentIns;

public class Utils {

	public static final String SUCCESS = "{\"status\":\"Success\"}";
	public static final String FAILURE = "{\"status\":\"Failure\"}";
	public static final String SERVER_ERROR = "{ \"response\" : \"Server error.\" }";
	public static final String INVALID_LOAN_ID = "{ \"response\" : \"Please enter a valid loanId.\" }";
	public static final String INTIATE_CLAIM = "INTIATE_CLAIM";
	public static final String UPDATE_CLAIM = "UPDATE_CLAIM";
	public static final String AUTHORIZE_CLAIM = "AUTHORIZE_CLAIM";
	public static final String UPDATE_SETTLEMENT = "UPDATE_SETTLEMENT";
	public static final String CANCEL_CLAIM = "CANCEL_CLAIM";
	public static final String INSURANCE_OPS = "INSURANCE_OPS";
	public static final String INSURANCE_OPS_CHECKER = "INSURANCE_OPS_CHECKER";
	public static final String IN_PROGRESS = "In progress";
	public static final String PARTIAL_SUCCESS = "partially success";
	public static final String INVALIDFILE = "File is invalid";
	public static final String STATUS = "Status";
	public static final String BASESTRING = "BaseString";
	public static final String REFRESH = "Refresh";
	public static final String INVALID_CSV_FILE = "Invalid column name in the csv file:";
	public static final String INVALID_COLUMN_NAME_IN_THE_CSV_FILE = "Invalid column name in the csv file:";
	public static final String INVALID_EXCEL_FILE = "Invalid column name in the excel file:";
	public static final String INVALID_COLUMN_NAME_IN_THE_EXCEL_FILE = "Invalid column name in the excel file:";
	public static final String INVALID_DATA_IN_THE_TEXT_FILE = "Invalid data in the text file:";
	public static final String UNEXPECTED_FORMAT_IN_THE_TEXT_FILE = "UnExpected Format in the text file:";
	public static final String EXCELX = "xlsx";
	public static final String EXCEL = "xls";

	private static Map<String, String> queryMap = new HashMap<>();

	public static final Format rupeeFormat = com.ibm.icu.text.NumberFormat.getCurrencyInstance(new Locale("en", "in"));

	public static final int GROUP_LOAN_BUSINESS_ID = 1;

	public static final String DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";

	public static final String RF_INSURANCE_PRODUCTS = "2,3";

	public static final Object objForSynchronization = new Object();

	public static final Object objForSynchronizationUpdate = new Object();

	private static final String NUMBER = "0123456789";
	private static final Random RANDOM = new SecureRandom();

	public static final String GRAMEENKOOTADS = "GRAMEENKOOTADS";
	public static final String STAGINGDATADS = "STAGINGDATADS";
	public static final String PUSHED_BACK_TO_KM = "Pushed back to KM";

	private Utils() {

	}

	public enum UploadTypes {
		IS_NEW_SETTLEMENT_STAGE, IS_APPROVED_BY_INSURANCE_MAKER_STAGE, IS_APPROVED_BY_INSURANCE_CHECKER_STAGE,
		IS_COI_MPH, NONE;
	}

	public enum PAYMENT_TYPE {
		PR("Premium Refund"), EC("Excess Collection"), CS("Claim Settlement");

		private String paymentType;

		private PAYMENT_TYPE(String paymentType) {
			this.paymentType = paymentType;
		}

		public String getPaymentType() {
			return paymentType;
		}

	}

	public static byte[] fileToByteArray(File f) {
		try {
			try (FileInputStream in = new FileInputStream(f)) {

				BufferedInputStream bi = new BufferedInputStream(in);

				byte[] bytes = new byte[(int) f.length()];
				int c = -1;
				int ix = 0;
				while ((c = bi.read()) > -1) {
					bytes[ix] = (byte) c;
					ix++;
				}
				bi.close();
				return bytes;
			}
		} catch (IOException e) {
			return new byte[0];
		}
	}

	public static String formatRupee(double amount) {
		String amountStr = rupeeFormat.format(amount);
		return amountStr.substring(1, amountStr.length() - 1);
	}

	public static void copyFileToDirectory(String origin, String destDir, String destination) throws IOException {

		Path from = Paths.get(origin);
		Path to = Paths.get(destination);
		File directory = new File(String.valueOf(destDir));

		if (!directory.exists()) {
			directory.mkdir();
		}
		// overwrite the destination file if it exists, and copy
		// the file attributes, including the rwx permissions
		CopyOption[] options = new CopyOption[] { StandardCopyOption.REPLACE_EXISTING,
				StandardCopyOption.COPY_ATTRIBUTES

		};
		Files.copy(from, to, options);

	}

	public static String getStackTrace(final Exception e) {
		String lStackTrace;
		StringWriter lSw;
		PrintWriter lPw;
		lSw = new StringWriter();
		lPw = new PrintWriter(lSw);
		e.printStackTrace(lPw);
		lStackTrace = lSw.toString();
		return lStackTrace;
	}

	public InputStream getResourceFromClasspath(String fileName) {
		return getClass().getClassLoader().getResourceAsStream(fileName);
	}

	public static String getSql(String sqlFile) throws IOException {
		InputStream inputStream = null;
		String query = queryMap.get(sqlFile);
		if (query == null) {
			inputStream = new Utils().getResourceFromClasspath("sql/" + sqlFile + ".sql");
			query = IOUtils.toString(inputStream);
			queryMap.put(sqlFile, query);
		}
		IOUtils.closeQuietly(inputStream);
		return query;
	}

	public static String getCurrentYearMonth() {
		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date());
		int year = cal.get(Calendar.YEAR);
		SimpleDateFormat sdf = new SimpleDateFormat("MM");
		String month = sdf.format(new Date());
		return String.valueOf(year) + month;
	}

	public static String getDeceasedPerson(String deceasedPerson) {
		String fullString = "";
		if ("M".equalsIgnoreCase(deceasedPerson)) {
			fullString = "Member";
		} else {
			fullString = "Spouse";
		}
		return fullString;
	}

	public static String getDocumentType(String documentType) {
		String fullString = "";
		if ("DC".equalsIgnoreCase(documentType)) {
			fullString = "Death_Certificate";
		} else if ("KYC".equalsIgnoreCase(documentType)) {
			fullString = "KYC_Document";
		} else if ("CF".equalsIgnoreCase(documentType)) {
			fullString = "GK_Claim_Format";
		} else if ("NBP".equalsIgnoreCase(documentType)) {
			fullString = "Nominee_Passbook";
		} else if ("LAF".equalsIgnoreCase(documentType)) {
			fullString = "Loan_Application";
		} else if ("OTH".equalsIgnoreCase(documentType)) {
			fullString = "Others";
		} else if ("DD".equalsIgnoreCase(documentType)) {
			fullString = "Declaration Document";
		}
		return fullString;
	}

	public static String getDocType(String documentType) {
		String fullString = "";
		if ("Death_Certificate".equalsIgnoreCase(documentType)) {
			fullString = "DC";
		} else if ("KYC_Document".equalsIgnoreCase(documentType)) {
			fullString = "KYC";
		} else if ("GK_Claim_Format".equalsIgnoreCase(documentType)) {
			fullString = "CF";
		} else if ("Nominee_Passbook".equalsIgnoreCase(documentType)) {
			fullString = "NBP";
		} else if ("Loan_Application".equalsIgnoreCase(documentType)) {
			fullString = "LAF";
		} else if ("Others".equalsIgnoreCase(documentType)) {
			fullString = "OTH";
		} else if ("Declaration Document".equalsIgnoreCase(documentType)) {
			fullString = "DD";
		}
		return fullString;
	}

	public static void deleteFile(String filePath) {
		File file = new File(filePath);
		if (file.exists())
			file.delete();
	}

	public static byte[] readFile(String filePath) throws IOException {
		Path path = Paths.get(filePath);
		return Files.readAllBytes(path);
	}

	public static void getFiles(String directoryName, List<File> files) {
		File directory = new File(directoryName);
		// get all the files from a directory
		File[] fList = directory.listFiles();
		for (File file : fList) {
			if (file.isFile()) {
				files.add(file);
			} else if (file.isDirectory()) {
				getFiles(file.getAbsolutePath(), files);
			}
		}
	}

	public static boolean isMoreThanOneMB(File file) {
		boolean isMoreThanOneMB = false;
		long sizeInBytes = file.length();
		long sizeInMb = sizeInBytes / (1024 * 1024);
		if (sizeInMb >= 1) {
			isMoreThanOneMB = true;
		}
		return isMoreThanOneMB;
	}

	public static <T> T replaceNull(T source, T replaceValue) {
		return source == null ? replaceValue : source;
	}

	public static String repEmptyObject(Object obj) {
		if (null == obj)
			return "";
		else
			return obj.toString();
	}

	public static String rep(Object obj) {
		if (null == obj)
			return "null";
		else
			return "'" + obj.toString() + "'";
	}

	public static String prepareIdsForQuery(String ids) {
		String idsAsCommaSeperatedString = null;
		if (StringUtils.isNotBlank(ids)) {
			idsAsCommaSeperatedString = Arrays.asList(ids.split(",")).stream()
					.collect(Collectors.joining("','", "'", "'"));
		}
		return idsAsCommaSeperatedString;
	}

	public static <T> Predicate<T> distinctByKey(Function<? super T, ?> keyExtractor) {
		Map<Object, Boolean> map = new ConcurrentHashMap<>();
		return t -> map.putIfAbsent(keyExtractor.apply(t), Boolean.TRUE) == null;
	}

	public static String getAction(String status) {
		String str = "";
		if ("Approved".equalsIgnoreCase(status) || "Settled".equalsIgnoreCase(status)) {
			str = "A";
		} else if ("Rejected".equalsIgnoreCase(status)) {
			str = "R";
		} else if ("On Hold".equalsIgnoreCase(status)) {
			str = "H";
		} else if ("Pushback".equalsIgnoreCase(status)) {
			str = "P";
		} else if ("NEFT".equalsIgnoreCase(status)) {
			str = "N";
		} else if ("PushbackMaker".equalsIgnoreCase(status)) {
			str = "M";
		}
		return str;
	}

	public static String getActionDes(String status) {
		String fullString = "";
		if ("A".equalsIgnoreCase(status)) {
			fullString = "Approved";
		} else if ("R".equalsIgnoreCase(status)) {
			fullString = "Rejected";
		} else if ("H".equalsIgnoreCase(status)) {
			fullString = "On Hold";
		}
		return fullString;
	}

	public static String fileDelete(File file) {
		return file.delete() ? "File deleted Successfully" : "File couldnt be deleted";
	}

	public static String fileCreate(File file) throws IOException {
		return file.createNewFile() ? "File Created Successfully" : "File couldnt be created";
	}

	public static String getRoleTag(String roleId) {
		String str = StringUtils.EMPTY;
		if (ROLE.KENDRA_MANAGER.name().equalsIgnoreCase(roleId)) {
			str = "KM";
		} else if (ROLE.AREA_MANAGER.name().equalsIgnoreCase(roleId)) {
			str = "AM";
		} else if (ROLE.BRANCH_USER.name().equalsIgnoreCase(roleId)) {
			str = "BU";
		} else if (ROLE.INSURANCE_CHECKER.name().equalsIgnoreCase(roleId)) {
			str = "IC";
		} else if (ROLE.INSURANCE_MAKER.name().equalsIgnoreCase(roleId)) {
			str = "IM";
		} else if (ROLE.INSURANCE_OPS.name().equalsIgnoreCase(roleId)) {
			str = "IOPS";
		} else if (ROLE.INSURANCE_OPS_CHECKER.name().equalsIgnoreCase(roleId)) {
			str = "IOPSCHK";
		} else if (ROLE.RPC_CHECKER.name().equalsIgnoreCase(roleId)) {
			str = "RPCCHK";
		}

		return str;
	}

	static char randomChar() {
		return NUMBER.charAt(RANDOM.nextInt(NUMBER.length()));
	}

	/**
	 * For WageLoss Id generation [enrollment/claim]
	 * 
	 * @param spacerChar
	 * @return
	 */
	public static String getUniqueId(char spacerChar) {
		StringBuilder sb = new StringBuilder();
		int length = 11;
		int spacing = 0;
		int spacer = 0;
		while (length > 0) {
			if (spacer == spacing) {
				sb.append(spacerChar);
				spacer = 0;
			}
			length--;
			spacer++;
			sb.append(randomChar());
		}
		return sb.toString();
	}

	public static String getUniqueId2(char spacerChar) {
		String id = getUniqueId(spacerChar);
		StringBuilder sc = new StringBuilder();
		int length = 11;
		int spacing = 0;
		int spacer = 0;
		while (length > 0) {
			if (spacer == spacing) {
				sc.append(spacerChar).replace(spacer, length, id);
				spacer = 0;
			}
			length--;
			spacer++;
			sc.append(randomChar());
		}
		return sc.toString();
	}
	
	/**
	 * Method to count the memberID for sent/received to insurer excel data
	 * 
	 * @param <T>
	 * @param inputList
	 * @return
	 */
	public static <T> Map<T, Integer> countByMemberIdFromExcelData(List<T> inputList) {
		Map<T, Integer> resultMap = new HashMap<>();
		inputList.forEach(e -> resultMap.merge(e, 1, Integer::sum));
		return resultMap;
	}

	/**
	 * Method to generate random numbers for wageloss sms service
	 * 
	 * @param len
	 * @return
	 */
	public static String generateRandomNumber(int len) {
		char[] otp = new char[len];
		for (int i = 0; i < len; i++) {
			otp[i] = NUMBER.charAt(RANDOM.nextInt(NUMBER.length()));
		}
		return new String(otp);
	}

	/**
	 * Gender value for cbCheck call
	 * 
	 * @param gender
	 * @return genderString
	 */
	public static String getGenderValue(char gender) {
		String genderValue = "";
		if (gender == 'F') {
			genderValue = "1";
		} else if (gender == 'M') {
			genderValue = "2";
		} else {
			genderValue = "3";
		}
		return genderValue;
	}

	/**
	 * Converting the comma-separated string into list string
	 * 
	 * @param ids
	 * @return
	 */
	public static List<String> prepareListIdForQuery(String ids) {
		List<String> idAsListString = null;
		if (StringUtils.isNotBlank(ids)) {
			idAsListString = Arrays.asList(ids.split("\\s*,\\s*"));
		}
		return idAsListString;
	}

	public static Properties readPropertiesFile(String fileName) throws IOException {
		Properties prop = null;
		try (InputStream is = new Utils().getResourceFromClasspath(fileName)) {
			prop = new Properties();
			prop.load(is);
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		return prop;
	}

	public static String getGenderDes(char gender) {
		String fullString = "";
		if (gender == 'F') {
			fullString = "Female";
		} else if (gender == 'M') {
			fullString = "Male";
		} else {
			fullString = "Trans";
		}
		return fullString;
	}

	public static String print(Object object) {
		String response = StringUtils.EMPTY;
		ObjectMapper mapper = new ObjectMapper().enable(SerializationFeature.INDENT_OUTPUT);
		try {
			response = mapper.writeValueAsString(object);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return response;
	}

	public static String readHtmlFile(String fileName) throws IOException {
		InputStream inputStream = new Utils().getResourceFromClasspath(fileName);
		String content = IOUtils.toString(inputStream);
		IOUtils.closeQuietly(inputStream);
		return content;
	}
}
