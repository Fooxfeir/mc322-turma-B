import java.util.ArrayList;

public class teste {
	
	public static void main(String[] args) {
		Obj obj1 = new Obj(" a", 1);
		Obj obj2 = new Obj(" b", 2);
		ArrayList<Obj> lista1 = new ArrayList<Obj>();
		
		lista1.add(obj1);
		lista1.add(obj2);
		Obj aux = null;
		
		for (Obj i : lista1) {
			if (i.getIdade() == 2) {
				aux = i;
				lista1.remove(i);
				break;
			}
		}
		
		for (Obj i : lista1) {
			System.out.println(i);
		}
		System.out.println(aux);

		
		
	}
	
	
}
