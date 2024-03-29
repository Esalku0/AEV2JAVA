package acts;

import org.w3c.dom.*;
import org.xml.sax.SAXException;

import com.mysql.cj.xdevapi.Statement;

import javax.swing.JOptionPane;
import javax.xml.parsers.*;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import java.awt.Component;
import java.awt.Dialog;
import java.io.*;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

public class model {

	private static String host;
	private static String usuari;
	private static String contrasenya = "";
	private static String port;
	private static int port2;
	private static String nom;
	protected static final Component JFrame = null;
	private static Connection conexio;

	/**
	 * METODE PER A COMPROBAR SI EL USUARI EXISTEIX EN LA BASE DE DADES
	 * @param user: es el usuari que li pasem per a conectarse al programa
	 * @param pass: es la contrasenya que li pasem que despres convertim en MD5
	 * @return Retorna true si hi ha un usuari que coincidisca amb la contrasenya
	 */
	public static boolean InicioSesion(String user, String pass) {

		boolean respuesta = false;

		try {

			java.sql.Statement stmt = conexio.createStatement();

			String plaintext = pass;
			MessageDigest m = MessageDigest.getInstance("MD5");
			m.reset();
			m.update(plaintext.getBytes());
			byte[] digest = m.digest();
			BigInteger bigInt = new BigInteger(1, digest);
			String hashtext = bigInt.toString(16);
			while (hashtext.length() < 32) {
				hashtext = "0" + hashtext;

			}

			ResultSet rs = stmt
					.executeQuery("SELECT * FROM users WHERE user = '" + user + "' AND pass = '" + hashtext + "'");

			if (rs.next()) {
				if (rs.getRow() > 0) {
					respuesta = true;
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return respuesta;
	}

	/**
	 * METODE PER A REALITZAR QUALSEVOL CONSULTA SELECT INSERT UPDATE O DELETE QUE I PASEM
	 * @param consulta: es la consulta que li pasem per a executar
	 * @return retorna el resultat de la consulta
	 */
	public static String realizarConsulta(String consulta) {
		String text = "";
		int cont = 0;

		try {
			//Comprobem si es un select, si no ho es, fa la consulta com insert, update o delete
			if (consulta.contains("select") || consulta.contains("SELECT")) {
				java.sql.Statement stmt = conexio.createStatement();
				ResultSet rs = ((java.sql.Statement) stmt).executeQuery(consulta);
				int columnas = rs.getMetaData().getColumnCount();
				while (rs.next()) {
					for (int i = 1; i <= columnas; i++) {
						text += rs.getString(i) + "\t";
						cont++;
						if (cont == 6) {
							text += "\n";
							cont = 0;
						}
					}
				}
				rs.close();
			} else {
				PreparedStatement ejecutar = conexio.prepareStatement(consulta);
				int realizar = ejecutar.executeUpdate();
				ejecutar.close();
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return text;
	}
	
	/**
	 * METODE PER A MOSTRAR TODAS LAS TABLAS DE LA BASE DE DATOS
	 * @return retorna totes les taules com a string
	 */
	public static String mostrarTotesLesTaules() {
		String resultadoString = "";
		try {
			String consulta = "SHOW tables FROM books;";

			java.sql.Statement stmt = conexio.createStatement();
			ResultSet rs = stmt.executeQuery(consulta);

			int columnas = rs.getMetaData().getColumnCount();
			while (rs.next()) {
				for (int i = 1; i <= columnas; i++) {
					resultadoString += rs.getString(i) + "\t";
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resultadoString;
	}

	
	/**
	 * METODE PER A MOSTRAR TOTA LA INFORMACIO DE LES TAULES QUE LI PASEM
	 * @param taula de la qual volem mirar les dades
	 * @return retorna la informacio de les taules
	 */
	public static String mostrarInfoTaula(String taula) {
		String resultado = "";
		try {
			String consulta = "SHOW COLUMNS FROM " + taula;

			java.sql.Statement stmt = conexio.createStatement();
			ResultSet rs = stmt.executeQuery(consulta);

			int columnas = rs.getMetaData().getColumnCount();
			while (rs.next()) {
				for (int i = 1; i <= columnas; i++) {
					resultado += rs.getString(i) + "\t";
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return resultado;
	}
	/**
	 * METODE PER A CONECTARNOS A LA BASE DE DADES AMB LES DADES PER XML
	 * @return retorna la conexio de la bbdd
	 */
	public static Connection conexioBBDD() {
		String url = "";
		String usuari = "";
		String contrasenya = "";
		Connection con = null;
		try {
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document document = dBuilder.parse(new File("contenido.xml"));

			Element raiz = document.getDocumentElement();
			NodeList nodeList = document.getElementsByTagName("conexio");
			for (int i = 0; i < nodeList.getLength(); i++) {
				Node node = nodeList.item(i);
				if (node.getNodeType() == Node.ELEMENT_NODE) {
					Element eElement = (Element) node;
					url = eElement.getElementsByTagName("url").item(0).getTextContent();
					usuari = eElement.getElementsByTagName("usuari").item(0).getTextContent();
					contrasenya = eElement.getElementsByTagName("contrasenya").item(0).getTextContent();
				}
			}
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection(url, usuari, contrasenya);
			conexio = con;
			return con;

		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(JFrame, e);

		}
		return con;
	}

	
	/**
	 * METODE PER A TANCAR LA CONEXIO
	 */
	public void cerrarConexion() {
		try {
			conexioBBDD().close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}