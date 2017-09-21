package halo;

public class HaloMain {

    public HaloMain() {
        System.out.println("in run manet");
        new GetPC();
        System.out.println("In run tempserver");
        new tempserver().tserver();
    }

    public static void main(String args[]) {
        HaloMain mmf = new HaloMain();
    }
}
