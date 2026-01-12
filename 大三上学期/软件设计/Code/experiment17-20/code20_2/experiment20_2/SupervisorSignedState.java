package experiment20_2;

class SupervisorSignedState implements ReimbursementState{
    @Override
    public void withdrawMoney(Reimbursement reimbursement) {
        System.out.println("Cannot withdraw money, require signatures of Manager.");
    }

    @Override
    public void requestSupervisorSignature(Reimbursement reimbursement) {
        System.out.println("Supervisor's signature is already obtained.");
    }

    @Override
    public void requestManagerSignature(Reimbursement reimbursement) {
        System.out.println("Requesting manager's signature...");
        reimbursement.setReimbursementState(new ManagerSignedState());
    }

    @Override
    public void requestDirectorSignature(Reimbursement reimbursement) {
        System.out.println("Cannot request director's signature. Manager's signature is required first.");
    }
}
