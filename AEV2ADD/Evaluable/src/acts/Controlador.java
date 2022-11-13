package acts;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.EventHandler;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.sql.Connection;
import java.text.StringCharacterIterator;
import java.util.ArrayList;

import javax.crypto.Cipher;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultHighlighter;
import javax.swing.text.Highlighter;
import javax.swing.text.Highlighter.HighlightPainter;
import com.mysql.cj.xdevapi.Statement;

import javax.xml.parsers.*;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import java.io.*;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Scanner;

public class Controlador {

	private Vista vista;
	private model model;
	private String ruta;

	public Controlador(Vista vista, model mode) {
		this.vista = vista;
		this.model = mode;
		initEventHandler();
	}
	
	private void initEventHandler() {
		//Boto per a executar el metode de mostrar les taules
		ActionListener botonMostrar = new ActionListener() {
		
			public void actionPerformed(ActionEvent e) {
			
				Connection con = model.conexioBBDD();
				String textoString = model.mostrarTotesLesTaules();
				System.out.println(textoString);

				vista.getTxtContenido().setText(textoString);
			}
		};
	
		vista.getBtnMostrarTablas().addActionListener(botonMostrar);
	
	
		//Boto per a executar el metode de mostrar la estructura de les dades
		ActionListener btnEstructura = new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
		
				Connection con = model.conexioBBDD();
				String textoString = vista.getTxtNombreTabla().getText();
				
				String aString = model.mostrarInfoTaula(textoString);
				
				vista.getTxtContenido().setText(aString);
			}
		};
	
		vista.getBtnEstructuraTabla().addActionListener(btnEstructura);
	
		//Boto per a executar el metode de iniciar sesio
		ActionListener botonLogin = new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				Connection con = model.conexioBBDD();
				
				String usuari = vista.getTxtUsuari().getText();
				String pass = vista.getTxtContrasenya().getText();

				acts.model.InicioSesion(usuari, pass);
				if (acts.model.InicioSesion(usuari, pass)) {
					JOptionPane.showMessageDialog(null, "USUARI CORRECTE");
					vista.getTxtConsulta().setVisible(true);
					vista.getBtnEstructuraTabla().setVisible(true);
					vista.getScrollPane().setVisible(true);
					vista.	getLblTablaInfo().setVisible(true);
					vista.getTxtNombreTabla().setVisible(true);
					vista.getTxtContenido().setVisible(true);
					vista.getBtnEjecutarConsulta().setVisible(true);
					vista.getBtnMostrarTablas().setVisible(true);
					vista.	getBtnCerrarConexion().setVisible(true);
					vista.getTxtUsuari().setVisible(false);;
					vista.getTxtContrasenya().setVisible(false);
					vista.getBtnLogin().setVisible(false);
					vista.getLblUsuari().setVisible(false);
					vista.getLblPass().setVisible(false);
				}else {
					JOptionPane.showMessageDialog(null, "ERROR");
				}
			}
		};
	
		vista.getBtnLogin().addActionListener(botonLogin);
		
	
		//Boto per a executar el metode de fer la consulta
		ActionListener botonEjecutarConsulta = new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				Connection con = model.conexioBBDD();
				
				String consulta = vista.getTxtConsulta().getText();

					model.realizarConsulta(consulta);

					vista.getTxtContenido().setText(model.realizarConsulta(consulta));
					
			}
		};
	
		vista.getBtnEjecutarConsulta().addActionListener(botonEjecutarConsulta);
		
		
		//Boto per a executar el metode de tancar consulta
		ActionListener CerrarConexion = new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {

					model.cerrarConexion();
					vista.getTxtConsulta().setVisible(false);
					vista.getBtnEstructuraTabla().setVisible(false);
					vista.getScrollPane().setVisible(false);
					vista.	getLblTablaInfo().setVisible(false);
					vista.getTxtNombreTabla().setVisible(false);
					vista.getTxtContenido().setVisible(false);
					vista.getBtnEjecutarConsulta().setVisible(false);
					vista.getBtnMostrarTablas().setVisible(false);
					vista.	getBtnCerrarConexion().setVisible(false);
					vista.getTxtUsuari().setVisible(true);;
					vista.getTxtContrasenya().setVisible(true);
					vista.getBtnLogin().setVisible(true);
					vista.getLblUsuari().setVisible(true);
					vista.getLblPass().setVisible(true);
					vista.getTxtUsuari().setText("");
					vista.getTxtContrasenya().setText("");
			}
		};
	
		vista.getbtnCerrarConexion().addActionListener(CerrarConexion);
		
}}
