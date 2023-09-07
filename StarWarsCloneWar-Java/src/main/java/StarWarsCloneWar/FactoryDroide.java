package StarWarsCloneWar;

public class FactoryDroide {
    private FactoryDroide(){}
    private static FactoryDroide instance = null;
    public static FactoryDroide getInstance(){
        if(instance == null) instance = new FactoryDroide();
        return instance;
    }

    public Droide createRandomly(){
        Droide droide = null;
        int chance = (int) (Math.random()*100);
        if(chance <= 30){
            droide = new Droide.SW348(50);
        }else{
            if(chance <= 80){
                droide = new Droide.SW497(100);
            }else{
                droide = new Droide.SW4421(((int) (Math.random()*50) + 100));
            }
        }
        return droide;
    }
}
