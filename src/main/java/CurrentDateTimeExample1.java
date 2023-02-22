import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.util.Date;
import java.util.Locale;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class CurrentDateTimeExample1 {
	private static final String CONNECTION_ESTABLISHED = "Connection established";
	private static final String DATASOURCE_FAILED = "Failed to lookup datasource";

	public static LocalDateTime convertToLocalDateTimeViaInstant(Date dateToConvert) {
		return dateToConvert.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
	}

	public static int getMembers(String day) {
		String selectQuery = "Select * from grameenkoota.customer_enrolment_ins where  member_id = ? AND kendra_id = ? AND branch_id = ? AND enrolment_end_date > (now() - interval '1' month) AND enrolment_end_date < (now() + interval '1' month) AND enrolment_status not in ('9','10','11','12')";
		JdbcConnector connector = new JdbcConnector();
		try (Connection connection = connector.getJdbcConnection(Utils.GRAMEENKOOTADS);
				PreparedStatement statement = connection.prepareStatement(selectQuery);) {
//			statement.setString(1, custId);
//			statement.setString(2, kendraId);
//			statement.setString(3, branchId);
			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) {
//				result.put("Renew_Flag",resultSet.getString("is_eligible_enrolment_renewed"));
//				result.put("Renew_Id", resultSet.getString("renewed_enrolment_id"));
//				result.put("enrolmentId",resultSet.getString("enrolment_id"));
			}
			resultSet.close();
		} catch (Exception exception) {
			exception.printStackTrace();
//			logger.error("");
		}
		return result;

		return 0;

	}

	@SuppressWarnings("deprecation")
	public static void main(String[] args) {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
		Locale locale = Locale.getDefault();
		LocalDateTime now = LocalDateTime.now();
		Date startDate = DateUtils.addDay(now, 1);
		LocalDateTime dateTimeViaInstant = convertToLocalDateTimeViaInstant(startDate);
		DayOfWeek day = dateTimeViaInstant.getDayOfWeek();
		String upperCase = day.getDisplayName(TextStyle.SHORT, locale).toUpperCase(locale);
		System.out.println(upperCase);
		int members = getMembers(upperCase);
	}
}