package gov.iti.jets.service.services;

import gov.iti.jets.presistance.daos.AdminDao;
import gov.iti.jets.presistance.dtos.AdminDto;

public class LoginAdminService {
    AdminDao adminDao = new AdminDao();
    boolean checkId;
    boolean checkPassword;
    public LoginAdminService() {
    }

    public boolean checkAdminLogin(AdminDto adminDto) {
        checkId = checkId(adminDto.getAdminID());
        if (checkId) {
            checkPassword = checkPassword(adminDto);
            return checkPassword;
        } else {
            return false;
        }
    }

    private boolean checkId(int id) {
        return adminDao.checkAdminId(id);
    }

    private boolean checkPassword(AdminDto adminDto) {
        return adminDao.checkAdminPassword(adminDto);

    }

}
