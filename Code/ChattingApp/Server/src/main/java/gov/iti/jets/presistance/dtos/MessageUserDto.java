package gov.iti.jets.presistance.dtos;

import gov.iti.jets.common.hibernate.ValidationMaker;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;

import java.util.Date;

public class MessageUserDto {
//    @Positive
    int messageId;
//    @NotNull
//    @NotEmpty
    String toId;
//    @NotNull
//    @NotEmpty
    String fromId;
//    @Pattern(regexp = "^\\d{4}-\\d{2}-\\d{2}$")
    Date date;
//    @NotNull
//    @NotEmpty
    String fontStyle;
//    @NotNull
//    @NotEmpty
    String fontColor;
//    @NotNull
//    @NotEmpty
    String fontWeight;
//    @Positive
    int fontSize;

    public MessageUserDto(){
        //ValidationMaker.getInstance().validate(this);
    }
    public MessageUserDto(int messageId, String toId, String fromId, 
                            Date date, String fontStyle, String fontColor
                            , String fontWeight , int fontSize){
        this.messageId = messageId;
        this.toId = toId;
        this.fromId = fromId;
        this.date = date;
        this.fontStyle = fontStyle;
        this.fontColor = fontColor;
        this.fontWeight = fontWeight;
        this.fontSize = fontSize;
    }

    public void setMessageId(int messageId){
        this.messageId = messageId;
    }
    public int getMessageId(){
        return this.messageId;
    }

    public void setToId(String toId){
        this.toId = toId;
    }
    public String getToId(){
        return this.toId;
    }

    public void setFromId(String fromId){
        this.fromId = fromId;
    }
    public String getFromId() {
        return fromId;
    }
    public Date getDate() {
        return date;
    }
    public void setDate(Date date) {
        this.date = date;
    }
    public String getFontStyle() {
        return fontStyle;
    }
    public void setFontStyle(String fontStyle) {
        this.fontStyle = fontStyle;
    }
    public String getFontColor() {
        return fontColor;
    }
    public void setFontColor(String fontColor) {
        this.fontColor = fontColor;
    }
    public String getFontWeight() {
        return fontWeight;
    }
    public void setFontWeight(String fontWeight) {
        this.fontWeight = fontWeight;
    }
    public int getFontSize() {
        return fontSize;
    }
    public void setFontSize(int fontSize) {
        this.fontSize = fontSize;
    }


}
