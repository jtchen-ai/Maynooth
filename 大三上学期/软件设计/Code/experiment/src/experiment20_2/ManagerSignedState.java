package experiment20_2;

class ManagerSignedState implements ReimbursementState{
    @Override
    public void withdrawMoney(Reimbursement reimbursement) {
        System.out.println("Cannot withdraw money, require signatures of Director.");
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
        System.out.println("Requesting director's signature...");
        reimbursement.setReimbursementState(new DirectorSignedState());
    }
}
