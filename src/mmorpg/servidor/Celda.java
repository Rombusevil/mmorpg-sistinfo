package mmorpg.servidor;

public interface Celda {
	Celda dameCeldaArriba();
	Celda dameCeldaAbajo();
	Celda dameCeldaIz();
	Celda dameCeldaDer();
	void setCeldaArriba(Celda c);
	void setCeldaAbajo(Celda c);
	void setCeldaIz(Celda c);
	void setCeldaDer(Celda c);
	void setEstado(Estado e);
	Estado getEstado();
	Celda getCelda();
	void setMyPos(int x, int y);
	void imprimeMyPos();
	ProveedorEstados getProveedorEstados();
	Estado getEstadoLibre();
	Estado getEstadoInaccesible();
	Dibujo getDibujo();
	ImpDibujoVacio getVacio();
	void setVacio(ImpDibujoVacio vacio);
	void setDibujo(Dibujo dibujo);
	void imprimite();
	void setProveedorEstados(ProveedorEstados pe);
}
