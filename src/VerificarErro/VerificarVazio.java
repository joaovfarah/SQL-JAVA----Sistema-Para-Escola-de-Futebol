package VerificarErro;

public class VerificarVazio {
		public static boolean verificarVazio(String elemento) {
			if(elemento.isBlank()) {
				return false;
			}
			return true;
		}
}
