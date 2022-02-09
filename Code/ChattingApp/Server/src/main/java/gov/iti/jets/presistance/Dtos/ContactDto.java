package gov.iti.jets.presistance.Dtos;

public class ContactDto {
    String userId;
    String contactId;

    public ContactDto(){}
    public ContactDto(String userId, String contactId){
        this.userId = userId;
        this.contactId = contactId;
    }

    public void setUserId(String userId){
        this.userId = userId;
    }
    public String getUserId(){
        return this.userId;
    }

    public void setContactId(String contactId){
        this.contactId = contactId;
    }
    public String getContactId(){
        return this.userId;
    }
}
