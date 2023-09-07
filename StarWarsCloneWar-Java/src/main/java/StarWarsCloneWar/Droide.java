package StarWarsCloneWar;

public sealed class Droide {
    int energia;

    public Droide(int energia) {
        this.energia = energia;
    }

    public int getEnergia() {
        return energia;
    }

    public static final class SW348 extends Droide {
        public SW348(int energia) {
            super(energia);
        }

        @Override
        public String toString() {
            return "SW348{" +
                    "energia=" + energia +
                    '}';
        }
    }

    public static final class SW497 extends Droide {
        public SW497(int energia) {
            super(energia);
        }

        @Override
        public String toString() {
            return "SW497{" +
                    "energia=" + energia +
                    '}';
        }
    }

    public static final class SW4421 extends Droide {
        public SW4421(int energia) {
            super(energia);
        }

        @Override
        public String toString() {
            return "SW4421{" +
                    "energia=" + energia +
                    '}';
        }
    }
}
