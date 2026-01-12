package experiment20_2;

class NoSignatureState implements ReimbursementState{
    @Override
    public void withdrawMoney(Reimbursement reimbursement) {
        System.out.println("Cannot withdraw money, require signatures of Supervisor.");
    }

    @Override
    public void requestSupervisorSignature(Reimbursement reimbursement) {
        System.out.println("Requesting supervisor's signature...");
        reimbursement.setReimbursementState(new SupervisorSignedState());
    }

    @Override
    public void requestManagerSignature(Reimbursement reimbursement) {
        System.out.println("Cannot request manager's signature. Supervisor's signature is required first.");
    }

    @Override
    public void requestDirectorSignature(Reimbursement reimbursement) {
        System.out.println("Cannot request director's signature. Supervisor's signature is required first.");
    }
}
