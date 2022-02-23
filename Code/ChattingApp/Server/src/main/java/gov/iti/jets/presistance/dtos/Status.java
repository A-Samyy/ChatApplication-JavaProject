package gov.iti.jets.presistance.dtos;

public enum Status {
    ACTIVE(1),
    DoNotDisturb(2),
    AWAY(3);
    final int number;
    Status(int number){
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
