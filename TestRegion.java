import java.util.*;
import java.util.ArrayList;

public class TestRegion {

	public static void main (String[] args) {
		Monde R = new Monde();
		Virus V = new Virus(0.60, 7, 0.05, "vivi");
				System.out.println();

		R.ToString();
	for(int i =0; i < 40; i++){
		System.out.println();
		R.majPropaPays(V);
		R.deplacements();
		R.ToString();

		}
	}
}

