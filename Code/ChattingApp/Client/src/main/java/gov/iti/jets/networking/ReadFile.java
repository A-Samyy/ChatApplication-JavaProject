package gov.iti.jets.networking;

import gov.iti.jets.presentation.controllers.ChatSectionController;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;

public class ReadFile extends Thread {
    Socket socket;
   // private static DataOutputStream dataOutputStream = null;
    private static DataInputStream dataInputStream = null;
    ChatSectionController chatSectionController ;
}
