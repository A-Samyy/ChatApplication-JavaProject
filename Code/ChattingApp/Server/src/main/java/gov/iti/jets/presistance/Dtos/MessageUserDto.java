package gov.iti.jets.presistance.Dtos;

import java.util.Date;

public class MessageUserDto {
    int messageId;
    String toId;
    String fromId;
    Date date;
    String fontStyle;
    String fontColor;
    String fontWeight;
    int fontSize;

    public MessageUserDto(){}
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
