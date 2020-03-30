package logica;

import vista.Modelo;

public class Juego {
		int ganador=0;
		
		public Juego(){
			
		}
	
		public int RevisarEstado(int juego[][]) {
			
			for(int i=0;i<3;i++)
			{
				if((juego[i][0]== 1 && juego[i][1]==1 && juego[i][2]==1) || juego[i][0]== 2 && juego[i][1]==2 && juego[i][2]==2 ) {
					//revisa horizontalmente alg�n ganador y asigna a ganador el valor de la casilla
					ganador=juego[i][0];
					break;
				}
			}
			
			for(int i=0;i<3;i++)
			{
				if((juego[0][i]== 1 && juego[1][i]==1 && juego[2][i]==1) || juego[0][i]== 2 && juego[1][i]==2 && juego[2][i]==2 ) {
					//revisa verticalmente alg�n ganador y asigna a ganador el valor de la casilla
					ganador=juego[0][i];
					break;
				}
			}
			
			if((juego[0][0]==1 &&juego[1][1]==1 && juego[2][2]==1)|| (juego[0][0]==2 &&juego[1][1]==2 && juego[2][2]==2)) {
				//revisa diagonalmente alg�n ganador
				ganador=juego[0][0];
			}
			
			if((juego[0][2]==1 &&juego[1][1]==1 && juego[2][0]==1)|| (juego[0][2]==2 &&juego[1][1]==2 && juego[2][0]==2)) {
				//revisa diagonalmente alg�n ganador
				ganador=juego[0][2];
			}
			
			
		return ganador;
		}
		
	
		
}
