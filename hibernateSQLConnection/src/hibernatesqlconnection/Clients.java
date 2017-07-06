
package hibernatesqlconnection;


public class Clients {
    private int clients_id = 1;
    private String clients_name;
    private String clients_phone;
    private String clients_mail;

    public Clients() {
    }
    
    

    public Clients(String clients_name, String clients_phone, String clients_mail) {
        this.clients_name = clients_name;
        this.clients_phone = clients_phone;
        this.clients_mail = clients_mail;
    }

    public int getClients_id() {
        return clients_id;
    }

    public void setClients_id(int clients_id) {
        this.clients_id = clients_id;
    }

    public String getClients_name() {
        return clients_name;
    }

    public void setClients_name(String clients_name) {
        this.clients_name = clients_name;
    }

    public String getClients_phone() {
        return clients_phone;
    }

    public void setClients_phone(String clients_phone) {
        this.clients_phone = clients_phone;
    }

    public String getClients_mail() {
        return clients_mail;
    }

    public void setClients_mail(String clients_mail) {
        this.clients_mail = clients_mail;
    }
    
    
}
