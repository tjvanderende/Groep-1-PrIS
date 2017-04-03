package model;

import java.util.ArrayList;

public abstract class PrISService {
	abstract Klas loadKlas(String klasCode, String klasNaam);
	abstract ArrayList<Les> loadLessen();
	

}
