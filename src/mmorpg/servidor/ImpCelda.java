package mmorpg.servidor;

public class ImpCelda implements Celda {
	private Estado estado;
	private Celda celdaArriba;
	private Celda celdaAbajo;
	private Celda celdaIz;
	private Celda celdaDer;
	private int posX;
	private int posY;
	private Dibujo dibujo;
	private ImpDibujoVacio vacio;
	
	
	public ImpDibujoVacio getVacio() {
		return vacio;
	}
	public void setVacio(ImpDibujoVacio vacio) {
		this.vacio = vacio;
	}

	private ProveedorEstados proveedorEstados;
	
	public ProveedorEstados getProveedorEstados(){
		return this.proveedorEstados;
	}
	public void setProveedorEstados(ProveedorEstados pe){
		this.proveedorEstados = pe;
	}
	
	public Estado getEstadoLibre(){
		return this.getProveedorEstados().getLibre();
	}
	public Estado getEstadoInaccesible(){
		return this.getProveedorEstados().getInaccesible();
	}

	@Override
	public Celda dameCeldaArriba() {
		return celdaArriba;
	}

	@Override
	public Celda dameCeldaAbajo() {
		return celdaAbajo;
	}

	@Override
	public Celda dameCeldaIz() {
		return celdaIz;
	}

	@Override
	public Celda dameCeldaDer() {
		return celdaDer;
	}

	@Override
	public void setCeldaArriba(Celda c) {
		this.celdaArriba = c;
	}

	@Override
	public void setCeldaAbajo(Celda c) {
		celdaAbajo = c;
		
	}

	@Override
	public void setCeldaIz(Celda c) {
		celdaIz = c;
	}

	@Override
	public void setCeldaDer(Celda c) {
		celdaDer = c;
		
	}
	@Override
	public void setMyPos(int x, int y) {
		this.posX = x;
		this.posY = y;
		
	}
	@Override
	public void imprimeMyPos() {
		System.out.print("["+this.posX+","+this.posY);
		this.getDibujo().imprimeDibujo();
		System.out.print("]");
	}
	@Override
	public void setEstado(Estado e) {
		this.estado = e;
	}
	public Estado getEstado(){
		return this.estado;
	}
	
	public Celda getCelda(){ /*?*/
		return this;
	}

	@Override
	public Dibujo getDibujo() {		
		return this.dibujo;
	}
	
	public void setDibujo(Dibujo dibujo){
		this.dibujo = dibujo;
	}

	@Override
	public void imprimite() {
		this.getDibujo().imprimeDibujo();
	}

}
