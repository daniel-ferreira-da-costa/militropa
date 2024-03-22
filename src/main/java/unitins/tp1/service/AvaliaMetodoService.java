package unitins.tp1.service;

import unitins.tp1.model.TipoArma;

public class AvaliaMetodoService {
    public static void main(String[] args) {
        for (int i = 1; i <=6; i++) {
            System.err.println(TipoArma.valueOf(i));
        }
    }
}
