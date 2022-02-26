package gov.iti.jets.common.dtos;

import java.io.Serializable;

public class FileRequestDto implements Serializable {

    private static final long serialVersionUID = 1427672609912364171L;
    private int senderId ;
    private int receiverId;
    private String fileName;

    public FileRequestDto(){
    }

    public void setSenderId(int senderId) {
        this.senderId = senderId;
    }

    public void setReceiverId(int receiverId) {
        this.receiverId = receiverId;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public int getSenderId() {
        return senderId;
    }

    public int getReceiverId() {
        return receiverId;
    }

    public String getFileName() {
        return fileName;
    }

    @Override
    public String toString() {
        return "FileRequestDto{" +
                "senderId=" + senderId +
                ", receiverId=" + receiverId +
                ", fileName='" + fileName + '\'' +
                '}';
    }
}
