package br.pro.hashi.ensino.desagil.lucianogic.model;

public class FullGate extends Gate {
	private XorGate xorLeft;
	private XorGate xorRight;
	private AndGate andTop;
	private AndGate andBottom;
	private OrGate orGate;

	public FullGate() {
		super(3, 2);

		name = "FULL";

		xorLeft = new XorGate();
		xorRight = new XorGate();
		andTop = new AndGate();
		andBottom = new AndGate();
		orGate = new OrGate();
		
		orGate.connect(andTop, 0);
		orGate.connect(andBottom, 1);
		
		xorRight.connect(xorLeft, 0);
		
		andTop.connect(xorLeft, 0);
	}

	@Override
	public boolean doRead(int index) {
		if(index == 0){
			return xorRight.read();
		}else{
			return orGate.read();
		}
	}

	@Override
	protected void doConnect(Emitter emitter, int index) {
		if(index==0){
			xorLeft.connect(emitter, 0);
			andBottom.connect(emitter, 0);

		}
		if(index==1){
			xorLeft.connect(emitter, 1);
			andBottom.connect(emitter, 1);
			
		}
		if(index==2){
			xorRight.connect(emitter, 1);
			andTop.connect(emitter, 1);
		}
	}
}
