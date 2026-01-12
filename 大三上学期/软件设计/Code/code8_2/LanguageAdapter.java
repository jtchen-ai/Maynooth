package experiment8_2;

class LanguageAdapter implements SpeakEnglish{
    private SpeakChinese chineseSpeaker;

    public LanguageAdapter(SpeakChinese chineseSpeaker){
        this.chineseSpeaker = chineseSpeaker;
    }

    @Override
    public void speakEnglish() {
        chineseSpeaker.speakChinese();
        System.out.println("Translating Chinese to English by adapter: Hello");
    }
}
