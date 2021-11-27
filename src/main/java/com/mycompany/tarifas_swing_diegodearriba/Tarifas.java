
package com.mycompany.tarifas_swing_diegodearriba;

import java.awt.Image;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;


public class Tarifas extends javax.swing.JFrame {
    
    String tipo_servicio; // El tipo de paquete que vamos a enviar
    String envio; // Si es nacional o internacional
    Integer peso; // el numero de gramos, determinará el precio
    Double precio; // el precio final
    Double suplementoValorDeclarado; // suplemento de valorDeclarado, solo actuará si es marcado y es variable
    Double suplementoAvisoRecibo; // suplemento de avisoRecibo, solo actuará si es marcado y es un gasto fijo.
    
    public Tarifas() {
        // Creamos el cuerpo de nuestro frame, donde estableceremos su posición, iniciaremos bloqueando los campos que solo deban de funcionar mediante un evento-acción y cargamos la etiqueta con un logo
        initComponents();
        setLocationRelativeTo(null); 
        setResizable(false);
        valorDeclaradoNum.setEnabled(false);
        valorDeclarado.setEnabled(false);
        avisoRecibo.setEnabled(false);
        mostrarInformacion.setEditable(false);
        ImageIcon imagen = new ImageIcon ("src/imagenes/logo.jpg");
        Icon icono = new ImageIcon (imagen.getImage().getScaledInstance(etiquetaLogo.getWidth(), etiquetaLogo.getHeight(), Image.SCALE_DEFAULT));
        etiquetaLogo.setIcon(icono);
        this.repaint();

    }
    
    
    
    
    public Boolean calcularAvisoRecibo () { // Comprobamos si el check de avisoRecivo esta marcado, en caso de estarlo establecemos el atributo suplementoAvisoRecibo y lo asignamos el valor predeterminado por la página de correos.
        Boolean check = false;
        if (avisoRecibo.isSelected()) {
            suplementoAvisoRecibo = 1.94;
            check = true;
        }
        return check;
    }
    
    public Boolean calculoValorDeclarado () { // Comprobamos que el valor declarado este marcado
        Boolean check = false;
        if (valorDeclarado.isSelected()) {
            String valor = valorDeclaradoNum.getText();
            Double total = Double.parseDouble(valor);
            int a = 0;
            int contadorSumas = 0;
            for (int k = 0; k != total ; k++) { // En caso de estar marcado ponemos un bucle que nos cuente cuantas veces se suman 50 en el precio final.
                a++;
                if (a == 50) {
                    contadorSumas++;
                    a = 0;
                }
            }
            
            suplementoValorDeclarado = 2.10 * contadorSumas; // despues multiplicamos 2.10 por el numero de veces que hay 50 en el precio final, así obtenemos el suplemento correcto.
            check = true;
        }
            return check;
    }
    
    public Boolean comprobar () { // Comprobamos que el peso que ha introducido esté comprendido en nuestra tabla.
        boolean pesoCorrecto = true;
        if (peso > 2000 ||peso <= 0) {
            JOptionPane.showMessageDialog(this, "El peso permitido para los paquetes se situa entre 1 y 2000 gramos ");
            pesoCorrecto = false;
        }
        return pesoCorrecto;
    }
    
    public boolean certificado () { // Comprobamos que el tipo de servicio que ha contratado sea certificado, esto es clave ya que es la única forma de poder activar los suplementos.
        boolean certificado = false;
        if ((tipo_servicio.equals("Cartas certificadas")) ||(tipo_servicio.equals("Cartas y tarjetas postales urgentes y certificadas"))){
            certificado = true;
        }
        
        return certificado;
    }

    private void calcularPrecio () { // Calculamos el precio en función del peso del paquete, en caso de ser certificado hacemos una comprobación a los métodos de los suplementos que comprobaban si estaban marcados, de ser así se añade al precio

        if (tipo_servicio.equals("Cartas y tarjetas postales")) {
           if(peso <= 20) {
               precio = 0.70;
           } else if (peso > 20 && peso <= 50) {
               precio = 0.80;
           }  else if (peso > 50 && peso <= 100) {
               precio = 1.25;
           }   else if (peso > 100 && peso <= 500) {
               precio = 2.50;
           }  else if (peso > 500 && peso <= 1000) {
               precio = 5.15;
           } else if (peso > 1000 && peso <= 2000){
               precio = 5.60;
           }     
        } else if ((tipo_servicio.equals("Cartas certificadas"))) { 
            if(peso <= 20) {
               precio = 4.15;
           } else if (peso > 20 && peso <= 50) {
               precio = 4.25;
           }  else if (peso > 50 && peso <= 100) {
               precio = 4.70;
           }   else if (peso > 100 && peso <= 500) {
               precio = 5.95;
           }  else if (peso > 500 && peso <= 1000) {
               precio = 8.60;
           } else if (peso > 1000 && peso <= 2000) {
               precio = 9.05;
           }
            if (calculoValorDeclarado ()) {
                precio = precio + suplementoValorDeclarado;
            }
            if (calcularAvisoRecibo ()) {
                precio = precio + suplementoAvisoRecibo;
            }
            
        } else if (tipo_servicio.equals("Cartas y tarjetas postales urgentes")) {
            if(peso <= 20) {
               precio = 4.10;
           } else if (peso > 20 && peso <= 50) {
               precio = 4.22;
           } else if (peso > 50 && peso <= 100) {
               precio = 4.74;
           } else if (peso > 100 && peso <= 500) {
               precio = 6.38;
           } else if (peso > 500 && peso <= 1000) {
               precio = 8.60;
           } else if (peso > 1000 && peso <= 2000) {
               precio = 9.05;
           }
            
        } else if (tipo_servicio.equals("Cartas y tarjetas postales urgentes y certificadas")) {
            if(peso <= 20) {
               precio = 8.28;
            }else if (peso > 20 && peso <= 50) {
               precio = 8.40;
            }else if (peso > 50 && peso <= 100) {
               precio = 8.92;
            }else if (peso > 100 && peso <= 500) {
               precio = 10.55;
            }else if (peso > 500 && peso <= 1000) {
               precio = 14.00;
            }else if (peso > 1000 && peso <= 2000) {
               precio = 14.58;
            }
            if (calculoValorDeclarado ()) {
                precio = precio + suplementoValorDeclarado;
            } 
            if (calcularAvisoRecibo ()) {
                precio = precio + suplementoAvisoRecibo;
            }  
        }
    }
    
    
   
    

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panel = new javax.swing.JPanel();
        etiquetaTitulo = new javax.swing.JLabel();
        productoElegido = new javax.swing.JComboBox<>();
        etiquetaTipoEnvio = new javax.swing.JLabel();
        etiquetaEspecifiqueDestino = new javax.swing.JLabel();
        spinnerPeso = new javax.swing.JSpinner();
        etiquetaGramos = new javax.swing.JLabel();
        etiquetaPuntos = new javax.swing.JLabel();
        boton_confirmar = new javax.swing.JButton();
        avisoRecibo = new javax.swing.JCheckBox();
        valorDeclarado = new javax.swing.JCheckBox();
        boton_calcular1 = new javax.swing.JButton();
        tipoEnvio = new javax.swing.JComboBox<>();
        valorDeclaradoNum = new javax.swing.JTextField();
        etiquetaPrecio = new javax.swing.JLabel();
        etiqueta_EspecifiquePeso = new javax.swing.JLabel();
        etiquetaLogo = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        mostrarInformacion = new javax.swing.JTextPane();
        boton_limpiar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(255, 255, 255));

        panel.setBackground(new java.awt.Color(255, 204, 0));
        panel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        etiquetaTitulo.setFont(new java.awt.Font("Cambria", 1, 48)); // NOI18N
        etiquetaTitulo.setForeground(new java.awt.Color(0, 0, 0));
        etiquetaTitulo.setText("CALCULADORA DE TARIFAS DE ENVÍO");
        panel.add(etiquetaTitulo, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 20, 890, 40));

        productoElegido.setBackground(new java.awt.Color(255, 255, 255));
        productoElegido.setFont(new java.awt.Font("Arial Black", 1, 14)); // NOI18N
        productoElegido.setForeground(new java.awt.Color(0, 0, 0));
        productoElegido.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Cartas y tarjetas postales", "Cartas certificadas", "Cartas y tarjetas postales urgentes", "Cartas y tarjetas postales urgentes y certificadas", " " }));
        productoElegido.setBorder(new javax.swing.border.MatteBorder(null));
        productoElegido.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        productoElegido.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                productoElegidoActionPerformed(evt);
            }
        });
        panel.add(productoElegido, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 100, 480, 40));

        etiquetaTipoEnvio.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        etiquetaTipoEnvio.setForeground(new java.awt.Color(0, 0, 0));
        etiquetaTipoEnvio.setText("Seleccione tipo de envio:");
        panel.add(etiquetaTipoEnvio, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 110, 200, 30));

        etiquetaEspecifiqueDestino.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        etiquetaEspecifiqueDestino.setForeground(new java.awt.Color(0, 0, 0));
        etiquetaEspecifiqueDestino.setText("Especifique el destino del envio:");
        panel.add(etiquetaEspecifiqueDestino, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 220, 250, 50));
        panel.add(spinnerPeso, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 180, 100, 30));

        etiquetaGramos.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        etiquetaGramos.setForeground(new java.awt.Color(0, 0, 0));
        etiquetaGramos.setText("GRAMOS");
        panel.add(etiquetaGramos, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 190, 90, -1));

        etiquetaPuntos.setFont(new java.awt.Font("sansserif", 1, 24)); // NOI18N
        etiquetaPuntos.setForeground(new java.awt.Color(0, 0, 0));
        etiquetaPuntos.setText("..........................................................................................................................................................................................................................................................................");
        panel.add(etiquetaPuntos, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 52, 1110, 30));

        boton_confirmar.setBackground(new java.awt.Color(0, 0, 255));
        boton_confirmar.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        boton_confirmar.setForeground(new java.awt.Color(255, 255, 255));
        boton_confirmar.setText("Realizar envio");
        boton_confirmar.setBorder(new javax.swing.border.MatteBorder(null));
        boton_confirmar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                boton_confirmarActionPerformed(evt);
            }
        });
        panel.add(boton_confirmar, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 510, 390, 100));

        avisoRecibo.setBackground(new java.awt.Color(0, 0, 0));
        avisoRecibo.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        avisoRecibo.setForeground(new java.awt.Color(0, 0, 0));
        avisoRecibo.setText("Aviso de Recibo");
        panel.add(avisoRecibo, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 300, -1, -1));

        valorDeclarado.setBackground(new java.awt.Color(0, 0, 0));
        valorDeclarado.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        valorDeclarado.setForeground(new java.awt.Color(0, 0, 0));
        valorDeclarado.setText("Valor declarado");
        valorDeclarado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                valorDeclaradoActionPerformed(evt);
            }
        });
        panel.add(valorDeclarado, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 300, 160, 20));

        boton_calcular1.setBackground(new java.awt.Color(0, 0, 255));
        boton_calcular1.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        boton_calcular1.setForeground(new java.awt.Color(255, 255, 255));
        boton_calcular1.setText("Calcular precio");
        boton_calcular1.setBorder(new javax.swing.border.MatteBorder(null));
        boton_calcular1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                boton_calcular1ActionPerformed(evt);
            }
        });
        panel.add(boton_calcular1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 400, 390, 90));

        tipoEnvio.setBackground(new java.awt.Color(255, 255, 255));
        tipoEnvio.setFont(new java.awt.Font("sansserif", 1, 12)); // NOI18N
        tipoEnvio.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Nacional", "Internacional", " " }));
        tipoEnvio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tipoEnvioActionPerformed(evt);
            }
        });
        panel.add(tipoEnvio, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 230, 220, 40));

        valorDeclaradoNum.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        panel.add(valorDeclaradoNum, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 330, 240, 40));

        etiquetaPrecio.setBackground(new java.awt.Color(0, 0, 0));
        etiquetaPrecio.setFont(new java.awt.Font("Arial Narrow", 1, 36)); // NOI18N
        etiquetaPrecio.setForeground(new java.awt.Color(0, 0, 0));
        panel.add(etiquetaPrecio, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 420, 660, 110));

        etiqueta_EspecifiquePeso.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        etiqueta_EspecifiquePeso.setForeground(new java.awt.Color(0, 0, 0));
        etiqueta_EspecifiquePeso.setText("Especifique el peso del envio:");
        panel.add(etiqueta_EspecifiquePeso, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 170, 250, 40));
        panel.add(etiquetaLogo, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 200, 70));

        mostrarInformacion.setFont(new java.awt.Font("sansserif", 1, 12)); // NOI18N
        jScrollPane1.setViewportView(mostrarInformacion);

        panel.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 170, 610, 200));

        boton_limpiar.setBackground(new java.awt.Color(0, 0, 204));
        boton_limpiar.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        boton_limpiar.setForeground(new java.awt.Color(255, 255, 255));
        boton_limpiar.setText("Limpiar");
        boton_limpiar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                boton_limpiarActionPerformed(evt);
            }
        });
        panel.add(boton_limpiar, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 540, 410, 80));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panel, javax.swing.GroupLayout.DEFAULT_SIZE, 1111, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panel, javax.swing.GroupLayout.DEFAULT_SIZE, 621, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void productoElegidoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_productoElegidoActionPerformed
        tipo_servicio =  productoElegido.getSelectedItem().toString(); // Sacamos el tipo de servicio.
        if (certificado ()) { // comprobamos que es certificado, de ser así permitimos dar la opción de marcar los suplementos, sino se lo denegamos.
           valorDeclarado.setEnabled(true);
           avisoRecibo.setEnabled(true);
       } else {
           valorDeclarado.setEnabled(false);
           avisoRecibo.setEnabled(false); 
       }
            
           
    }//GEN-LAST:event_productoElegidoActionPerformed

    private void boton_confirmarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_boton_confirmarActionPerformed
        int mensaje = JOptionPane.showConfirmDialog(this, "Quiere confirmar el envio"); // Boton de certificar envio, con diversos JOptionPane para hacerlo más interactivo.
            if (mensaje == JOptionPane.YES_OPTION) {
                JOptionPane.showMessageDialog(this, "Pedido confirmado");
                System.exit(0);
            } else if (mensaje == JOptionPane.NO_OPTION) {
                JOptionPane.showMessageDialog(this, "Pedido no confirmado");
                int salir = JOptionPane.showConfirmDialog(this, "Quieres salir?");
                if (salir == JOptionPane.YES_OPTION) {
                    System.exit(0);
               }
            }
    }//GEN-LAST:event_boton_confirmarActionPerformed

    private void boton_calcular1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_boton_calcular1ActionPerformed
        peso = (Integer) spinnerPeso.getValue(); // calculamos los gramos, mediante el spinner
        tipo_servicio =  productoElegido.getSelectedItem().toString(); // calculamos el tipo de servicio
        envio = tipoEnvio.getSelectedItem().toString(); // calculamos el envio
        
        
        if (comprobar()) {  // Si la cantidad que ha introducido es correcta entonces
            calcularPrecio (); // calculamos el precio
            String informacion = ""; // añadimos en un String todos los datos recopilados
            informacion += "El tipo de servicio contratado es " + tipo_servicio;
            informacion +=  "\nHa seleccionado un peso de  " + peso + " gramos";
            informacion += "\nEl destino del envio es  " + envio;
            if (valorDeclarado.isSelected() && certificado () ) { // Si el valor esta elegido y es declarado, lo añadimos
                informacion += "\nHa marcado la opción de valor declarado, con una cantidad de " + valorDeclaradoNum.getText() + " se ha cobrado " + String.format("%.2f",suplementoValorDeclarado) + " euros";
            } else {
                informacion+="\nNo se ha aplicado la opción de valor declarado";  
            }
            if (avisoRecibo.isSelected() && certificado ()) { // Lo mismo con el aviso de recibo.
                informacion+="\nHa marcado la opción de Aviso de Recibo, se le ha facturado la cantidad de " + String.format("%.2f",suplementoAvisoRecibo) + " euros ";   
            } else {
                informacion += "\nNo se ha aplicado la opción de Aviso de Recibo ";    
            }
            
            mostrarInformacion.setText(informacion); // Mostramos la información en nuestro textpanel.
            etiquetaPrecio.setText("El precio del envio sería de "  + String.format("%.2f", precio) + " euros "); // Mostramos la información con el precio en nuestra etiqueta.
    }
    }//GEN-LAST:event_boton_calcular1ActionPerformed

    private void tipoEnvioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tipoEnvioActionPerformed
        // TODO add your handling code here:
       envio = tipoEnvio.getSelectedItem().toString(); // Llenamos la variable envio al cambiar la selección.
    }//GEN-LAST:event_tipoEnvioActionPerformed

    private void valorDeclaradoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_valorDeclaradoActionPerformed

        if (valorDeclarado.isSelected() == false) { // Si el valor declarado no esta elegido, no permitimos escribir en el textfield y lo vaciamos.
            valorDeclaradoNum.setEnabled(false);
            valorDeclaradoNum.setText("");
        } else {
            valorDeclaradoNum.setEnabled(true);
        }
    }//GEN-LAST:event_valorDeclaradoActionPerformed

    private void boton_limpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_boton_limpiarActionPerformed
        // Volvemos todo al estado inicial.
        valorDeclaradoNum.setText("");
        etiquetaPrecio.setText("");
        mostrarInformacion.setText("");
        spinnerPeso.setValue(0);
        valorDeclarado.setSelected(false);
        avisoRecibo.setSelected(false); 

    }//GEN-LAST:event_boton_limpiarActionPerformed


    public static void main(String args[]) {
        
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Tarifas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Tarifas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Tarifas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Tarifas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

       
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Tarifas().setVisible(true);
                
            }
        });
    }
   
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JCheckBox avisoRecibo;
    private javax.swing.JButton boton_calcular1;
    private javax.swing.JButton boton_confirmar;
    private javax.swing.JButton boton_limpiar;
    private javax.swing.JLabel etiquetaEspecifiqueDestino;
    private javax.swing.JLabel etiquetaGramos;
    private javax.swing.JLabel etiquetaLogo;
    private javax.swing.JLabel etiquetaPrecio;
    private javax.swing.JLabel etiquetaPuntos;
    private javax.swing.JLabel etiquetaTipoEnvio;
    private javax.swing.JLabel etiquetaTitulo;
    private javax.swing.JLabel etiqueta_EspecifiquePeso;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextPane mostrarInformacion;
    private javax.swing.JPanel panel;
    private javax.swing.JComboBox<String> productoElegido;
    private javax.swing.JSpinner spinnerPeso;
    private javax.swing.JComboBox<String> tipoEnvio;
    private javax.swing.JCheckBox valorDeclarado;
    private javax.swing.JTextField valorDeclaradoNum;
    // End of variables declaration//GEN-END:variables
}
