package experiment12_1;

abstract class HandsetBrand {
    protected HandsetSoft handsetSoft;

    public void setHandsetSoft(HandsetSoft handsetSoft){
        this.handsetSoft = handsetSoft;
    }

    public void Run(){
        handsetSoft.Run();
    }
}
