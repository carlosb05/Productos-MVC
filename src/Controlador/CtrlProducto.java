/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.Producto;
import Modelo.ProductoDAO;
import Vista.ProductoFrm;
import java.awt.Dimension;
import java.awt.HeadlessException;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class CtrlProducto implements ActionListener {

    // Instancias
    Producto producto = new Producto();
    ProductoDAO productoDAO = new ProductoDAO();
    ProductoFrm frm = new ProductoFrm();
    DefaultTableModel modeloTabla = new DefaultTableModel();

    //Variables globales
    private int Id;
    private String nombre;
    private String codigo;
    private String descripcion;
    private String dimensiones;
    private Float valor;
    private int cat;

    public CtrlProducto(ProductoFrm frm) {
        this.frm = frm;
        frm.setVisible(true);
        agregarEventos();
        listarTabla();
    }

    private void agregarEventos() {
        frm.getBtnCrear().addActionListener(this);
        frm.getBtnBuscar().addActionListener(this);
        frm.getBtnActualizar().addActionListener(this);
        frm.getBtnEliminar().addActionListener(this);
        frm.getBtnLimpiar().addActionListener(this);

        frm.getTabla().addMouseListener(new MouseAdapter() {

            public void mouseClicked(MouseEvent e) {
                llenarCampos(e);
            }
        });
    }

    private void listarTabla() {
        String[] titulos = new String[]{"Id Producto", "Nombre", "Codigo", "Descripcion", "Dimensiones", "Valor", "Id Categoria"};
        modeloTabla = new DefaultTableModel(titulos, 0);
        List<Producto> listaProducto = productoDAO.listar();
        for (Producto producto : listaProducto) {
            modeloTabla.addRow(new Object[]{producto.getProId(), producto.getProNombre(), producto.getProCodigo(), producto.getProDescripcion(), producto.getProDimensiones(), producto.getProValor(), producto.getCatId()});
        }
        frm.getTabla().setModel(modeloTabla);
        frm.getTabla().setPreferredSize(new Dimension(350, modeloTabla.getRowCount() * 16));
    }

    private void agregar() {
        try {
            if (validarDatos()) {
                if (cargarDatos()) {
                    Producto producto = new Producto(nombre, codigo, descripcion, dimensiones, valor, cat);
                    productoDAO.agregarProducto(producto);
                    JOptionPane.showMessageDialog(null, "registro exitoso");
                    limpiarCampos();
                }
            }

        } catch (Exception e) {
            System.out.println("Error metodo agregar en el controlador" + e);
        } finally {
            listarTabla();
        }

    }

    private void seleccionar() {
        try {
            //if (validarDatos()) {
            if (cargarDatos()) {            
            productoDAO.obtenerProducto(producto);
            Id = Integer.parseInt(frm.getTxtId().getText());
            nombre = frm.getTxtNombre().getText();
            codigo = frm.getTxtCodigo().getText();
            descripcion = frm.getTxtDescripcion().getText();
            dimensiones = frm.getTxtDimensiones().getText();
            valor = Float.parseFloat(frm.getTxtValor().getText());
            cat = Integer.parseInt(frm.getTxtCat().getText());

            JOptionPane.showMessageDialog(null, "busqueda exitosa");
            limpiarCampos();
            }
            //}
        } catch (HeadlessException | NumberFormatException e) {
            System.out.println("Error metodo seleccionar controlador" + e);
            System.out.println("error en id" + Id);
        }

    }

    private void actualizar() {
        try {
            if (validarDatos()) {
                if (cargarDatos()) {
                    Producto producto = new Producto(Id, nombre, codigo, descripcion, dimensiones, valor, cat);
                    productoDAO.actualizarProducto(producto);
                    JOptionPane.showMessageDialog(null, "actualizacion exitosa");
                    limpiarCampos();
                }
            }
        } catch (HeadlessException e) {
            System.out.println("Error metodo actualizar controlador" + e);
        } finally {
            listarTabla();
        }

    }

    private void eliminar() {
        try {
            //if (Id != 0) {
            if (cargar()) {
                Producto producto = new Producto(Id, nombre, codigo, descripcion, dimensiones, valor, cat);
                productoDAO.eliminarProducto(producto);
                JOptionPane.showMessageDialog(null, "eliminacion exitosa");
                limpiarCampos();
            }
            //}
        } catch (HeadlessException e) {
            System.out.println("Error metodo eliminar controlador" + e);
        } finally {
            listarTabla();
        }

    }

    private void llenarCampos(MouseEvent e) {
        try {
        JTable target = (JTable) e.getSource();
        frm.getTxtId().setText(frm.getTabla().getModel().getValueAt(target.getSelectedRow(), 0).toString());
        frm.getTxtNombre().setText(frm.getTabla().getModel().getValueAt(target.getSelectedRow(), 1).toString());
        frm.getTxtCodigo().setText(frm.getTabla().getModel().getValueAt(target.getSelectedRow(), 2).toString());
        frm.getTxtDescripcion().setText(frm.getTabla().getModel().getValueAt(target.getSelectedRow(), 3).toString());
        frm.getTxtDimensiones().setText(frm.getTabla().getModel().getValueAt(target.getSelectedRow(), 4).toString());
        frm.getTxtValor().setText(frm.getTabla().getModel().getValueAt(target.getSelectedRow(), 5).toString());
        frm.getTxtCat().setText(frm.getTabla().getModel().getValueAt(target.getSelectedRow(), 6).toString());
            
    } catch (Exception ex) {    
      System.out.println("Error al llenar " + e);  
    }    
    }

    private boolean validarDatos() {
        if ("".equals(frm.getTxtNombre().getText()) || "".equals(frm.getTxtCodigo().getText()) || "".equals(frm.getTxtDescripcion().getText()) || "".equals(frm.getTxtDimensiones().getText()) || "".equals(frm.getTxtValor().getText()) || "".equals(frm.getTxtCat().getText())) {
            JOptionPane.showMessageDialog(null, "Los campos no pueden estar vacios", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        return true;
    }

    private void limpiarCampos() {
        frm.getTxtId().setText("");
        frm.getTxtNombre().setText("");
        frm.getTxtCodigo().setText("");
        frm.getTxtDescripcion().setText("");
        frm.getTxtDimensiones().setText("");
        frm.getTxtValor().setText("");
        frm.getTxtCat().setText("");
        
    }

    private boolean cargar() {
        try {
            Id = Integer.parseInt(frm.getTxtId().getText());

            return true;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "el campo Id debe ser texto", "Error", JOptionPane.ERROR_MESSAGE);
            System.out.println("Error al cargar " + e);
            return false;
        }
    }

    // metodo 3 en 1 1. carga variables globales 2. convierte los valores 3. valida que los campos sean numericos.
    private boolean cargarDatos() {
        try {
            Id = Integer.parseInt(frm.getTxtId().getText());
            nombre = frm.getTxtNombre().getText();
            codigo = frm.getTxtCodigo().getText();
            descripcion = frm.getTxtDescripcion().getText();
            dimensiones = frm.getTxtDimensiones().getText();
            valor = Float.parseFloat(frm.getTxtValor().getText());
            cat = Integer.parseInt(frm.getTxtCat().getText());
            return true;
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Los campos id, valor, cat deben ser numericos", "Error", JOptionPane.ERROR_MESSAGE);
            System.out.println("Error al cargar datos" + e);
            return false;
        }
    }

    // dar acccione a los botones
    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == frm.getBtnCrear()) {
            agregar();
        }
        if (ae.getSource() == frm.getBtnBuscar()) {
            seleccionar();
        }
        if (ae.getSource() == frm.getBtnActualizar()) {
            actualizar();
        }
        if (ae.getSource() == frm.getBtnEliminar()) {
            eliminar();
        }
        if (ae.getSource() == frm.getBtnLimpiar()) {
            limpiarCampos();
        }

    }
}
