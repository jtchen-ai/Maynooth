package experiment20_2;

class DirectorSignedState implements ReimbursementState{
    @Override
    public void withdrawMoney(Reimbursement reimbursement) {
        System.out.println("Withdraw money successfully.");
    }

    @Override
    public void requestSupervisorSignature(Reimbursement reimbursement) {
        System.out.println("Supervisor's signature is already obtained.");
    }

    @Override
    public void requestManagerSignature(Reimbursement reimbursement) {
        System.out.println("Manager's signature is already obtained.");
    }

    @Override
    public void requestDirectorSignature(Reimbursement reimbursement) {
        System.out.println("Director's signature is already obtained.");
    }
}
