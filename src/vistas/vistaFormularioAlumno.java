/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JInternalFrame.java to edit this template
 */
package vistas;

import accesoADatos.AlumnoData;
import accesoADatos.Conexion;
import entidades.Alumno;
import java.time.LocalDate;
/**
 *
 * @author Nicolas
 */
public class vistaFormularioAlumno extends javax.swing.JInternalFrame {
    private AlumnoData alumnoData;
    private Alumno alumnoBuscado=null;
    /**
     * Creates new form vistaFormularioAlumno
     */
    public vistaFormularioAlumno() {
        initComponents();
         promptTxt();
        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPnlComponetes = new javax.swing.JPanel();
        jTxtDni = new javax.swing.JTextField();
        jTxtApellido = new javax.swing.JTextField();
        jTxtNombre = new javax.swing.JTextField();
        jCboxEstado = new javax.swing.JCheckBox();
        jTxtFechaNacimiento = new javax.swing.JTextField();
        jBtnBuscar = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jBtnNuevo = new javax.swing.JButton();
        jBtnGuardar = new javax.swing.JButton();
        jBtnEliminar = new javax.swing.JButton();
        jBtnSalir = new javax.swing.JButton();

        setIconifiable(true);
        setResizable(true);
        setTitle("Gestion Alumno");

        jCboxEstado.setText("Estado");
        jCboxEstado.setToolTipText("Alumno activo o no");

        jBtnBuscar.setText("Buscar");
        jBtnBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnBuscarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPnlComponetesLayout = new javax.swing.GroupLayout(jPnlComponetes);
        jPnlComponetes.setLayout(jPnlComponetesLayout);
        jPnlComponetesLayout.setHorizontalGroup(
            jPnlComponetesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPnlComponetesLayout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(jPnlComponetesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTxtApellido, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jCboxEstado)
                    .addGroup(jPnlComponetesLayout.createSequentialGroup()
                        .addComponent(jTxtDni, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(27, 27, 27)
                        .addComponent(jBtnBuscar))
                    .addGroup(jPnlComponetesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(jTxtFechaNacimiento, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 112, Short.MAX_VALUE)
                        .addComponent(jTxtNombre, javax.swing.GroupLayout.Alignment.LEADING)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPnlComponetesLayout.setVerticalGroup(
            jPnlComponetesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPnlComponetesLayout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(jPnlComponetesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTxtDni, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jBtnBuscar))
                .addGap(18, 18, 18)
                .addComponent(jTxtApellido, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jTxtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jTxtFechaNacimiento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jCboxEstado)
                .addContainerGap(31, Short.MAX_VALUE))
        );

        jBtnNuevo.setText("Nuevo");
        jBtnNuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnNuevoActionPerformed(evt);
            }
        });

        jBtnGuardar.setText("Guardar");
        jBtnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnGuardarActionPerformed(evt);
            }
        });

        jBtnEliminar.setText("Eliminar");

        jBtnSalir.setText("Salir");
        jBtnSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnSalirActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jBtnNuevo)
                .addGap(18, 18, 18)
                .addComponent(jBtnGuardar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jBtnEliminar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jBtnSalir)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jBtnNuevo)
                    .addComponent(jBtnGuardar)
                    .addComponent(jBtnEliminar)
                    .addComponent(jBtnSalir))
                .addContainerGap(22, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPnlComponetes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addComponent(jPnlComponetes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(32, 32, 32)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(23, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jBtnSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnSalirActionPerformed
        this.dispose();
    }//GEN-LAST:event_jBtnSalirActionPerformed

    private void jBtnNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnNuevoActionPerformed
        //resetea los componetes dentro dedl panel
        librerias.FuncionesComunes.resetFormContentPanel(jPnlComponetes);
    }//GEN-LAST:event_jBtnNuevoActionPerformed

    private void jBtnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnBuscarActionPerformed
        try {
            alumnoData = new AlumnoData();
            alumnoBuscado=null;
            String campoDni = jTxtDni.getText();
            if (librerias.FuncionesComunes.EventoValidarNumericos(campoDni)) {
                int dni = Integer.parseInt(campoDni);
                alumnoBuscado = alumnoData.buscarAlumnoDni(dni);//si no esta alumno es null
                
                if(alumnoBuscado!=null){
                   
                    completarCamposAlumno(alumnoBuscado);
                }else{
                    librerias.FuncionesComunes.vistaDialogo("El dni: "+campoDni+" no se encuentra." , 1);
                }

            } else {
                librerias.FuncionesComunes.vistaDialogo("El dni es un campo numerico.", 0);
            }
        } catch (Exception e) {
            Conexion.msjError.add("VistaFormAlumno evento boton buscar por dni: " + e.getMessage());
        }


    }//GEN-LAST:event_jBtnBuscarActionPerformed

    private void jBtnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnGuardarActionPerformed
        try {
           
            if(alumnoBuscado!=null){
                 
                editar();
            }else{
                guardar();
            }
            
        } catch (Exception e) {
            Conexion.msjError.add("VistaFormAlumno evento boton Guardar: " + e.getMessage());
        }
    }//GEN-LAST:event_jBtnGuardarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jBtnBuscar;
    private javax.swing.JButton jBtnEliminar;
    private javax.swing.JButton jBtnGuardar;
    private javax.swing.JButton jBtnNuevo;
    private javax.swing.JButton jBtnSalir;
    private javax.swing.JCheckBox jCboxEstado;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPnlComponetes;
    private javax.swing.JTextField jTxtApellido;
    private javax.swing.JTextField jTxtDni;
    private javax.swing.JTextField jTxtFechaNacimiento;
    private javax.swing.JTextField jTxtNombre;
    // End of variables declaration//GEN-END:variables


    private void promptTxt() {

        librerias.FuncionesComunes.textPrompt(jTxtDni, "DNI-34877112");
        librerias.FuncionesComunes.textPrompt(jTxtApellido, "Apellido");
        librerias.FuncionesComunes.textPrompt(jTxtNombre, "Nombre");
        librerias.FuncionesComunes.textPrompt(jTxtFechaNacimiento, "26-10-1989");

    }
    
    private void completarCamposAlumno(Alumno alumno){
        jCboxEstado.setSelected(false);
        jTxtDni.setText(String.valueOf(alumno.getDni()));
        jTxtApellido.setText(alumno.getApellido());
        jTxtNombre.setText(alumno.getNombre());
        jTxtFechaNacimiento.setText(librerias.FuncionesComunes.voltearFecha(alumno.getFechaNacimiento(),false));
       
        if(alumno.getEstado()){
            jCboxEstado.setSelected(true);
        }
        
    }
    private void editar(){
       
        boolean estado=false;
        if(jCboxEstado.isSelected()){
            estado=true;
        }
        if(validarCampos()){
            
             
            alumnoBuscado.setDni(Integer.parseInt(jTxtDni.getText()));
            alumnoBuscado.setNombre(jTxtNombre.getText());
            alumnoBuscado.setApellido(jTxtApellido.getText());
            System.out.println("Validadoooooo id alumno:"+alumnoBuscado.getIdAlumno() );
            alumnoBuscado.setFechaNacimiento(librerias.FuncionesComunes.convertirFechaBd(jTxtFechaNacimiento.getText()));
            System.out.println("Validadoooooo id alumno:"+alumnoBuscado.getIdAlumno() );
            alumnoBuscado.setEstado(estado);
            System.out.println(alumnoBuscado);
            if(alumnoData.modificarMateria(alumnoBuscado)){
                librerias.FuncionesComunes.vistaDialogo("Editado correctamente", 1);
                
            }else{
                 librerias.FuncionesComunes.vistaDialogo("No se pudo editar", 0);
            }
            
            librerias.FuncionesComunes.resetFormContentPanel(jPnlComponetes);
            
       }
        Conexion.mostrarErrores();
    }
    
    private void guardar(){
            
            }
  
    
    private boolean validarCampos(){
        boolean res=false;
       
        if(librerias.FuncionesComunes.EventoValidarNumericos(jTxtDni.getText().trim()) &&
                librerias.FuncionesComunes.validarVacio(jTxtApellido.getText().trim()) &&
                librerias.FuncionesComunes.validarVacio(jTxtNombre.getText().trim()) &&
                librerias.FuncionesComunes.validaFecha(jTxtFechaNacimiento.getText().trim())){
            
            res=true;
            
        }
        System.out.println("VAldiar campos:"+res);
       
        return res;
    }
    
}
