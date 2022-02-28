package gov.iti.jets.presentation.controllers;

import gov.iti.jets.common.dtos.FileRequestDto;
import gov.iti.jets.service.impl.ClientFileRequestImpl;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;

import java.rmi.RemoteException;

public class FileRequestController {
    FileRequestDto fileRequestDto = new FileRequestDto();

    @FXML
    private Label FileRequestName;

    @FXML
    void OnAccept(MouseEvent event) {
        try {
            ClientFileRequestImpl clientFileRequest = new ClientFileRequestImpl();
            clientFileRequest.sendResponseRequest(fileRequestDto);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void OnReject(MouseEvent event) {
        ClientFileRequestImpl.fileRequestDtos.remove(fileRequestDto);
    }

    public void dispalyFileReq(FileRequestDto fileRequestDto) {
        FileRequestName.setText(fileRequestDto.getFileName());
        this.fileRequestDto = fileRequestDto;
    }
}
