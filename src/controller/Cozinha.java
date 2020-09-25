package controller;

import java.util.Random;
import java.util.concurrent.Semaphore;

public class Cozinha extends Thread{
	private int IdPrato;
	private String prato;
	private double tempo1;
	private double tempo2;
	private double tempoPreparo;
	private Semaphore semaforo;

	public Cozinha(int IdPrato,double tempo1,double tempo2,Semaphore semaforo) {
		this.semaforo = semaforo;
		this.IdPrato = IdPrato;
		if (IdPrato % 2 == 0) {
			this.prato = "Lasanha a Bolonhesa";
			this.tempo1 = 0.3;
			this.tempo2 = 0.5;
		}else {
			this.prato = "Sopa de Cebola";
			this.tempo1 = 6.0;
			this.tempo2 = 6.0;
		}
	}
		
	@Override
	public void run() {
		Inicio();
		
		try {
			Percentual();
			Entrega();
			semaforo.acquire();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}finally {
			semaforo.release();
		}
		
	}
	
	public void Inicio() {
		System.out.println("Come�ou o preparo do prato " + "#" +IdPrato + " " +  prato);
		Random x = new Random();
		tempoPreparo = ((x.nextDouble()* tempo1) + tempo2);
		try {
			sleep(100);
		}catch(InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public void Percentual(){
		int Percentual = 0;
		do{
			Percentual += (tempoPreparo/0.1);
			if (Percentual > 100) {
				System.out.println("O prato #" + IdPrato + " ja esta " + "100 % pronto");
			}else {
			System.out.println("O prato #" + IdPrato + " ja esta " + Percentual + " % pronto");
			}
		}while(Percentual < 100); 
			try {
			sleep(100);
			}catch(InterruptedException e) {
				e.printStackTrace();
			}
		}
	
	
	public void Entrega() {
		System.out.println("Prato #" + IdPrato + " " +prato +  " pronto!");
		System.out.println("Prato #" + IdPrato + " entregue!");
		try {
			sleep(500);
		}catch(InterruptedException e){
			e.printStackTrace();
		}
	}
}
