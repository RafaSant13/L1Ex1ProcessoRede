package controller;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

public class RedesController {

	public RedesController() {
		super();
	}
	
	private String os(){
		String os = System.getProperty("os.name");
		return os;
	}
	
	public void ip() {
		String process = "";
		String os = os();
		if (os.contains("Windows")) {
			process = "ipconfig";
		}
		else if(os.contains("Linux")) {
			process = "ifconfig";
		}
		try {
			Process ip = Runtime.getRuntime().exec(process);
			InputStream fluxo = ip.getInputStream();
			InputStreamReader leitor = new InputStreamReader(fluxo);
			BufferedReader buffer = new BufferedReader(leitor);
			String linha = buffer.readLine();
			if (os.contains("Windows")) {
				while (linha != null) {
					if (linha.contains("Adaptador")) {
						String [] Adaptador = linha.split(":");
						System.out.println(Adaptador[Adaptador.length-1]+": ");
					}
					if (linha.contains("IPv4.")) {
						String [] IPv = linha.split(":");
						System.out.println(IPv[IPv.length-1]);
					}	
					linha = buffer.readLine();
				}
			}
			else if(os.contains("Linux")) {
				while (linha != null) {
					if (linha.contains("ether ")) {
						String[] Ether = linha.split(" ");
						System.out.println(Ether[Ether.length-1]);
					}					
					if (linha.contains("inet ") && linha.contains("broadcast") ) {
						String[] inet = linha.split(" ");
						System.out.println(inet[9]);
					}
					linha = buffer.readLine();
				}
			}
			
			buffer.close();
			leitor.close();
			fluxo.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
			
	}
	
	public void Ping(){
		String os = os();
		String process = "";
		if (os.contains("Windows")) {
			process = "ping -4 -n 10 www.google.com.br";
		}
		else if(os.contains("Linux")) {
			process = "ping -4 -c 10 www.google.com.br";
		}
		try {
			Process ip = Runtime.getRuntime().exec(process);
			InputStream fluxo = ip.getInputStream();
			InputStreamReader leitor = new InputStreamReader(fluxo);
			BufferedReader buffer = new BufferedReader(leitor);
			String linha = buffer.readLine();
			if (os.contains("Windows")) {
				while (linha != null) {
					if (linha.contains("ms, ")) {
						String [] Media = linha.split("=");
						System.out.println("Média de Ping = "+Media[Media.length-1]);
					}
					linha = buffer.readLine();
				}
			}
			else if(os.contains("Linux")) {
				while (linha != null) {
					if (linha.contains("rtt ")) {
						String[] Rtt = linha.split("/");
						System.out.println("Média de Ping = "+Rtt[4]);
					}
					linha = buffer.readLine();
				}
			}
			
			buffer.close();
			leitor.close();
			fluxo.close();
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println(e);
		}
			
	}
}

