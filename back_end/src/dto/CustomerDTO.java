package dto;

/**
 * @author : D.D.Sandaruwan <dulanjayasandaruwan1998@gmail.com>
 * @Since : 19/05/2022
 **/
public class CustomerDTO {
    private String CustId;
    private String CustName;
    private String CustAddress;
    private String Contact;

    public CustomerDTO() {
    }

    public CustomerDTO(String custId, String custName, String custAddress, String contact) {
        CustId = custId;
        CustName = custName;
        CustAddress = custAddress;
        Contact = contact;
    }

    public String getCustId() {
        return CustId;
    }

    public void setCustId(String custId) {
        CustId = custId;
    }

    public String getCustName() {
        return CustName;
    }

    public void setCustName(String custName) {
        CustName = custName;
    }

    public String getCustAddress() {
        return CustAddress;
    }

    public void setCustAddress(String custAddress) {
        CustAddress = custAddress;
    }

    public String getContact() {
        return Contact;
    }

    public void setContact(String contact) {
        Contact = contact;
    }

    @Override
    public String toString() {
        return "CustomerDTO{" +
                "CustId='" + CustId + '\'' +
                ", CustName='" + CustName + '\'' +
                ", CustAddress='" + CustAddress + '\'' +
                ", Contact='" + Contact + '\'' +
                '}';
    }
}
