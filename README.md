🏨 StayEasy - Hotel Booking Console App
Ek simple aur powerful hotel reservation system banaya hai Java mein, jo aapko naye room reserve karne, reservations dekhne, aur cancel karne ki facility deta hai — sab kuch console (CLI) ke through.
Backend mein PostgreSQL database use kiya gaya hai. Pure JDBC ka istemal kiya gaya hai bina kisi external library ke.

🔧 Tech Stack
Java (JDK 8+) – Console Application Development

PostgreSQL – Data storage (Reservations)

JDBC – Database Connectivity

💡 Key Features
🏨 Reserve New Room (Customer ka naam, number, room number ke saath)

📅 View Active Reservations (sirf active reservations dikhata hai)

❌ Cancel Reservation (soft-delete with cancellation date)

🗓️ Automatic Date Handling (Reservation aur Cancellation dates auto save)

🔄 Console Menu Navigation (easy to use)

🚫 Input Validation (basic error handling)

🗂 Database Table
📄 Table: reservation

Column	Type	Details
reserve_id	INT	Primary Key
customer_name	TEXT	Customer ka full name
customer_number	BIGINT	Customer phone number
reserved_room	INT	Room number
reservation_date	DATE	Reservation created date
status	TEXT	Default 'active', 'cancelled'
cancelled_date	DATE	Reservation cancellation date
🛠️ SQL Queries
Yeh apko pehle apne PostgreSQL database mein run karni hongi:

sql
Copy
CREATE TABLE reservation (
    reserve_id INT PRIMARY KEY,
    customer_name VARCHAR(100) NOT NULL,
    customer_number BIGINT NOT NULL,
    reserved_room INT NOT NULL,
    status VARCHAR(20) DEFAULT 'active',
    booking_date DATE DEFAULT CURRENT_DATE,
    cancelled_date DATE
);

▶️ How to Run
PostgreSQL install karo aur ek database banao, naam: hoteldb

Upar di gayi SQL query run karo reservation table create karne ke liye.

Apne DB credentials apne code mein update karo (agar zarurat ho).

java
Copy
public static final String url = "jdbc:postgresql://localhost:5432/hoteldb";
public static final String user = "postgres"; // apna username
public static final String password = "your_password"; // apna password
Code compile aur run karo:

bash
Copy
Edit
javac hotel_reservation_JDBC.java
java hotel_reservation_JDBC
🧪 App Flow
text
Copy
Edit
================ Hotel Reservation System ================
1. 🏨 Reserve New Room
2. 📅 View Active Reservations
3. ❌ Cancel Reservation
0. 🚪 Exit
✅ Reserve New Room → ID, Name, Number aur Room enter karoge → reservation save ho jayega with today's date.

✅ View Active Reservations → Sirf 'active' status wale reservations dikhaye jaayenge.

✅ Cancel Reservation → ID dalke reservation cancel kar sakte ho aur cancellation date save hogi.

✅ Exit → System se safely exit ho jaoge with animation.

🔄 Cancellation Logic (How It Works)
User reservation ID daalta hai.

Cancellation date input hoti hai.

Reservation ka status 'cancelled' set hota hai aur cancelled_date update hoti hai.

Safe soft-delete logic — data table mein hi rahta hai.

✅ TODOs / Future Improvements
Proper input validations (jaise wrong date format handle karna)

GUI version (JavaFX / Swing se)

Email notifications for booking/cancellation

Multi-user login system (admin/staff)

Room availability check before reservation

🧑‍💻 Created By
Made by **Haris Khatti**
Dil se likha, test karke banaya, aur hotel wale vibe mein soch ke design kiya 😄🏨
Agar pasand aaye toh ⭐ zarur dena!

📜 License
MIT License — Open-source hai, use karo, seekho aur upgrade karo!
