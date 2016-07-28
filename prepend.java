import com.cycling74.max.*;

public class prepend extends MaxObject
{
	public Atom[] stuff = null;
	public Atom[] prepend = null;

	public prepend(Atom[] args){
		//declaring inlets
		declareInlets(new int[]{DataTypes.ALL, DataTypes.ALL});
		declareOutlets(new int[]{DataTypes.ALL});
		createInfoOutlet(false);
		//help messages
		setInletAssist(new String[]{"Message to prepend", "Stored message"});
		setOutletAssist(new String[]{"Prepended message"});
	}

	//concat function
	public Atom[] concat(Atom[] a, Atom[] b){
		//make sure we don't get a nullpointerexception
		if(b==null){
			return a;
		}
		int aLen = a.length;
		int bLen = b.length;
		Atom[] c = new Atom[aLen+bLen];
		System.arraycopy(a, 0, c, 0, aLen);
		System.arraycopy(b, 0, c, aLen, bLen);
		return c;
	}
	//add second inlet messages to stuff
	private void setStuff(Atom[] a){
		stuff = a;
	}
	//add first inlet messages to prepend
	private void setPrepend(Atom[] a){
		prepend = a;
	}
	//add's int's to prepend or stuff if prepend outputs the complete message
	public void inlet(int i){
		if(getInlet()==0){
			setPrepend(new Atom[] {Atom.newAtom(i)});
			outlet(0, concat(prepend, stuff));
		}else{
			setStuff(new Atom[] {Atom.newAtom(i)});
		}	
	}
    //add's float's to prepend or stuff if prepend outputs the complete message
	public void inlet(float f){
		if(getInlet()==0){
			setPrepend(new Atom[] {Atom.newAtom(f)});
			outlet(0, concat(prepend, stuff));
		}else{
			setStuff(new Atom[] {Atom.newAtom(f)});
		}

	}
	//add lists to prepend or stuff if prepend outputs the complete message
	public void list(Atom[] a){
		if(getInlet()==0){
			setPrepend(a);
			outlet(0, concat(prepend, stuff));
		}else{
			setStuff(a);
		}
	}
	//add's anything else to prepend or stuff if prepend outputs the complete message
	public void anything(String msg, Atom[] a){
		if(getInlet()==0){
			setPrepend(Atom.newAtom(msg, a));
			outlet(0, concat(prepend, stuff));
		}else{
			setStuff(Atom.newAtom(msg, a));
		}			
	}
}











