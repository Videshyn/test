
package sqlitehibernatexmlconnection;

public class Departments {
    
    private int departments_id = 1;
    private String departments_name;
    private String region_info;

    public Departments() {
    }

    public Departments(String departments_name, String region_info) {
        this.departments_name = departments_name;
        this.region_info = region_info;
    }

    public int getDepartments_id() {
        return departments_id;
    }

    public void setDepartments_id(int departments_id) {
        this.departments_id = departments_id;
    }


    public String getDepartments_name() {
        return departments_name;
    }

    public void setDepartments_name(String departments_name) {
        this.departments_name = departments_name;
    }

    public String getRegion_info() {
        return region_info;
    }

    public void setRegion_info(String region_info) {
        this.region_info = region_info;
    }
    
    
    
}
