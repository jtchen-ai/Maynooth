package experiment20_2;

public class Client {
    public static void main(String[] args) {
        Reimbursement reimbursement = new Reimbursement();
        reimbursement.requestSupervisorSignature();
        reimbursement.requestManagerSignature();
        reimbursement.requestDirectorSignature();
        reimbursement.withdrawMoney();
    }
}
