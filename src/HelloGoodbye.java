
public class HelloGoodbye {
    public static void main(String[] args) {
        String firstName = args[0];
        String secondName = args[1];
        System.out.println(String.format("Hello %s and %s.",firstName,secondName));
        System.out.println(String.format("Goodbye %s and %s.",secondName,firstName));

    }
}
