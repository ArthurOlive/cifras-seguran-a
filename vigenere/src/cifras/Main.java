package cifras;

public class Main {
    public static void main(String[] args) {
        var textoOriginal = "ATACAREMOSAOAMANHECER";
        var cifra = new CifraVigenere("BICICLETA");
        var textoCifrado = cifra.encriptar(textoOriginal);
        var textoDecriptado = cifra.decriptar(textoCifrado);
        System.out.println("Original: " + textoOriginal);
        System.out.println("Cifrado: " + textoCifrado);
        System.out.println("Decriptado: " + textoDecriptado);
    }
}
