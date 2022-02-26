//package gov.iti.jets.service.Impl;
//
//
//import gov.iti.jets.common.dtos.MessageGroupDto;
//import gov.iti.jets.common.interfaces.ClientMesseageInt;
//import gov.iti.jets.common.interfaces.ServerMessageGroupInt;
//import gov.iti.jets.presistance.daos.GroupChatUsersDao;
//import gov.iti.jets.presistance.daos.UserDao;
//import gov.iti.jets.presistance.dtos.GroupChatDto;
//import gov.iti.jets.presistance.dtos.UserDto;
//
//import java.rmi.RemoteException;
//import java.rmi.server.UnicastRemoteObject;
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//public class ServerMessageGroupImpl extends UnicastRemoteObject implements ServerMessageGroupInt {
//
//    //map<groupId , List Of clients in this group>>
//    Map<Integer, List<ClientMesseageInt>> groupClients = new HashMap<>();
//
//    protected ServerMessageGroupImpl() throws RemoteException {
//    }
//
//    @Override
//    public boolean getMesssage(MessageGroupDto messageGroupDto) throws RemoteException {
//        if (groupClients.containsKey(messageGroupDto.getGroupId()) && (groupClients.get(messageGroupDto.getGroupId()) != null)) {
//            sendMessage(messageGroupDto);
//            return true;
//        } else
//            return false;
//    }
//
//    @Override
//    public boolean fillGroupInfo(int groupId) throws RemoteException {
//
//        GroupChatUsersDao groupChatUsersDao = new GroupChatUsersDao();
//        List<Integer> groupChatUserList = new ArrayList<>();
//        groupChatUserList= groupChatUsersDao.getAllUsersIdFromGroupId(groupId);
//     //   groupClients.put(groupId , groupChatUserList);
//        return false;
//    }
//
//
//    private void sendMessage(MessageGroupDto messageGroupDto) {
//        for (ClientMesseageInt clientMesseageInt : groupClients.get(messageGroupDto.getGroupId())) {
//            try {
//                clientMesseageInt.reciveGroupMessage(messageGroupDto);
//            } catch (RemoteException e) {
//                e.printStackTrace();
//            }
//        }
//   }
//}
