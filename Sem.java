package Tarea;


//AQUI SE QUEDA SI SIRVE YA NO SE LE MUEVE 
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class Sem {
//	int a , a , c; c = b + a;

	static BufferedReader entra = new BufferedReader(new InputStreamReader(System.in));
	String cad = "";
	Stack<String> pila = new Stack<String>();
	int j = 0;
	int columna = 0;
	int fila = 0;
	String estado = " ", nvaFila = " ";
	int edo = 0, h = 0;
	String nvoEdo = "";
	String Texto[];
	Map<String, String> produccion_eliminar = new HashMap<String, String>();
	Map<String, String> produccion = new HashMap<String, String>();

//	Stack<String[]> variables = new Stack<String[]>();
//	Map<String, Declaracion> producciona = new HashMap<String, Declaracion>();
//	Map<String, String[]> produccionb = new HashMap<String, String[]>();

	Map<String, String> variables = new HashMap<String, String>();
	Stack<String> pila_semantica = new Stack<String>();

	String igualacion = "=";
	String[] tipos_datos = { "int", "float", "char" };

	String encabezado[] = { "id", "int", "float", "char", ",", ";", "+", "-", "*", "/", "(", ")", "$", "P", "Tipo", "V",
			"A", "Exp", "T", "F", "=", "E", "Term" };

	String tabla[][] = {
			{ "I7", "I4", "I5", "I6", "", "", "", "", "", "", "", "", "", "I1", "I2", "", "I3", "", "", "", "", "",
					"" }, // 0
			{ "", "", "", "", "", "", "", "", "", "", "", "", "P0", "", "", "", "", "", "", "", "", "", "" }, // 1 solo
																												// en
																												// este
																												// va P0
			{ "I8", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "" }, // 2
			{ "", "", "", "", "", "", "", "", "", "", "", "", "P2", "", "", "", "", "", "", "", "", "", "" }, // 3
			{ "P3", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "" }, // 4
			{ "P4", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "" }, // 5
			{ "P5", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "" }, // 6
			{ "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "I9", "", "" }, // 7
			{ "", "", "", "", "I11", "I12", "", "", "", "", "", "", "", "", "", "I10", "", "", "", "", "", "", "" }, // 8
			{ "I16", "", "", "", "", "", "", "", "", "", "I17", "", "", "", "", "", "", "I13", "", "I15", "", "",
					"I14" }, // 9
			{ "", "", "", "", "", "", "", "", "", "", "", "", "P1", "", "", "", "", "", "", "", "", "", "" }, // 10
			{ "I18", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "" }, // 11
			{ "I7", "I4", "I5", "I6", "", "", "", "", "", "", "", "", "", "I19", "I2", "", "I3", "", "", "", "", "",
					"" }, // 12
			{ "", "", "", "", "", "", "", "", "", "", "", "", "P8", "", "", "", "", "", "", "", "", "", "" }, // 13
			{ "", "", "", "", "", "", "I21", "I22", "", "", "", "P12", "P12", "", "", "", "", "", "", "", "", "I20",
					"" }, // 14
			{ "", "", "", "", "", "", "P16", "P16", "I24", "I25", "", "P16", "P16", "", "", "", "", "", "I23", "", "",
					"", "" }, // 15
			{ "", "", "", "", "", "", "P17", "P17", "P17", "P17", "", "P17", "P17", "", "", "", "", "", "", "", "", "",
					"" }, // 16
			{ "I16", "", "", "", "", "", "", "", "", "", "", "I17", "", "", "", "", "", "I26", "", "I15", "", "",
					"I14" }, // 17
			{ "", "", "", "", "I11", "I12", "", "", "", "", "", "", "", "", "", "I27", "", "", "", "", "", "", "" }, // 18
			{ "", "", "", "", "", "", "", "", "", "", "", "", "P7", "", "", "", "", "", "", "", "", "", "" }, // 19
			{ "", "", "", "", "", "", "", "", "", "", "", "P9", "P9", "", "", "", "", "", "", "", "", "", "" }, // 20
			{ "I16", "", "", "", "", "", "", "", "", "", "I17", "", "", "", "", "", "", "", "", "I15", "", "", "I28" }, // 21
			{ "", "", "", "", "", "", "", "I29", "I24", "I25", "", "P16", "P16", "", "", "", "", "", "I23", "", "", "",
					"I28" }, // 22
			{ "", "", "", "", "", "", "P13", "P13", "", "", "", "P13", "P13", "", "", "", "", "", "", "", "", "", "" }, // 23
			{ "I16", "", "", "", "", "", "", "", "", "", "I17", "", "", "", "", "", "", "", "", "I30", "", "", "" }, // 24
			{ "I16", "", "", "", "", "", "", "", "", "", "I17", "", "", "", "", "", "", "", "", "I31", "", "", "" }, // 25
			{ "", "", "", "", "", "", "", "", "", "", "", "I32", "", "", "", "", "", "", "", "", "", "", "" }, // 26
			{ "", "", "", "", "", "", "", "", "", "", "", "", "P6", "", "", "", "", "", "", "", "", "", "" }, // 27
			{ "", "", "", "", "", "", "I21", "I22", "", "", "", "P12", "P12", "", "", "", "", "", "", "", "", "I33",
					"" }, // 28
			{ "", "", "", "", "", "", "I21", "I22", "", "", "", "P12", "P12", "", "", "", "", "", "", "", "", "I34",
					"" }, // 29
			{ "", "", "", "", "", "", "P16", "P16", "I24", "I25", "", "P16", "P16", "", "", "", "", "", "I35", "", "",
					"", "" }, // 30
			{ "", "", "", "", "", "", "P16", "P16", "I24", "I25", "", "P16", "P16", "", "", "", "", "", "I36", "", "",
					"", "" }, // 31
			{ "", "", "", "", "", "P0", "P18", "P18", "P18", "P18", "", "P18", "P18", "", "", "", "", "", "", "", "",
					"", "" }, // 32
			{ "", "", "", "", "", "", "", "", "", "", "", "P10", "P10", "", "", "", "", "", "", "", "", "", "" }, // 33
			{ "", "", "", "", "", "", "", "", "", "", "", "P11", "P11", "", "", "", "", "", "", "", "", "", "" }, // 34
			{ "", "", "", "", "", "", "P14", "P14", "", "", "", "P14", "P14", "", "", "", "", "", "", "", "", "", "" }, // 35
			{ "", "", "", "", "", "", "P15", "P15", "", "", "", "P15", "P15", "", "", "", "", "", "", "", "", "", "" }, // 36
	};

	public Sem() {
		produccion_eliminar.put("P0", "P");
		produccion_eliminar.put("P1", "Tipo");
		produccion_eliminar.put("P2", "A");
		produccion_eliminar.put("P3", "int");
		produccion_eliminar.put("P4", "float");
		produccion_eliminar.put("P5", "char");
		produccion_eliminar.put("P6", ",");
		produccion_eliminar.put("P7", ";");
		produccion_eliminar.put("P8", "id");
		produccion_eliminar.put("P9", "Term");
		produccion_eliminar.put("P10", "+");
		produccion_eliminar.put("P11", "-");
		produccion_eliminar.put("P12", "meter");
		produccion_eliminar.put("P13", "F");
		produccion_eliminar.put("P14", "*");
		produccion_eliminar.put("P15", "/");
		produccion_eliminar.put("P16", "meter");
		produccion_eliminar.put("P17", "id");
		produccion_eliminar.put("P18", "(");

		produccion.put("P0", "P'");
		produccion.put("P1", "P");
		produccion.put("P2", "P");
		produccion.put("P3", "Tipo");
		produccion.put("P4", "Tipo");
		produccion.put("P5", "Tipo");
		produccion.put("P6", "V");
		produccion.put("P7", "V");
		produccion.put("P8", "A");
		produccion.put("P9", "Exp");
		produccion.put("P10", "E");
		produccion.put("P11", "E");
		produccion.put("P12", "E");
		produccion.put("P13", "Term");
		produccion.put("P14", "T");
		produccion.put("P15", "T");
		produccion.put("P16", "T");
		produccion.put("P17", "F");
		produccion.put("P18", "F");
	}

	public void leer() {
		System.out.println("Escribe la entrada");
		try {
		cad = entra.readLine();
//		cad = "int a , c , b , h ; b = a + ( c * h )";
//		cad = "int id ; id = id + ( id * id ) ;";
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		cad += " $";
	}

	int com = 0;
	String entrada = "";

	public void Analizar() {
		pila.push("$");
		pila.push("I0");
		Texto = cad.split(" ");
		boolean comp = false;
		for (int i = 0; i < Texto.length; i++) {
			entrada = Texto[i];
			entrada = A_Lexico(entrada);

			j = 0;
			comp = false;
			do {

				if (entrada.equals(encabezado[j])) {
					columna = j;
//					print("Encontro id" + j);
					comp = true;
				} else
					j++;
			} while (comp == false && j < encabezado.length);
			if (comp == true) {
				com = i;
				estado = tabla[fila][columna];
				System.out.println("estado : " + columna);
				Acciones(estado);
			} else {
//				if (Variable(entrada)) {
//					print("es una variable");
//					com = i;
//					estado = tabla[fila][0];
//					Acciones(estado);
//				} else {
				System.out.println("Error en: " + entrada + " No forma parte de la gramatica");
				System.exit(0);
//				}
			}

		}

		System.out.println(estado);

	}

	public void Acciones(String edo) {

//		print(edo);
		if (edo.charAt(0) == 'I') {
//			System.out.println("Estado I: " + edo);
			pila.push(entrada);
			pila.push(edo);
			nvoEdo = estado.replace("I", "");
			fila = Integer.parseInt(nvoEdo);
			estado = tabla[fila][columna];
			System.out.println(pila);
		} else if (edo.charAt(0) == 'P') {
			if (edo.equalsIgnoreCase("P0")) {
				System.out.println("Se encontro P0 y se acepta la cadena");
				System.exit(0);
			}
//			System.out.println("Produccion P: " + edo);
			if (edo.equalsIgnoreCase("P12") || edo.equalsIgnoreCase("P16")) {

			} else {
				do {

					pila.pop();
				} while (!pila.peek().equalsIgnoreCase(produccion_eliminar.get(edo)));
				pila.pop();
			}

			nvoEdo = pila.peek();
//			System.out.println("Estado "+nvoEdo);
			pila.push(produccion.get(edo));

			nvoEdo = nvoEdo.replace("I", "");

			fila = Integer.parseInt(nvoEdo);

			columna = Arrays.asList(encabezado).indexOf(produccion.get(edo));

			estado = tabla[fila][columna];

			pila.push(estado);
			nvaFila = pila.peek();

			nvaFila = estado.replace("I", "");
//			print(Texto[com]);
			for (int t = 0; t < encabezado.length; t++) {
				if (entrada.equals(encabezado[t])) {
					columna = t;
				}
			}

			fila = Integer.parseInt(nvaFila);
			estado = tabla[fila][columna];
//			print(fila+":"+columna);
			if (estado.charAt(0) == 'I') {
				nvaFila = estado.replace("I", "");
			} else {
				nvaFila = estado.replace("P", "");
			}
			Acciones(estado);
			// fila=Integer.parseInt(nvaFila);
			System.out.println(pila);
		}
//			switch (edo)// 1
//			{
//			case "":
//				System.out.println("Error");
//				System.exit(0);
//				break;
//			case "P0":
//				// P'->P
//				System.out.println("Se encontro P0 y se acepta la cadena");
//				System.exit(0);
//				break;
//			
//			}

	}

	boolean ban_asignacion = false;
	String tipo_actual = "";

	public String A_Lexico(String lexema) {
		System.out.println(lexema);// a
		if (Buscar(tipos_datos, lexema)) {
			tipo_actual = lexema;
			return lexema;
		} else if (lexema.equalsIgnoreCase(";")) {
			tipo_actual = "";
			if (ban_asignacion) {
				if (AnalizarPilaSemantica())
					System.out.println("pila aceptada");
				ban_asignacion = false;
			}
			return lexema;
		} else if (Variable(lexema)) {
//			print("entro a variable");
			if (!tipo_actual.isEmpty()) {
				// declarar variables
				// putos ; me tienen hasta la verga
				try {
					if (!variables.get(lexema).isEmpty()) {// variable ya existe
						System.out.println("la variable " + lexema + " ya está declarada");
						return "error";
					} else {
						// declara variable
						variables.put(lexema, tipo_actual);
						return "id";
					}
				} catch (Exception e) {
					// declara variable
//					print("entro a variable");
					variables.put(lexema, tipo_actual);
					return "id";
				}
//				return "id";
			} else {
				// empezar a hacer una asignacion
				pila_semantica.push(variables.get(lexema));
				return "id";
			}
		} else if (lexema.equalsIgnoreCase("=")) {
			System.out.println("encontre un igual");
			ban_asignacion = true;
		}
		return lexema;
	}

	public boolean AnalizarPilaSemantica() {
		System.out.println(pila_semantica + "");
		do {
			String tipo1 = pila_semantica.pop();
			String tipo2 = pila_semantica.pop();

			if (tipo1.equalsIgnoreCase("char") || tipo2.equalsIgnoreCase("char"))
				return false;
			else if (tipo1.equalsIgnoreCase(tipo2)) {
				pila_semantica.push(tipo1);
			} else
				return false;
			System.out.println(pila_semantica + "");
		} while (pila_semantica.size() > 2);

		return true;
	}

	public boolean Buscar(String[] vec, String var) {
		for (int i = 0; i < vec.length; i++) {
			if (vec[i].equalsIgnoreCase(var)) {
				return true;
			}
		}
		return false;
	}

	String var = "[A-z]+[0-9]*[A-z]*";

	public boolean Variable(String lex) {
		if (lex.matches(var)) {
			return true;
		} else {
			return false;
		}
	}

	public String[] Limpiar(String c) {
		String aux = c.replace("\\r\\n|\\r|\\n", "");
		return aux.split(" ");
	}

	public static void main(String[] args) {
		Sem obj = new Sem();
		obj.leer();
		obj.Analizar();
	}

	
}
