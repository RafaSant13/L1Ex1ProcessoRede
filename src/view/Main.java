package view;
import javax.swing.JOptionPane;

import controller.RedesController;

public class Main {

	public static void main(String[] args) {
		RedesController rc = new RedesController();
		int X =0;
		while (X!=9){
			X = Integer.parseInt(JOptionPane.showInputDialog("MENU\n1 - Verificar Endereços de IP\n2 - Checar Ping Médio\n9 - FIM"));
			switch(X){
				case 1: rc.ip();
					break;
				case 2: rc.Ping();
					break;
				case 9: System.exit(0);
				default: JOptionPane.showMessageDialog(null, "Valor Inválido");
		}
		}
	}
}
