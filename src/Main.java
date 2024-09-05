import java.sql.*;

public class Main {
  public static void main(String[] args) {
    Connection connection = null;
    Statement statement = null;
    ResultSet resultSet = null;
    int[] numbers = {
      1,
      2,
      3,
      4,
      5,
      6,
      7,
      8,
      9,
      10,
      11,
      12,
      13,
      14,
      15,
      16,
      17,
      18
    };

    try {
      String data = "";
      // Load Oracle JDBC driver
      Class.forName("oracle.jdbc.driver.OracleDriver");

      // Connect to the database
      connection = DriverManager.getConnection("", "", "");

      // Create a statement
      statement = connection.createStatement();
      Timer timer = new Timer(16, 37);
      while (true) {
        // Execute a query
        if (timer.checkTime()) {

          int i = 0;
          while (i < numbers.length) {
            data = "";

            String query = "SELECT\r\n"
            		+ "    i.ID_PENITENCIAR,\r\n"
            		+ "    i.LOC_DETENTIE,\r\n"
            		+ "    i.ID,\r\n"
            		+ "    i.CIRCUMSTANTE,\r\n"
            		+ "    i.ASISTENTAMEDICALA,\r\n"
            		+ "    i.MEDICAL_CONCLUSION,\r\n"
            		+ "    i.INSERTEDDATE,\r\n"
            		+ "    i.MEDICAL_STAFF,\r\n"
            		+ "    i.DREPTINFORM,\r\n"
            		+ "    spr.NAME AS INCIDENT_TYPE_NAME,\r\n"
            		+ "    d.NAME || ' ' || d.SURNAME AS DETINUTI_NAME_SURNAME\r\n"
            		+ "FROM\r\n"
            		+ "    PRISON.INCIDENTS i\r\n"
            		+ "JOIN\r\n"
            		+ "    PRISON.DETINUTI d ON i.IDNP = d.IDNP\r\n"
            		+ "JOIN\r\n"
            		+ "    PRISON.SPR_TYPE_INCIDENT spr ON i.ID_TYPE_INCIDENT = spr.ID\r\n"
            		+ "WHERE\r\n"
            		+ "    i.ID_TYPE_INCIDENT IN (1, 2, 3, 4, 6)\r\n"
            		+ "    AND (TRUNC(i.INSERTED_DATE) = TRUNC(SYSDATE))\r\n"
            		+ "    AND i.ID_PENITENCIAR = \r\n"
            		+ " ";
            query = query + numbers[i];
            resultSet = statement.executeQuery(query);

            // Process the result set
            while (resultSet.next()) {
              // Retrieve data from the result set
              data = data + "\n-----ID incident:";
              data = data + resultSet.getString("ID");
              data = data + "-----";
              data = data + "\nNume detinut: " + resultSet.getString("DETINUTI_NAME_SURNAME");
              data = data + "\nPenitenciar: " + resultSet.getString("ID_PENITENCIAR");
              data = data + "\nLoc Detentie: " + resultSet.getString("LOC_DETENTIE");
              data = data + "\nCircumstante: " + resultSet.getString("CIRCUMSTANTE");
              data = data + "\nAsistenta Medicala: " + resultSet.getString("ASISTENTAMEDICALA");
              data = data + "\nConcluzie Medicala: " + resultSet.getString("MEDICAL_CONCLUSION");
              data = data + "\nNume/Prenume Medic: " + resultSet.getString("MEDICAL_STAFF");
              data = data + "\nTip incident: " + resultSet.getString("INCIDENT_TYPE_NAME");
              data = data + "\nInformare drepturi detinut: " + resultSet.getString("DREPTINFORM");
              data = data + "\nData inserare in baza: " + resultSet.getString("INSERTEDDATE");

              // Do something with the data...
              //System.out.println(data);

              //BLOCK EMAIL//

              //END EMAIL BLOCK//
            }
            if (data == "") {
              data = 
"Mesajul este generat de Sistemul informațional. Confirmarea recepționării nu este necesară.


În Sistemul informațional automatizat ”Registrul persoanelor reținute, arestate și condamnate” în ultimile 24 de ore nu au fost introduse date cu privire la leziuni corporale, aplicare a forței fizice, mijloacelor speciale și/sau armei de foc ori a pretinselor acte de rele tratament. 

Informația atașată la prezentul mesaj este expediată în conformitate cu prevederile Ordinului Administrației Naționale a Penitenciarelor nr.150/2024 Cu privire la evidența electronică a cazurilor de leziuni corporale, aplicare a forței fizice, mijloacelor speciale și/sau armei de foc ori a pretinselor acte de rele tratament.  

Prezentul mesaj constituie o informație confidențială și este permisă spre utilizare doar destinatarului. În măsura în care nu sunteți destinatarul vizat, sunteți notificat prin prezenta că orice utilizare, copiere sau distribuire a acestei informații este strict interzisă.
În situația în care ați recepționat acest mesaj din greșeală, vă rugăm să ne notificați imediat prin răspuns la emailul anp.dtisc@anp.gov.md și să ștergeți acest mesaj.

În cazul care nu recepționați zilnic mesaje generate de Sistemul informațional, rugăm să ne notificați la adresa de email anp.dtisc@anp.gov.md";
            }
            
            Email email = new Email(i, data);
            email.sendEmail();
            
            
            
            i++;
          }
        }
      }

    } catch (SQLException | ClassNotFoundException e) {
      e.printStackTrace();
    } 
  }
}