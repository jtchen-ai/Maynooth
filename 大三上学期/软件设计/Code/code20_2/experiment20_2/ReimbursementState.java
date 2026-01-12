package experiment20_2;

interface ReimbursementState {
    void withdrawMoney(Reimbursement reimbursement);
    void requestSupervisorSignature(Reimbursement reimbursement);
    void requestManagerSignature(Reimbursement reimbursement);
    void requestDirectorSignature(Reimbursement reimbursement);
}
