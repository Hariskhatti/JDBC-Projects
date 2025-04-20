package Lets_Ceate_File;

import java.sql.*;
import java.util.Scanner;

public class hotel_reservation_JDBC {
    public static final String url = "jdbc:postgresql://localhost:5432/hoteldb";
    public static final String user = "postgres"; // Default PostgreSQL user
    public static final String password = "haris";
    public static void main(String[] args) {
        try{
            Class.forName("org.postgresql.Driver");
        }catch (Exception e){
            System.out.println(e);
        }
        try{
            Connection con = DriverManager.getConnection(url,user,password);
            Scanner sc = new Scanner(System.in);
            while(true){
                System.out.println("Welcome to the hotel");
                System.out.println("1. Reserve New Customer");
                System.out.println("2. See Reservations");
                System.out.println("3. Update Reservation");
                System.out.println("4. Delete Reservation");
                System.out.println("0. Exit");
                System.out.println("Choose Number Here To Perform Action");
                int choose = sc.nextInt();
                switch (choose){
                    case 1: reserveNew(con);
                    break;
                    case 2:  retriveTable(con);
                        break;
                    case 3: reserveUpdate(con);
                        break;
                    case 4: reserveDelete(con);
                        break;
                    case 0:  exit();
                    break;
                    default:
                        System.out.println("Thanks");
                }
            }
        }catch (Exception e){
            System.out.println(e);
        }
    }
    public static void reserveNew(Connection con){ //Scanner sc dena he argument me
        try{
            Statement stmnt = con.createStatement();
            Scanner sc = new Scanner(System.in);
            System.out.println("Enter reservation id");
            int r_id = sc.nextInt();
            System.out.println("Enter customer name");
            String c_name = sc.next();
            System.out.println("Enter customer number");
            int c_num = sc.nextInt();
            System.out.println("Enter room number");
            int r_num = sc.nextInt();
            int rowsAffect = stmnt.executeUpdate("insert into reservation (reserve_id, customer_name, customer_number, reserved_room) " +
                    "values (" + r_id + ", '" + c_name + "', " + c_num + ", " + r_num + ");");
            if (rowsAffect>0){
                System.out.println("inserted succesfully");
            }else System.out.println("not successful");
        }catch (Exception e){
            System.out.println(e);
        }
    }
    public static void reserveUpdate(Connection con){
        try {
            PreparedStatement ps = con.prepareStatement("Update reservation set reserve_id = ? , customer_name =? , customer_number =?, reserved_room =? where reserve_id =?");
            Scanner sc = new Scanner(System.in);
            System.out.println("Enter reserve_id");
            int r_id = sc.nextInt();
            System.out.println("Enter customer_name");
            String c_name = sc.next();
            System.out.println("Enter cutomer_number");
            int c_num = sc.nextInt();
            System.out.println("Enter reserveed_room");
            int r_num = sc.nextInt();
            System.out.println("where reserve_id will be");
            int ri_id = sc.nextInt();
            ps.setInt(1,r_id);
            ps.setString(2,c_name);
            ps.setInt(3,c_num);
            ps.setInt(4,r_num);
            ps.setInt(5,ri_id);
            int rowsAffect =  ps.executeUpdate();
            if (rowsAffect>0){
                System.out.println("Updated Successfully");
            }else System.out.println("Not Updated");
        }catch (Exception e ){
            System.out.println(e);
        }
        }
    public static void retriveTable(Connection con){
        try{
            Statement stmnt = con.createStatement();
            ResultSet resultSet = stmnt.executeQuery("Select * from reservation");
            while(resultSet.next()){
                int id = resultSet.getInt("reserve_id");
                String name = resultSet.getString("customer_name");
                int num = resultSet.getInt("customer_number");
                int r_num = resultSet.getInt("reserved_room");
                System.out.println(r_num +" "+ name+ " "+ num+ " "+ r_num);
            }System.out.println("\n\n");

        }catch (Exception e){
            System.out.println(e);
        }
    }

    public static void reserveDelete(Connection con){
        try {
            Statement stmnt = con.createStatement();
            Scanner sc = new Scanner(System.in);
            System.out.println("enter room number which you want to delete");
            int r_id = sc.nextInt();
            int rowsAffect = stmnt.executeUpdate("delete from reservation where reserved_room= "+r_id+";");
            if (rowsAffect>0){
                System.out.println("deleted succesfully");
            }else System.out.println("deletion not successful");
        }catch (Exception e){
            System.out.println(e);
        }
    }
    public static void exit() throws InterruptedException {
        System.out.print("Exiting System");
        int i = 5;
        while(i!=0){
            System.out.print(".");
            Thread.sleep(500);
            i--;
        }
        System.out.println();
        System.out.println("ThankYou For Using Hotel Reservation System!!!");
        System.exit(0);
    }
}

