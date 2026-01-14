package experiment20_2;

class Reimbursement {
    private ReimbursementState reimbursementState;
    public Reimbursement(){
        reimbursementState = new NoSignatureState();
    }
    public void setReimbursementState(ReimbursementState reimbursementState){
        this.reimbursementState = reimbursementState;
    }
    public void withdrawMoney(){
        reimbursementState.withdrawMoney(this);
    }
    public void requestSupervisorSignature(){
        reimbursementState.requestSupervisorSignature(this);
    }
    public void requestManagerSignature(){
        reimbursementState.requestManagerSignature(this);
    }
    public void requestDirectorSignature(){
        reimbursementState.requestDirectorSignature(this);
    }

}
