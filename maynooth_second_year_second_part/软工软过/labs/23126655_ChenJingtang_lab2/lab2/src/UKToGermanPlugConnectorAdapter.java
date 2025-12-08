public class UKToGermanPlugConnectorAdapter implements GermanPlugConnector {

    private UKPlugConnector plug;


    public UKToGermanPlugConnectorAdapter(UKPlugConnector plug) {
        this.plug = plug;
    }


    @Override
    public void giveElectricity() {

        plug.provideElectricity();
    }
}