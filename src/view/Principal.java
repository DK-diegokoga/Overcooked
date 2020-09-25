package view;

import java.util.concurrent.Semaphore;

import controller.Cozinha;

public class Principal {

	public static void main(String[] args) {
		
		int permissao = 1;
		Semaphore semaforo = new Semaphore(permissao);
		for (int i = 0; i < 5 ; i++) {
			Cozinha cozinha = new Cozinha(i, i, i, semaforo);
			cozinha.start();
		}
	}

}
