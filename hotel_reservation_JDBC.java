package Lets_Ceate_File;

import java.sql.*;
import java.time.LocalDate;
import java.util.Scanner;

public class StayEasy {
    public static final String url = "jdbc:postgresql://localhost:5432/hoteldb";
    public static final String user = "postgres";
    public static final String password = "haris";

    public static void main(String[] args) {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (Exception e) {
            System.out.println(e);
        }

        try {
            Connection con = DriverManager.getConnection(url, user, password);
            Scanner sc = new Scanner(System.in);
            while (true) {
                System.out.println("\n================ Hotel Reservation System ================");
                System.out.println("1. 🏨 Reserve New Room");
                System.out.println("2. 📅 View Active Reservations");
                System.out.println("3. ❌ Cancel Reservation");
                System.out.println("0. 🚪 Exit");
                System.out.print("\nChoose an option: ");

                int choose = sc.nextInt();
                switch (choose) {
                    case 1: reserveNew(con); break;
                    case 2: retrieveTable(con); break;
                    case 3: reserveDelete(con); break;
                    case 0: exit(); break;
                    default: System.out.println("Invalid Option. Please try again.");
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    // Method to insert new reservation with current date
    public static void reserveNew(Connection con) {
        try {
            Scanner sc = new Scanner(System.in);
            System.out.print("Enter Reservation ID: ");
            int r_id = sc.nextInt();
            System.out.print("Enter Customer Name: ");
            String c_name = sc.next();
            System.out.print("Enter Customer Number: ");
            int c_num = sc.nextInt();
            System.out.print("Enter Room Number: ");
            int r_num = sc.nextInt();

            LocalDate today = LocalDate.now();

            String query = "INSERT INTO reservation (reserve_id, customer_name, customer_number, reserved_room, reservation_date) " +
                    "VALUES (?, ?, ?, ?, ?)";
            PreparedStatement pst = con.prepareStatement(query);
            pst.setInt(1, r_id);
            pst.setString(2, c_name);
            pst.setInt(3, c_num);
            pst.setInt(4, r_num);
            pst.setDate(5, Date.valueOf(today));

            int rowsAffected = pst.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("\n✅ Reservation created successfully on " + today);
            } else {
                System.out.println("\n❌ Reservation failed.");
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    // Method to retrieve active reservations only
    public static void retrieveTable(Connection con) {
        try {
            Statement stmnt = con.createStatement();
            ResultSet resultSet = stmnt.executeQuery("SELECT * FROM reservation WHERE status = 'active'");

            System.out.println("\n===== Active Reservations =====");
            while (resultSet.next()) {
                int id = resultSet.getInt("reserve_id");
                String name = resultSet.getString("customer_name");
                int num = resultSet.getInt("customer_number");
                int r_num = resultSet.getInt("reserved_room");
                Date date = resultSet.getDate("reservation_date");
                System.out.println("ID: " + id + " | Name: " + name + " | Phone: " + num + " | Room: " + r_num + " | Date: " + date);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    // Method to soft-delete reservation and set cancellation date
    public static void reserveDelete(Connection con) {
        try {
            Scanner sc = new Scanner(System.in);
            System.out.print("Enter Reservation ID to cancel: ");
            int r_id = sc.nextInt();

            System.out.print("Enter cancellation date (YYYY-MM-DD): ");
            String cancelDateInput = sc.next();
            LocalDate cancelDate = LocalDate.parse(cancelDateInput);

            String query = "UPDATE reservation SET status = 'cancelled', cancelled_date = ? WHERE reserve_id = ?";
            PreparedStatement pst = con.prepareStatement(query);
            pst.setDate(1, Date.valueOf(cancelDate));
            pst.setInt(2, r_id);

            int rowsAffected = pst.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("\n❌ Reservation cancelled on " + cancelDate);
            } else {
                System.out.println("\n⚠️ No reservation found with the given ID.");
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    // Method to exit system with animation
    public static void exit() throws InterruptedException {
        System.out.print("\nExiting system");
        int i = 5;
        while (i-- > 0) {
            System.out.print(".");
            Thread.sleep(500);
        }
        System.out.println("\nThank you for using Hotel Reservation System! Have a great day! ✨");
        System.exit(0);
    }
}
