import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class DatabaseLoader {
    public static void loadCustomers(List<Customer> customers, String tableName) {
        String sql = "INSERT INTO " + tableName + " (customer_name, customer_id, open_date, last_consulted_date, vaccination_type, doctor_name, state, country, post_code, dob, is_active, age, days_since_last_consulted) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = DatabaseConnection.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            for (Customer customer : customers) {
                pstmt.setString(1, customer.getCustomerName());
                pstmt.setString(2, customer.getCustomerId());
                pstmt.setDate(3, java.sql.Date.valueOf(customer.getOpenDate()));
                pstmt.setDate(4, java.sql.Date.valueOf(customer.getLastConsultedDate()));
                pstmt.setString(5, customer.getVaccinationType());
                pstmt.setString(6, customer.getDoctorName());
                pstmt.setString(7, customer.getState());
                pstmt.setString(8, customer.getCountry());
                pstmt.setInt(9, customer.getPostCode());
                pstmt.setDate(10, java.sql.Date.valueOf(customer.getDob()));
                pstmt.setString(11, customer.getIsActive());
                pstmt.setInt(12, customer.getAge());
                pstmt.setInt(13, customer.getDaysSinceLastConsulted());

                pstmt.addBatch();
            }
            pstmt.executeBatch();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
