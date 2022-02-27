package gov.iti.jets.presistance.dtos;

import gov.iti.jets.common.hibernate.ValidationMaker;
import jakarta.validation.constraints.Positive;

public enum Status {
    ACTIVE(1),
    DoNotDisturb(2),
    AWAY(3),
    OFFLINE(4);
//    @Positive
    final int number;
    Status(int number){
     //   ValidationMaker.getInstance().validate(this);
        this.number=number;
    }
    public static Status getStatusFromNumber(int num) {
        for (Status e : Status.values()) {
            if (e.number==num) {
                return e;
            }
        }
        return null;
    }

}
